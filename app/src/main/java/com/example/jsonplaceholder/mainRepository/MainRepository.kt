package com.example.jsonplaceholder.mainRepository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.mainModel.AlbumModel
import com.example.jsonplaceholder.mainModel.CommentModel
import com.example.jsonplaceholder.mainModel.PhotoModel
import com.example.jsonplaceholder.mainModel.PostModel
import com.example.jsonplaceholder.network.ApiInterface
import com.example.jsonplaceholder.network.callApi

class MainRepository {

    private var apiInterface = ApiInterface.getInstance()

    var postValue: MutableLiveData<List<PostModel>> = MutableLiveData()
    var commentValue: MutableLiveData<List<CommentModel>> = MutableLiveData()
    var albumValue: MutableLiveData<List<AlbumModel>> = MutableLiveData()
    var photoValue: MutableLiveData<List<PhotoModel>> = MutableLiveData()

    fun getPost(lifecycleOwner: LifecycleOwner) {
        callApi { apiInterface.getPost() }.observe(lifecycleOwner) {
            postValue.postValue(it)
        }
    }

    fun getComment(lifecycleOwner: LifecycleOwner, postId: String) {
        callApi { apiInterface.getComment(postId) }.observe(lifecycleOwner) {
            commentValue.postValue(it)
        }
    }

    fun getAlbum(lifecycleOwner: LifecycleOwner) {
        callApi { apiInterface.getAlbum() }.observe(lifecycleOwner) {
            albumValue.postValue(it)
        }
    }

    fun getPhotos(lifecycleOwner: LifecycleOwner, albumId: String) {
        callApi { apiInterface.getPhotos(albumId) }.observe(lifecycleOwner) {
            photoValue.postValue(it)
        }
    }
}