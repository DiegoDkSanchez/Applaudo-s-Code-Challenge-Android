package com.example.challengeaplaudo.modules

import com.example.challengeaplaudo.MainViewModel
import com.example.challengeaplaudo.service.Service
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    // single instance of Service
    single{ Service() }

    //ViewModel
    viewModel { MainViewModel(get()) }
}