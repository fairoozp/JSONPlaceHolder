package com.example.jsonplaceholder

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.jsonplaceholder.databinding.ActivityMainBinding
import com.example.jsonplaceholder.mainViewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentPage: Int = 0
    var currentDestPage: Int = 0
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListener()
        observeCurrentPage()
        setNavigation(R.id.homeFragment)
        preference()
        checkPreference()
    }

    private fun checkPreference() {
        val sharedPreferences = getSharedPreferences("ThemeSharedPref", MODE_PRIVATE)
        when (sharedPreferences.getString("theme", "system")) {
            "system" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "night" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun preference() {
        val sharedPreferences = getSharedPreferences("ThemeSharedPref", MODE_PRIVATE)
        val data = sharedPreferences.getString("theme", "")
        if (data == "") {
            val myEdit = sharedPreferences.edit()
            myEdit.putString("theme", "system")
            myEdit.apply()
        }
    }

    private fun setNavigation(destination: Int) {
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navigation)
        graph.setStartDestination(destination)
        navHostFragment.navController.graph = graph
    }

    private fun observeCurrentPage() {
        mainViewModel.currentPage.observe(this) {
            currentPage = it
            changeSelectColor()
        }
        mainViewModel.currentDestPage.observe(this) {
            currentDestPage = it
            setNavigation(currentDestPage)
        }
    }

    private fun clickListener() {
        binding.tvAlbum.setOnClickListener {
            if (currentPage != binding.tvAlbum.id) {
                currentPage = binding.tvAlbum.id
                mainViewModel.currentPage.postValue(currentPage)
                currentDestPage = R.id.albumFragment
                mainViewModel.currentDestPage.postValue(currentDestPage)
            }
        }
        binding.tvHome.setOnClickListener {
            if (currentPage != binding.tvHome.id) {
                currentPage = binding.tvHome.id
                mainViewModel.currentPage.postValue(currentPage)
                currentDestPage = R.id.homeFragment
                mainViewModel.currentDestPage.postValue(currentDestPage)
            }
        }
        binding.tvSettings.setOnClickListener {
            if (currentPage != binding.tvSettings.id) {
                currentPage = binding.tvSettings.id
                mainViewModel.currentPage.postValue(currentPage)
                currentDestPage = R.id.settingsFragment
                mainViewModel.currentDestPage.postValue(currentDestPage)
            }
        }
    }

    private fun changeSelectColor() {
        when (currentPage) {
            binding.tvHome.id -> {
                clearColor()
                binding.tvHome.setBackgroundColor(ContextCompat.getColor(this, R.color.cardSelectColor))
            }
            binding.tvAlbum.id -> {
                clearColor()
                binding.tvAlbum.setBackgroundColor(ContextCompat.getColor(this, R.color.cardSelectColor))
            }
            binding.tvSettings.id -> {
                clearColor()
                binding.tvSettings.setBackgroundColor(ContextCompat.getColor(this, R.color.cardSelectColor))
            }
        }
    }

    private fun clearColor() {
        binding.tvHome.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBgColor))
        binding.tvAlbum.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBgColor))
        binding.tvSettings.setBackgroundColor(ContextCompat.getColor(this, R.color.cardBgColor))
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}