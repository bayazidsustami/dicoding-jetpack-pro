package com.dicoding.sample.academy.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseWithModule(
    @Embedded
    var mCourse: CourseEntity,

    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    var mModules: List<ModuleEntity>
)