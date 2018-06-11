package com.vinhdn.question2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vinhdn.question2.base.BaseActivity
import com.vinhdn.question2.ui.product.ProductFragment

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "addContentFragmentIfEmpty ")
        var fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment != null) {
            return
        }
        fragment = ProductFragment()
        Log.d(TAG, "addContentFragmentIfEmpty begin")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.commit()
    }
}
