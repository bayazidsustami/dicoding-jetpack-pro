package com.dicoding.sample.academy.ui.reader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.sample.academy.data.entity.ContentEntity
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.utils.DataDummy
import com.dicoding.sample.academy.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AcademyRepository

    @Mock
    private lateinit var modulesObserver: Observer<Resource<List<ModuleEntity>>>

    @Mock
    private lateinit var moduleObserver: Observer<Resource<ModuleEntity>>


    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(repository)
        viewModel.setSelectedCourse(courseId)
        viewModel.setSelectedModule(moduleId)

        val dummyModule = dummyModules[0]
        dummyModule.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">"+dummyModule.title+"</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Test
    fun getModules() {
        val modules = MutableLiveData<Resource<List<ModuleEntity>>>()
        val resource = Resource.success(dummyModules)
        modules.value = resource

        `when`(repository.getAllModulesByCourse(courseId)).thenReturn(modules)

        viewModel.modules.observeForever(modulesObserver)
        verify(modulesObserver).onChanged(resource)
    }

    @Test
    fun getSelectedModule() {
        val module = MutableLiveData<Resource<ModuleEntity>>()
        val resource = Resource.success(dummyModules[0])
        module.value = resource

        `when`(repository.getContent(moduleId)).thenReturn(module)

        viewModel.selectedModule.observeForever(moduleObserver)
        verify(moduleObserver).onChanged(resource)
    }
}