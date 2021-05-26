package com.dicoding.sample.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.vo.Resource

class CourseReaderViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    var courseId = MutableLiveData<String>()
    var moduleId = MutableLiveData<String>()

    fun setSelectedCourse(courseId: String) {
        this.courseId.value = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId.value = moduleId
    }

    var modules: LiveData<Resource<List<ModuleEntity>>> = Transformations.switchMap(courseId) {mCourseId->
        repository.getAllModulesByCourse(mCourseId)
    }

    val selectedModule: LiveData<Resource<ModuleEntity>> = Transformations.switchMap(moduleId){selectedPosition ->
        repository.getContent(selectedPosition)
    }

    fun readContent(module: ModuleEntity){
        repository.setReadModule(module)
    }

    fun getModuleSize(): Int{
        if (modules.value != null){
            val moduleEntities = modules.value?.data
            if (moduleEntities != null){
                return moduleEntities.size
            }
        }

        return 0
    }

    fun setNextPage(){
        if (selectedModule.value != null && modules.value != null){
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data
            if (moduleEntity != null && moduleEntities != null){
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0){
                    moduleId.value = moduleEntities[position + 1].moduleId
                }
            }
        }
    }

    fun setPrevPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data
            if (moduleEntity != null && moduleEntities != null) {
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0) {
                    moduleId.value = moduleEntities[position - 1].moduleId
                }
            }
        }
    }
}