package com.project.usychol.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.usychol.viewModel.ProfileViewModel

class ProfileViewModelFactory(private val id: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(id) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}