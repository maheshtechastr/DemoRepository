package com.mpg.shaadidemoapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mpg.shaadidemoapp.R
import com.mpg.shaadidemoapp.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    /*
     * Rather than injecting the ViewModelFactory
     * in the activity, we are going to implement the
     * HasActivityInjector and inject the ViewModelFactory
     * into our Fragments
     * */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment?>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment?>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
         * We still need to inject this method
         * into our activity so that our fragment can
         * inject the ViewModelFactory
         * */
        AndroidInjection.inject(this)

        binding = setContentView(this, R.layout.activity_main)

        Timber.i(TAG)

        setupNavigationDrawer()

        setSupportActionBar(findViewById(R.id.toolbar))

        val navController: NavController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration =
            AppBarConfiguration.Builder(
//                R.id.device_list_fragment,
//                R.id.employee_list_fragment,
//                R.id.device_allotted_list_fragment
            )
                .setOpenableLayout(drawerLayout)
                .build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout))
            .apply {
                setStatusBarBackground(R.color.colorPrimaryDark)
            }
    }


}