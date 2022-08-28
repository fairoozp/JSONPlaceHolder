package com.example.jsonplaceholder.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.jsonplaceholder.BuildConfig
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkTheme()
        clickListener()
        binding.tvAppVersion.text = "Current App Version ${BuildConfig.VERSION_NAME}"
    }

    private fun clickListener() {
        val sharedPreferences = mContext.getSharedPreferences("ThemeSharedPref", AppCompatActivity.MODE_PRIVATE)
        binding.cvSystem.setOnClickListener {
            val myEdit = sharedPreferences.edit()
            myEdit.putString("theme", "system")
            myEdit.apply()
            checkTheme()
        }
        binding.cvLight.setOnClickListener {
            val myEdit = sharedPreferences.edit()
            myEdit.putString("theme", "light")
            myEdit.apply()
            checkTheme()
        }
        binding.cvNight.setOnClickListener {
            val myEdit = sharedPreferences.edit()
            myEdit.putString("theme", "night")
            myEdit.apply()
            checkTheme()
        }
    }

    private fun checkTheme() {
        val sharedPreferences = mContext.getSharedPreferences("ThemeSharedPref", AppCompatActivity.MODE_PRIVATE)
        clearSelection()
        when (sharedPreferences.getString("theme", "system")) {
            "system" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                binding.tvSystem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cardSelectColor))
            }
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.tvLight.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cardSelectColor))
            }
            "night" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.tvNight.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cardSelectColor))
            }
        }
    }

    private fun clearSelection() {
        binding.tvSystem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.backgroundColor))
        binding.tvLight.setBackgroundColor(ContextCompat.getColor(mContext, R.color.backgroundColor))
        binding.tvNight.setBackgroundColor(ContextCompat.getColor(mContext, R.color.backgroundColor))
    }

}