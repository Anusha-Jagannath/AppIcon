package com.example.appicon

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appicon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var iconName: String = "icon1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.changeBtn.setOnClickListener {
            changeIcon()
        }

        binding.changeBtn.setOnClickListener {
            iconName = "icon2"
            changeIcon2()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun changeIcon() {
        val packageManager = applicationContext.packageManager

        // Disable all icons first
        packageManager.setComponentEnabledSetting(
            ComponentName(applicationContext, "com.example.appicon.MainActivity"),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packageManager.setComponentEnabledSetting(
            ComponentName(applicationContext, "com.example.appicon.MainActivityAlias"),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

    }

    private fun changeIcon2() {
        val packageManager = applicationContext.packageManager

        // Disable all icons first
        packageManager.setComponentEnabledSetting(
            ComponentName(applicationContext, "com.example.appicon.MainActivityAlias"),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        // Enable the chosen icon
        val componentName = when (iconName) {
            "icon1" -> "com.example.appicon.MainActivity"
            else -> "com.example.appicon.MainActivityAlias" // Default
        }

        packageManager.setComponentEnabledSetting(
            ComponentName(applicationContext, "com.example.appicon.MainActivity"),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

    }
}