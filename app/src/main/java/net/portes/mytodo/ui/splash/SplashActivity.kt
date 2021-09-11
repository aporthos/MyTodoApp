package net.portes.mytodo.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.portes.mytodo.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.launch(this)
    }
}