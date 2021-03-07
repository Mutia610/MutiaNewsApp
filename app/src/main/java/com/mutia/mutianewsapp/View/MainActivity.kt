package com.mutia.mutianewsapp.View

import android.app.AlertDialog
import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.contains
import androidx.recyclerview.widget.LinearLayoutManager
import com.mutia.mutianewsapp.Adapter.KategoriAdapter
import com.mutia.mutianewsapp.Model.NewsCategory
import com.mutia.mutianewsapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_no_internet.view.*
import java.util.*
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

    private var dialogView: Dialog? = null

    val listKategori = ArrayList<NewsCategory>()
    var adapter: KategoriAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isConnect()) {
            listKategori.add(NewsCategory(R.drawable.teknologi, "Teknologi"))
            listKategori.add(NewsCategory(R.drawable.bisnis, "Bisnis"))
            listKategori.add(NewsCategory(R.drawable.kesehatan, "Kesehatan"))
            listKategori.add(NewsCategory(R.drawable.olahraga, "Olahraga"))

            adapter =
                KategoriAdapter(listKategori, object : KategoriAdapter.OnClickListener {
                    override fun detail(categoryName: String?) {
                        if (isConnect()) {
                            val intent = Intent(this@MainActivity, NewsActivity::class.java)
                            intent.putExtra("CATEGORY", categoryName)
                            startActivity(intent)
                        } else {
                            val intent = Intent(this@MainActivity, NoInternetActivity::class.java)
                            startActivity(intent)
                        }

                    }

                })

            recyclerKategori.setHasFixedSize(true)
            recyclerKategori.adapter = adapter

        } else {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.item_search, menu)
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter!!.filter.filter(newText)
                return false
            }
        })
        return true
    }


    private fun isConnect(): Boolean {
        val connect: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}



