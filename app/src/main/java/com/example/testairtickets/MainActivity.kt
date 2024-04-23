package com.example.testairtickets

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.testairtickets.databinding.ActivityMainBinding
import com.example.testairtickets.screens.mainScreen.MainFragment
import com.example.testairtickets.screens.stubScreen.StubFragment
import com.google.android.material.navigation.NavigationBarMenu


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge(SystemBarStyle.dark(Color.BLACK))
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomMainMenu) { v, insets ->
            v.updatePadding(
                bottom = 0
            )
            insets
        }
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        supportFragmentManager.beginTransaction().replace(R.id.mainContent, MainFragment()).commit()

        binding.bottomMainMenu.selectedItemId = R.id.actionMain

        binding.bottomMainMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.actionMain -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, MainFragment()).commit()

                R.id.actionHotels -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, StubFragment()).commit()

                R.id.actionShort -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, StubFragment()).commit()

                R.id.actionSub -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, StubFragment()).commit()

                R.id.actionUser -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, StubFragment()).commit()

            }
            return@setOnItemSelectedListener true
        }

        setContentView(binding.root)
    }

}
