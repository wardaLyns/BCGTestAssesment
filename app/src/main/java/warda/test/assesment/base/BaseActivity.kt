package warda.test.assesment.base

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import warda.test.assesment.R
import warda.test.assesment.util.Constant

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var progressDialog: Dialog
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressDialog()
        rootView = window.decorView.rootView
    }

    private fun initProgressDialog() {
        progressDialog = Dialog(this)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val window = progressDialog.window
        window?.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)
        progressDialog.setCancelable(false)
    }

    override fun showSuccess(message: String?) {
        val snackBar = Snackbar.make(rootView, message.toString(), Snackbar.LENGTH_SHORT)
        val green = ContextCompat.getColor(this, android.R.color.holo_green_light)
        snackBar.view.setBackgroundColor(green)
        snackBar.show()
    }

    override fun showSuccess(stringResId: Int) {
        val snackBar = Snackbar.make(rootView, getString(stringResId), Snackbar.LENGTH_SHORT)
        val green = ContextCompat.getColor(this, android.R.color.holo_green_light)
        snackBar.view.setBackgroundColor(green)
        snackBar.show()
    }

    override fun showError(status: Int?, error: String?) {
        var e = error.toString()
        if (e.contains(Constant.UNABLE_TO_RESOLVE_HOST)) e = getString(R.string.unable_to_connect)
        if (e.toLowerCase().contains("null") || e.toLowerCase().contains(Constant.JSON_ERROR)) e =
            getString(R.string.something_went_wrong)

        val snackBar = Snackbar.make(rootView, e, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(Color.RED)
        snackBar.show()
    }

    override fun showError(stringResId: Int) {
        val snackBar = Snackbar.make(rootView, getString(stringResId), Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(Color.RED)
        snackBar.show()
    }

    override fun showMessage(stringResId: Int) {
        val snackBar = Snackbar.make(rootView, getString(stringResId), Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        snackBar.show()
    }

    override fun showMessage(message: String?) {
        val snackBar = Snackbar.make(rootView, message.toString(), Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        snackBar.show()
    }

    override fun showProgressDialog() {
        if (progressDialog.isShowing) progressDialog.dismiss()
        progressDialog.show()
    }

    override fun hideProgressDialog() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }
}