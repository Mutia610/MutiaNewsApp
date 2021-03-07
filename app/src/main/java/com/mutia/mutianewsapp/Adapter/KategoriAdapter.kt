package com.mutia.mutianewsapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mutia.mutianewsapp.Model.ArticlesItem
import com.mutia.mutianewsapp.Model.NewsCategory
import com.mutia.mutianewsapp.databinding.ItemKategoriBinding
import java.util.ArrayList

class KategoriAdapter(val data: List<NewsCategory?>?, var itemClick: OnClickListener):RecyclerView.Adapter<KategoriAdapter.ViewHolder>(), Filterable {

    var itemKategoriFilter = ArrayList<NewsCategory>()
    var itemKategori = ArrayList<NewsCategory>()

    class ViewHolder(var binding: ItemKategoriBinding) : RecyclerView.ViewHolder(binding.root){
       fun bind(data: NewsCategory?){
           binding.txtNamaKategori.text = data?.categoryName
         //  Glide.with(this).load(data?.image).into(binding.imgKategori)
           data?.image?.let { binding.imgKategori.setImageResource(it) }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriAdapter.ViewHolder {
        val binding = ItemKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KategoriAdapter.ViewHolder, position: Int) {
        holder.bind(itemKategori?.get(position))

        holder.itemView.setOnClickListener {
            itemClick.detail(itemKategori?.get(position)?.categoryName)
        }
    }

    override fun getItemCount() = itemKategori?.size ?: 0

    interface OnClickListener{
        fun detail(categoryName: String?)
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }

    private val exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<NewsCategory> = java.util.ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(itemKategoriFilter)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in itemKategoriFilter) {
                    if (item.categoryName.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            itemKategori.clear()
            itemKategori.addAll(results.values as Collection<NewsCategory>)
            notifyDataSetChanged()
        }
    }

    init {
        this.itemKategori = data as java.util.ArrayList<NewsCategory>
        itemKategoriFilter = ArrayList(data)
    }
}



