package com.example.myappspinnerfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val redFragment = RedFragment.newInstance()
        val greenFragment = GreenFragment.newInstance()
        val orangeFragment = OrangeFragment.newInstance()
        val blueFragment = BlueFragment.newInstance()
        val purpleFragment = PurpleFragment.newInstance()
        val colors = resources.getStringArray(R.array.Colors)
        val spinner = findViewById<Spinner>(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                when (position) {
                    1 -> selectFragment(redFragment)
                    2 -> selectFragment(greenFragment)
                    3 -> selectFragment(orangeFragment)
                    4 -> selectFragment(blueFragment)
                    5 -> selectFragment(purpleFragment)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun selectFragment(fragment: Fragment) {
        val btChangeColor = findViewById<Button>(R.id.btChangeColor)
        btChangeColor.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("name")
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}