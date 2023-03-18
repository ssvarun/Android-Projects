package com.example.puppyparadise

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.puppyparadise.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var ispermissionreadgranted = 0
    lateinit var appdatabase: AppDatabase
    private lateinit var drawerlayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        appdatabase = AppDatabase.getDatabase(this)


        val navcontoller = this.findNavController(R.id.myNavHostFragment)
        drawerlayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navcontoller, drawerlayout)
        NavigationUI.setupWithNavController(binding.navView, navcontoller)

        val actionBar: ActionBar?
        actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#291F1F"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        val navView = binding.navView
        binding.navView.setNavigationItemSelectedListener{ menuitem->
            when (menuitem.itemId) {
                R.id.Admin -> {
                    navcontoller.navigate(MainfragmentDirections.actionMainfragmentToAdminlogin())
                    binding.drawerLayout.closeDrawers()
                    true
                }
                R.id.About -> {
                    navcontoller.navigate(MainfragmentDirections.actionMainfragmentToAboutFragment())
                    binding.drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    false
                }
            }
        }
        binding.navView.bringToFront()



//        val petdetailslist = listOf(
//            PetDetails(2, 1, R.drawable.goldenretriever.toString(), 4000.00, "Pedigree", "Retreiver"),
//            PetDetails(3, 1, R.drawable.bgpet, 4500.00, "Chocos", "Husky"),
//            PetDetails(4, 1, R.drawable.bgpet, 10000.50, "Chocos", "Husky"),
//            PetDetails(5, 1, R.drawable.bgpet, 12000.00, "Chocos", "Husky"),
//            PetDetails(6, 1, R.drawable.bgpet, 6000.00, "Chocos", "Husky"),
//            PetDetails(7, 1, R.drawable.bgpet, 8000.00, "Chocos", "Husky")
//        )
        val categorylist = listOf(
            Category(null, "labor"),
            Category(null, "Husky")

        )

        lifecycleScope.launch {
            categorylist.forEach { appdatabase.customerdao().insertcategory(it) }
        }

//        lifecycleScope.launch {
//            petdetailslist.forEach { appdatabase.customerdao().insertpetdetails(it) }
//        }

    }



    override fun onSupportNavigateUp(): Boolean {
        val navcontroller = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navcontroller, drawerlayout)
    }

    override fun onBackPressed() {
        if (this.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerlayout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

}

