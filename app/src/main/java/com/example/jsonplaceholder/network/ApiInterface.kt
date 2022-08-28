package com.example.jsonplaceholder.network

import com.example.jsonplaceholder.mainModel.AlbumModel
import com.example.jsonplaceholder.mainModel.CommentModel
import com.example.jsonplaceholder.mainModel.PhotoModel
import com.example.jsonplaceholder.mainModel.PostModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(EndPoints.POST_URL)
    suspend fun getPost(): Response<List<PostModel>>

    @GET(EndPoints.COMMENT_URL)
    suspend fun getComment(@Query("postId")postId: String): Response<List<CommentModel>>

    @GET(EndPoints.ALBUM_URL)
    suspend fun getAlbum(): Response<List<AlbumModel>>

    @GET(EndPoints.PHOTOS_URL)
    suspend fun getPhotos(@Query("albumId") albumId: String): Response<List<PhotoModel>>

    companion object {
        private var apiInterface: ApiInterface? = null
        fun getInstance() : ApiInterface {
            if (apiInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(EndPoints.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiInterface = retrofit.create(ApiInterface::class.java)
            }
            return apiInterface!!
        }
    }
}