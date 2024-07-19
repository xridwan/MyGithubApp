package id.eve.core.data.remote.network

import id.eve.core.data.remote.response.UserData
import id.eve.core.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
    ): UserData

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String,
    ): UserResponse

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String,
    ): List<UserResponse>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String,
    ): List<UserResponse>
}