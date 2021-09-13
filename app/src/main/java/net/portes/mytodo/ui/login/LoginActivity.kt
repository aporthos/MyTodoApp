package net.portes.mytodo.ui.login

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import net.portes.mytodo.R
import net.portes.mytodo.databinding.ActivityLoginBinding
import net.portes.mytodo.ui.base.BaseActivity
import net.portes.mytodo.ui.main.MainActivity
import net.portes.shared.extensions.createFun
import net.portes.shared.extensions.isBiometricSuccess
import net.portes.shared.extensions.observe
import net.portes.shared.ui.base.ViewState
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(), View.OnClickListener {

    companion object {

        @JvmStatic
        fun launch(from: AppCompatActivity) {
            from.startActivity(Intent(from, LoginActivity::class.java))
            from.finish()
        }

    }

    @Inject
    lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var biometricPrompt: BiometricPrompt

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun initializeView() {
        biometricPrompt = createFun(this, {
            toConfigLoginAnonymous()
        }, {
            viewModel.toLoginFirebase()
        })

        if (!isBiometricSuccess()) {
            toConfigLoginAnonymous()
        }
    }

    override fun initListeners() {
        dataBinding().loginButton.setOnClickListener(this)
        dataBinding().alsoAnonymousButton.setOnClickListener(this)
    }

    override fun initObservers() {
        observe(viewModel.loginFirebaseResponse, ::resultLoginFIrebase)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.loginButton -> startLogin()
            R.id.alsoAnonymousButton -> viewModel.toLoginFirebase()
        }
    }

    private fun startLogin() {
        if (isBiometricSuccess()) {
            biometricPrompt.authenticate(promptInfo)
        } else {
            viewModel.toLoginFirebase()
        }
    }

    private fun toConfigLoginAnonymous() {
        dataBinding().signInImageView.setImageResource(R.drawable.anonymous)
        dataBinding().messageDescriptionSignInTextView.setText(R.string.message_description_anonymous)
        dataBinding().loginButton.setText(R.string.message_use_anonymous)
        dataBinding().alsoSignIntTextView.isVisible = false
        dataBinding().alsoAnonymousButton.isVisible = false
    }

    private fun resultLoginFIrebase(result: ViewState<Boolean>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                MainActivity.launch(this)
            }
            is ViewState.Error -> hideLoader()
        }
    }

}