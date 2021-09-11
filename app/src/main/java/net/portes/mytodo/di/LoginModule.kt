package net.portes.mytodo.di

import android.content.Context
import androidx.biometric.BiometricPrompt
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.portes.login.data.datasource.LoginFirebaseDataSourceImpl
import net.portes.login.data.repository.LoginFirebaseRepositoryImpl
import net.portes.login.domain.datasource.LoginFirebaseDataSource
import net.portes.login.domain.repository.LoginFirebaseRepository
import net.portes.mytodo.R
import javax.inject.Singleton

/**
 * @author amadeus.portes
 */
@Module
@InstallIn(ApplicationComponent::class)
object LoginModule {

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun providesLoginFirebaseDataSource(loginFirebaseDataSourceImpl: LoginFirebaseDataSourceImpl): LoginFirebaseDataSource =
        loginFirebaseDataSourceImpl

    @Singleton
    @Provides
    fun providesLoginFirebaseRepository(loginFirebaseRepositoryImpl: LoginFirebaseRepositoryImpl): LoginFirebaseRepository =
        loginFirebaseRepositoryImpl

    @Provides
    fun providesPromptInfo(context: Context): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder()
            .setTitle(context.getString(R.string.app_name))
            .setSubtitle(context.getString(R.string.message_subtitle_fingerprint))
            .setDescription(context.getString(R.string.message_caninit_fingerprint))
            .setConfirmationRequired(true)
            .setNegativeButtonText(context.getString(R.string.message_cancel))
            .build()

}