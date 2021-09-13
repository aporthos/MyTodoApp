package net.portes.mytodo.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import net.portes.mytodo.ui.main.MainActivity
import net.portes.shared.extensions.observe
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getIpcUrl()
        MainActivity.launch(this)

        observe(viewModel.ipcUrl, ::resultToIpcUrl)
    }

    private fun resultToIpcUrl(result: Boolean) {
        Timber.i("resultToIpcUrl: -> remote config ${result}")
    }
}