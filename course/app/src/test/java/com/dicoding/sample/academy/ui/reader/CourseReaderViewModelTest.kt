package com.dicoding.sample.academy.ui.reader

import com.dicoding.sample.academy.data.ContentEntity
import com.dicoding.sample.academy.data.ModuleEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @Mock
    private lateinit var repository: AcademyRepository

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
        `when`(repository.getAllModulesByCourse(courseId)).thenReturn(dummyModules as ArrayList<ModuleEntity>)
        val moduleEntities = viewModel.getModules()
        verify(repository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }

    @Test
    fun getSelectedModule() {
        `when`(repository.getContent(courseId, moduleId)).thenReturn(dummyModules[0])
        val moduleEntity = viewModel.getSelectedModule()
        verify(repository).getContent(courseId, moduleId)
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity.contentEntity
        assertNotNull(contentEntity)
        val content = contentEntity?.content
        assertNotNull(content)
        assertEquals(content, dummyModules[0].contentEntity?.content)
    }
}