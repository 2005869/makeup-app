package com.fv2005869.makeupapp

import android.app.Application
import com.fv2005869.makeupapp.data.AppContainer
import com.fv2005869.makeupapp.data.DefaultAppContainer



class MakeUpApplication:Application() {

    lateinit var container:AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }

}