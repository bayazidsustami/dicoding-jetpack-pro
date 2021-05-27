package com.dicoding.sample.academy

import androidx.paging.PagedList
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt

object PagedListUtils {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }

        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}