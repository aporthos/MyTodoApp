package net.portes.mytodo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import net.portes.mytodo.R
import net.portes.mytodo.ui.login.LoginActivity
import net.portes.mytodo.ui.login.LoginSharedPref
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun launch(from: AppCompatActivity) {
            from.startActivity(Intent(from, MainActivity::class.java))
            from.finish()
        }
    }

    @Inject
    lateinit var loginSharedPref: LoginSharedPref

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.navViewBottomNavigation)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_ipc, R.id.navigation_topten, R.id.navigation_notifications
            )
        )
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            LoginActivity.launch(this)
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.navViewBottomNavigation).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}