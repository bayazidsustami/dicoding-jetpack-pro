package com.dicoding.submission.jetpack.data.dataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.submission.jetpack.data.dataSource.remote.ApiResult
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val coroutineScope: CoroutineScope
) {
    private val result = MediatorLiveData<Result<ResultType>>()

    init {
        result.value = Result.Loading(data = null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource){data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)){
                coroutineScope.launch {
                    fetchFromNetwork(dbSource)
                }
            }else{
                result.addSource(dbSource){newData ->
                    result.value =Result.Success(newData)
                }
            }
        }
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?):Boolean

    protected abstract suspend fun createCall(): LiveData<ApiResult<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    private suspend fun fetchFromNetwork(dbSource: LiveData<ResultType>){
        val apiResponse = createCall()

        coroutineScope.launch(Dispatchers.Main){
            result.addSource(dbSource){newData ->
                result.value =Result.Loading(data = newData)
            }

            result.addSource(apiResponse){response ->
                result.removeSource(apiResponse)
                result.removeSource(dbSource)

                when(response){
                    is ApiResult.Success -> {
                        coroutineScope.launch(Dispatchers.IO) {
                            saveCallResult(response.data)
                        }
                        result.addSource(loadFromDB()){newData->
                            result.value = Result.Success(data = newData)
                        }
                    }
                    is ApiResult.Error -> {
                        onFetchFailed()
                        result.addSource(dbSource){newData ->
                            result.value = Result.Error(newData, response.message)
                        }
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Result<ResultType>> = result
}