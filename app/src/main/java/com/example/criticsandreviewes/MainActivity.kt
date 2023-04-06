package com.example.criticsandreviewes

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.criticsandreviewes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navHostCriticsFragment: NavHostFragment
    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.criticsFragment -> {
                    binding.toolbar.visibility = View.VISIBLE
                }
                else -> {
                    binding.toolbar.visibility = View.GONE
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        setupRadioGroup()
    }

    private fun setupNavigation() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostCriticsFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewCritios) as NavHostFragment
    }

    private fun setupRadioGroup() {
        binding.myRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.reviewes_radio_btn -> {
                    navController = navHostFragment.navController
                    binding.navigate.root.visibility = View.VISIBLE
                    binding.navigateCrtitic.root.visibility = View.GONE
                    setupActionBar(R.color.persik, "Reviewes")
                }
                R.id.critics_radio_btn -> {
                    navController = navHostCriticsFragment.navController
                    binding.navigateCrtitic.root.visibility = View.VISIBLE
                    binding.navigate.root.visibility = View.GONE
                    setupActionBar(R.color.blue, "Critics")
                    navController.addOnDestinationChangedListener(onDestinationChangedListener)
                }
            }
        }
    }

    private fun setupActionBar(colorId: Int, title: String) {
        binding.toolbar.setBackgroundColor(ContextCompat.getColor(this@MainActivity, colorId))
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        supportActionBar?.run {
            setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this@MainActivity, colorId)))
            this.title = ""
            binding.toolbarTitle.text = title
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(onDestinationChangedListener)
    }

}