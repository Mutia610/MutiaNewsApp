package com.mutia.mutianewsapp.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mutia.mutianewsapp.Model.ArticlesItem
import com.mutia.mutianewsapp.R
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.android.synthetic.main.activity_teknologi.*
import kotlinx.android.synthetic.main.dialog_no_internet.view.*

class DetailNewsActivity : AppCompatActivity() {

    private var dialogView: Dialog? = null

    var webSettings: WebSettings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        val getData = intent.getParcelableExtra<ArticlesItem>("DATA")

        if (isConnect()) {

            if (getData != null) {

                webSettings = webView.settings

                webView.webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, newProgress: Int) {
                        pbDetail.setVisibility(View.VISIBLE)
                        pbDetail.setProgress(newProgress)
                        if (newProgress == 100) {
                            pbDetail.setVisibility(View.GONE)
                        }
                        super.onProgressChanged(view, newProgress)
                    }
                }

                webView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        request: WebResourceRequest
                    ): Boolean {
                        view.loadUrl(request.toString())
                        return true
                    }

                    override fun onPageFinished(view: WebView, url: String) {
                        super.onPageFinished(view, url)
                        pbDetail.setVisibility(View.GONE)
                    }
                }


                webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                webView.loadUrl(getData.url.toString())

            } else {
                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show()
            }
        } else {
            pbDetail.visibility = View.GONE

            val dialog = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_no_internet, null)
            dialog.setView(view)

            view.back.setOnClickListener {
                dialogView?.dismiss()
            }

            dialogView = dialog.create()
            dialogView?.show()
        }

    }

    private fun isConnect(): Boolean {
        val connect: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}