package com.android.weathertestapp.ui.base

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.weathertestapp.App
import com.android.weathertestapp.R
import com.android.weathertestapp.di.component.ActivityComponent
import com.android.weathertestapp.di.component.DaggerActivityComponent
import com.android.weathertestapp.di.module.ActivityModule
import com.android.weathertestapp.utils.DialogHelper
import com.android.weathertestapp.utils.ResourceUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


abstract class BaseMvpActivity : AppCompatActivity(), BaseView {

    private var mActivityComponent: ActivityComponent? = null
    private var loadingDialog: AlertDialog? = null
    private val disposableSubscription = CompositeDisposable()

    @Inject
    lateinit var dialogHelper: DialogHelper

    val activityComponent: ActivityComponent?
        get() {
            if (mActivityComponent == null) {
                createComponent()
            }
            return mActivityComponent
        }

    val isActive: Boolean
        get() = !this.isFinishing && !this.isDestroyed


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent!!.inject(this)
        //set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
        if (intent.extras != null) {
            sendExtrasToPresenter(intent.extras!!)
        }
    }

    protected abstract fun getPresenter(): BasePresenter<*>

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (getIntent().extras != null) {
            sendExtrasToPresenter(getIntent().extras!!)
        }
    }

    override fun onPause() {
        super.onPause()
        getPresenter().isViewPaused = true
    }

    override fun onResume() {
        super.onResume()
        getPresenter().isViewPaused = false
        getPresenter().onResume()
    }

    protected abstract fun sendExtrasToPresenter(extras: Bundle)

    private fun createComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as App).component)
            .build()
    }

    override fun showProgress() {
        if (!isActive) {
            return
        }
        hideProgress()
        if (loadingDialog == null) {
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(this, ResourceUtil.getStyleId(this, "transparentBackgroundDialog"))
            val loadingView = View.inflate(this, R.layout.loading_layout, null)
            builder.setView(loadingView)
            builder.setCancelable(false)
            loadingDialog = builder.create()
        }
        try {
            loadingDialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            loadingDialog!!.show()
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }

    override fun hideProgress() {
        if (!isActive) {
            return
        }
        if (loadingDialog != null) {
            try {
                loadingDialog!!.dismiss()
                loadingDialog = null
            } catch (e: RuntimeException) {
                e.printStackTrace()
            }
        }
    }

    override fun showError(message: String, messageStyle: Int) {
        when (messageStyle) {
            ErrorView.ERROR_DIALOG -> {
                val title = getString(R.string.title_information)
                dialogHelper.showAlert(this, title, message)
            }
            ErrorView.ERROR_TOAST -> Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            else -> {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun showError(@StringRes messageResource: Int, messageStyle: Int) {
        when (messageStyle) {
            ErrorView.ERROR_DIALOG -> {
                val title = getString(R.string.title_information)
                dialogHelper.showAlert(this, title, getString(messageResource))
            }
            ErrorView.ERROR_TOAST -> Toast.makeText(this, messageResource, Toast.LENGTH_LONG).show()
            else -> {
            }
        }
    }

    override fun destroyView() {
        finish()
    }

    override fun onDestroy() {
        disposableSubscription.clear()
        getPresenter().onDestroyView()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun addToSubscription(disposable: Disposable) {
        disposableSubscription.add(disposable)
    }
}
