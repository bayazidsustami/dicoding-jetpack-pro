package com.dicoding.submission.jetpack

import androidx.paging.PagedList
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object PagedListUtils {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[Mockito.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }

        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}