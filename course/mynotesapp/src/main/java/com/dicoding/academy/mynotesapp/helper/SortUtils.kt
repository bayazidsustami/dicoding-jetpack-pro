package com.dicoding.academy.mynotesapp.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "newest"
    const val OLDEST = "oldest"
    const val RANDOM = "random"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery{
        val simpleQuery = StringBuilder().append("SELECT * FROM note ")
        if (filter == NEWEST){
            simpleQuery.append("ORDER BY id DESC")
        } else if (filter == OLDEST){
            simpleQuery.append("ORDER BY id ASC")
        } else if (filter == RANDOM){
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}