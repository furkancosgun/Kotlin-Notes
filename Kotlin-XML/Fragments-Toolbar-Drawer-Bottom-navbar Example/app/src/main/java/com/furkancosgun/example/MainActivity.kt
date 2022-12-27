package com.furkancosgun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.furkancosgun.example.View.fragment_home
import com.furkancosgun.example.View.fragment_menu
import com.furkancosgun.example.View.fragment_search
import com.furkancosgun.example.databinding.ActivityMainBinding

enum class Page {
    Home, Search, Menu;

    val getFragemnt: () -> (Fragment) = {
        when (this) {
            Home -> fragment_home()
            Search -> fragment_search()
            Menu -> fragment_menu()
        }
    }

    val getIndex: () -> (Int) = {
        when (this) {
            Home -> 0
            Search -> 1
            Menu -> 2
        }
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentChanger(Page.Home)

        binding.main.bottomNavigation.setOnItemSelectedListener {
            changeSelectedItem(it.itemId)
        }

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            binding.drawerLayout.close()
            changeSelectedItem(menuItem.itemId)
        }
    }

    private fun changeSelectedItem(itemId: Int): Boolean {
        when (itemId) {
            R.id.item_home -> fragmentChanger(Page.Home)
            R.id.item_search -> fragmentChanger(Page.Search)
            R.id.item_menu -> fragmentChanger(Page.Menu)
        }
        return true
    }

    private fun fragmentChanger(page: Page): Boolean {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frgament_renderer, page.getFragemnt())
        fragmentTransaction.commit()
        binding.navigationView.menu.getItem(page.getIndex()).isChecked = true
        binding.main.bottomNavigation.menu.getItem(page.getIndex()).isChecked = true
        return true
    }
}