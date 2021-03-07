package com.mutia.mutianewsapp.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mutia.mutianewsapp.Adapter.NewsAdapter
import com.mutia.mutianewsapp.Config.ConfigNetwork
import com.mutia.mutianewsapp.Model.ArticlesItem
import com.mutia.mutianewsapp.Model.ResponseNews
import com.mutia.mutianewsapp.R
import com.mutia.mutianewsapp.databinding.ActivityTeknologiBinding
import kotlinx.android.synthetic.main.activity_teknologi.*
import kotlinx.android.synthetic.main.dialog_no_internet.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTeknologiBinding
    private var dialogView: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_teknologi)

        binding = ActivityTeknologiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName : String = intent.getStringExtra("CATEGORY").toString()

        if (isConnect()){
            if (categoryName == "Teknologi"){
                showDataTeknologi()
            }else if (categoryName == "Bisnis"){
                showDataBisnis()
            }else if (categoryName == "Kesehatan"){
                showDataKesehatan()
            }else{
                showDataOlahraga()
            }

        }else{
            pb.visibility = View.GONE

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

    private fun showDataTeknologi() {
        ConfigNetwork.service().getTeknologi().enqueue(object: Callback<ResponseNews>{
            override fun onResponse(
                call: Call<ResponseNews>,
                response: Response<ResponseNews>
            ) {
                if (response.isSuccessful){

                    pb.visibility = View.GONE
                    val status = response.body()?.status

                    if (status == "ok"){
                        val data = response.body()?.articles

                        binding.recyclerNews.adapter = NewsAdapter(data,this@NewsActivity, object : NewsAdapter.OnClickListener{
                            override fun detail(item: ArticlesItem?) {
                                if (isConnect()){
                                    val intent = Intent(this@NewsActivity, DetailNewsActivity::class.java)
                                    intent.putExtra("DATA", item)
                                    startActivity(intent)
                                }else{
                                    val intent = Intent(this@NewsActivity, NoInternetActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        })
                    }
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@NewsActivity,"Gagal get data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun showDataBisnis() {
        ConfigNetwork.service().getBisnis().enqueue(object: Callback<ResponseNews>{
            override fun onResponse(
                call: Call<ResponseNews>,
                response: Response<ResponseNews>
            ) {
                if (response.isSuccessful){

                    pb.visibility = View.GONE
                    val status = response.body()?.status

                    if (status == "ok"){
                        val data = response.body()?.articles

                        binding.recyclerNews.adapter = NewsAdapter(data,this@NewsActivity, object : NewsAdapter.OnClickListener{
                            override fun detail(item: ArticlesItem?) {
                                val intent = Intent(this@NewsActivity, DetailNewsActivity::class.java)
                                intent.putExtra("DATA", item)
                                startActivity(intent)
                            }

                        })
                    }
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@NewsActivity,"Gagal get data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun showDataOlahraga() {
        ConfigNetwork.service().getOlahraga().enqueue(object: Callback<ResponseNews>{
            override fun onResponse(
                call: Call<ResponseNews>,
                response: Response<ResponseNews>
            ) {
                if (response.isSuccessful){

                    pb.visibility = View.GONE
                    val status = response.body()?.status

                    if (status == "ok"){
                        val data = response.body()?.articles

                        binding.recyclerNews.adapter = NewsAdapter(data,this@NewsActivity, object : NewsAdapter.OnClickListener{
                            override fun detail(item: ArticlesItem?) {
                                val intent = Intent(this@NewsActivity, DetailNewsActivity::class.java)
                                intent.putExtra("DATA", item)
                                startActivity(intent)
                            }

                        })
                    }
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@NewsActivity,"Gagal get data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun showDataKesehatan() {
        ConfigNetwork.service().getKesehatan().enqueue(object: Callback<ResponseNews>{
            override fun onResponse(
                call: Call<ResponseNews>,
                response: Response<ResponseNews>
            ) {
                if (response.isSuccessful){

                    pb.visibility = View.GONE
                    val status = response.body()?.status

                    if (status == "ok"){
                        val data = response.body()?.articles

                        binding.recyclerNews.adapter = NewsAdapter(data,this@NewsActivity, object : NewsAdapter.OnClickListener{
                            override fun detail(item: ArticlesItem?) {
                                val intent = Intent(this@NewsActivity, DetailNewsActivity::class.java)
                                intent.putExtra("DATA", item)
                                startActivity(intent)
                            }
                        })
                    }
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@NewsActivity,"Gagal get data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun isConnect(): Boolean {
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return  connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}
