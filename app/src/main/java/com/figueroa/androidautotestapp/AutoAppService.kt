package com.figueroa.androidautotestapp

import android.content.Intent
import com.figueroa.androidautotestapp.AutoAppScreen
import com.google.android.libraries.car.app.CarAppService
import com.google.android.libraries.car.app.Screen

class AutoAppService : CarAppService() {
    override fun onCreateScreen(intent: Intent): Screen {
        return AutoAppScreen(carContext)
    }
}