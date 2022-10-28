package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.source.local.entity.ArticleEntity
import com.example.myapplication.databinding.ItemListBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.HomeViewHolder>() {

    private var listNews = ArrayList<ArticleEntity>()

    fun setNews(news: List<ArticleEntity>?) {
        if (news == null) return
        this.listNews.clear()
        this.listNews.addAll(news)
    }

    class HomeViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: ArticleEntity) {
            with(binding) {
                tvItemTitle.text = news.title
                tvItemDate.text = news.publishedAt
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .centerCrop()
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemNewsBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemNewsBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val news = listNews[position]
        holder.bind(news)
    }

    override fun getItemCount() = listNews.size

}