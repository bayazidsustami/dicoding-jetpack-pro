package com.dicoding.sample.academy.data.source

import com.dicoding.sample.academy.data.ContentEntity
import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.data.ModuleEntity
import com.dicoding.sample.academy.data.source.remote.RemoteDataSource

class AcademyRepository private constructor(
    private val remoteDataSource: RemoteDataSource
): AcademyDataSource{
    companion object{
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): AcademyRepository =
            instance ?: synchronized(this){
                instance ?: AcademyRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getAllCourses(): List<CourseEntity> {
        val courseResponse = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponse){
            val course = CourseEntity(
                response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getBookmarkedCourses(): List<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponses) {
            val course = CourseEntity(response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath)
            courseList.add(course)
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String): CourseEntity {
        val courseResponses = remoteDataSource.getAllCourses()
        lateinit var course: CourseEntity
        for (response in courseResponses) {
            if (response.id == courseId) {
                course = CourseEntity(response.id,
                    response.title,
                    response.description,
                    response.date,
                    false,
                    response.imagePath)
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String): List<ModuleEntity> {
        val moduleResponses = remoteDataSource.getModules(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        for(response in moduleResponses) {
            val course = ModuleEntity(response.moduleId,
                response.courseId,
                response.title,
                response.position,
                false)
            moduleList.add(course)
        }
        return moduleList
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModules(courseId)
        lateinit var module: ModuleEntity
        for(response in moduleResponses) {
            if (response.moduleId == moduleId) {
                module = ModuleEntity(response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false)
                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }
        return module
    }
}