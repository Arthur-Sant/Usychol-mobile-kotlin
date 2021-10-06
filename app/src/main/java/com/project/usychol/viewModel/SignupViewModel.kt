package com.project.usychol.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.project.usychol.db.PsychologistDB
import com.project.usychol.data.repositories.UserRepository
import com.project.usychol.domain.entities.User
import com.project.usychol.implementations.UserImplementation
import com.project.usychol.useCases.UserUseCase

class SignupViewModel: ViewModel() {

    private val psychologistDB = PsychologistDB()
    private val psychologistDAO = UserImplementation(psychologistDB)
    private val psychologistRepository = UserRepository(psychologistDAO)
    private val psychologistUseCases = UserUseCase(psychologistRepository)

//    Uri.parse("file:///android_asset/images/jack_road.jpg"))
    // codigo guardado pra qaundo for pegar o caminho da imagem

    @RequiresApi(Build.VERSION_CODES.N)
    fun registerPsychologist(user: User){

        psychologistUseCases.createUser(user)
    }
}