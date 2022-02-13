package com.example.drawingappkotlin

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {
    var myCanvas: myCanvas? = null
    var tabs: TabLayout? = null
    var colorTabs: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myCanvas = myCanvas(this, null)
        setContentView(R.layout.activity_main)

        //color tabs
        colorTabs = findViewById<View>(R.id.color_tab_layout) as TabLayout
        colorTabs!!.visibility = View.GONE
        setupColorTabIcons()
        tabs = findViewById<View>(R.id.tabLayout) as TabLayout
        tabs!!.addOnTabSelectedListener(
            object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    Log.d("mytag", "shape tag position " + tab.position)
                    myCanvas!!.changeBrush(tab.position)
                    if (tab.position == 4) {
                        colorTabs!!.visibility = View.VISIBLE
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    if (tab.position == 4) {
                        colorTabs!!.visibility = View.GONE
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            }
        )
        colorTabs!!.addOnTabSelectedListener(
            object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    Toast.makeText(applicationContext, "color tag position", tab.position)
                    myCanvas!!.changeColor(tab.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            }
        )
    }

    private fun setupColorTabIcons() {
        colorTabs!!.getTabAt(0)!!.icon!!.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
        colorTabs!!.getTabAt(1)!!.icon!!
            .setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN)
        colorTabs!!.getTabAt(2)!!.icon!!.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)
        colorTabs!!.getTabAt(3)!!.icon!!
            .setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
    }
}