package com.dicoding.sample.academy.data.source

import androidx.lifecycle.LiveData
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.entity.CourseWithModule
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.data.source.local.LocalDataSource
import com.dicoding.sample.academy.data.source.remote.ApiResponse
import com.dicoding.sample.academy.data.source.remote.RemoteDataSource
import com.dicoding.sample.academy.data.source.remote.response.ContentResponse
import com.dicoding.sample.academy.data.source.remote.response.CourseResponse
import com.dicoding.sample.academy.data.source.remote.response.ModuleResponse
import com.dicoding.sample.academy.utils.AppExecutors
import com.dicoding.sample.academy.vo.Resource

class AcademyRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): AcademyDataSource{
    companion object{
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): AcademyRepository =
            instance ?: synchronized(this){
                instance ?: AcademyRepository(remoteDataSource, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<CourseEntity>> {
                return localDataSource.getAllCourses()
            }

            override fun shouldFetch(data: List<CourseEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return remoteDataSource.getAllCourses()
            }

            override fun saveCallResult(data: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in data) {
                    val course = CourseEntity(response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath)
                    courseList.add(course)
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        return localDataSource.getBookmarkedCourses()
    }

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {

        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<CourseWithModule> {
                return localDataSource.getCourseWithModules(courseId)
            }

            override fun shouldFetch(data: CourseWithModule?): Boolean {
                return data?.mModules == null || data.mModules.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModules(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data) {
                    val course = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {

        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<ModuleEntity>> {
                return localDataSource.getAllModulesByCourse(courseId)
            }

            override fun shouldFetch(data: List<ModuleEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModules(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for(response in data) {
                    val course = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)
                    moduleList.add(course)
                }

                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors){
            override fun loadFromDB(): LiveData<ModuleEntity> {
                return localDataSource.getModuleWithContent(moduleId)
            }

            override fun shouldFetch(data: ModuleEntity?): Boolean {
                return data?.contentEntity == null
            }

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> {
                return remoteDataSource.getContent(moduleId)
            }

            override fun saveCallResult(data: ContentResponse) {
                return localDataSource.updateContent(data.content, moduleId)
            }
        }.asLiveData()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setCourseBookmark(course, state)
        }
    }

    override fun setReadModule(module: ModuleEntity) {
        appExecutors.diskIO().execute {
            localDataSource.setReadModule(module)
        }
    }
}