package com.jfma75.mymovies.ui.base

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_SECURE
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.jfma75.mymovies.ui.common.ILogger

/**
 * Created by juanfran on 22/02/2018.
 */

abstract class BaseActivity : AppCompatActivity(), ILogger {
    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @get:LayoutRes
    protected abstract val contentResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentResource)
    }

    override fun onPause() {
        super.onPause()
        window.setFlags(FLAG_SECURE, FLAG_SECURE)
    }

    override fun onResume() {
        super.onResume()
        window.clearFlags(FLAG_SECURE)
    }
}