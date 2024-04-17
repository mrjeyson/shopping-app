package com.jsoft.shoppingapp.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jsoft.shoppingapp.R
import com.jsoft.shoppingapp.activities.ShoppingActivity

fun Fragment.hideBottomNavigationView() {
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            R.id.bottomNavigation
        )
    bottomNavigationView.visibility = View.GONE
}

fun Fragment.showBottomNavigationView() {
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            R.id.bottomNavigation
        )
    bottomNavigationView.visibility = View.VISIBLE
}

