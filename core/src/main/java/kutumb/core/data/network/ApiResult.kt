package kutumb.core.data.network

sealed class ApiResult<out T> {
    class Success<out T>(val data: T) : ApiResult<T>()
    class Error(val message: String) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}