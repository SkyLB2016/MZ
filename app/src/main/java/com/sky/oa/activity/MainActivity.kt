package com.sky.oa.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.sky.oa.R
import com.sky.oa.databinding.ActivityMainBinding
import com.sky.ui.activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appBar.toolbar)

        val controller = findNavController(R.id.navFragment)
        NavigationUI.setupWithNavController(binding.bottomBar,controller)



    }

}