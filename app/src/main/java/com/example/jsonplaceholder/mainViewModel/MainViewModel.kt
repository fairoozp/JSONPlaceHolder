package com.example.jsonplaceholder.mainViewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.mainModel.AlbumModel
import com.example.jsonplaceholder.mainModel.CommentModel
import com.example.jsonplaceholder.mainModel.PhotoModel
import com.example.jsonplaceholder.mainModel.PostModel
import com.example.jsonplaceholder.mainRepository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    var currentPage: MutableLiveData<Int> = MutableLiveData()
    var currentDestPage: MutableLiveData<Int> = MutableLiveData()
    var postValue: MutableLiveData<List<PostModel>> = repository.postValue
    var commentValue: MutableLiveData<List<CommentModel>> = repository.commentValue
    var albumValue: MutableLiveData<List<AlbumModel>> = repository.albumValue
    var photoValue: MutableLiveData<List<PhotoModel>> = repository.photoValue
    fun getPost(lifecycleOwner: LifecycleOwner) { repository.getPost(lifecycleOwner) }
    fun getComment(lifecycleOwner: LifecycleOwner, postId: String) { repository.getComment(lifecycleOwner, postId) }
    fun getAlbum(lifecycleOwner: LifecycleOwner) { repository.getAlbum(lifecycleOwner) }
    fun getPhotos(lifecycleOwner: LifecycleOwner, albumId: String) { repository.getPhotos(lifecycleOwner, albumId) }
}