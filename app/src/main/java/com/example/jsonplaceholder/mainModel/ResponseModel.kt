package com.example.jsonplaceholder.mainModel

data class PostModel(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val body: String?
)

data class CommentModel(
    val postId: Int?,
    val id: Int?,
    val name: String?,
    val email: String?,
    val body: String?
)

data class AlbumModel(
    val userId: Int?,
    val id: Int?,
    val title: String?
)

data class PhotoModel(
    val albumId: Int?,
    val id: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)