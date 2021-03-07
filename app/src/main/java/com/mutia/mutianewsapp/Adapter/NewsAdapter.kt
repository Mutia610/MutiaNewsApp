package com.mutia.mutianewsapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mutia.mutianewsapp.Model.ArticlesItem
import com.mutia.mutianewsapp.databinding.ItemBeritaBinding

class NewsAdapter(val data: List<ArticlesItem?>?, var c: Context, var itemClick: OnClickListener): RecyclerView.Adapter<NewsAdapter.TeknologiHolder>() {

    class TeknologiHolder(var binding: ItemBeritaBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ArticlesItem?, c: Context){
            binding.tittleNews.text = data?.title
            binding.txtDescription.text = data?.description
            binding.txtSource.text = data?.source?.name
            binding.txtPublished.text = data?.publishedAt
            Glide.with(c).load(data?.urlToImage).into(binding.imgNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeknologiHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsAdapter.TeknologiHolder(binding)
    }

    override fun onBindViewHolder(holder: TeknologiHolder, position: Int) {
        holder.bind(data?.get(position),c)

        holder.itemView.setOnClickListener {
            itemClick.detail(data?.get(position))
        }
    }

    override fun getItemCount() = data?.size ?: 0

    interface OnClickListener{
        fun detail(item : ArticlesItem?)
    }
}