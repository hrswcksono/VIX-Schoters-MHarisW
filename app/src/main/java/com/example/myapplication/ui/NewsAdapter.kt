package com.example.myapplication.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.source.local.entity.ArticleEntity
import com.example.myapplication.databinding.ItemListBinding
import com.example.myapplication.ui.detail.DetailActivity

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var listNews = ArrayList<ArticleEntity>()

    fun setNews(news: List<ArticleEntity>?) {
        if (news == null) return
        this.listNews.clear()
        this.listNews.addAll(news)
    }

    class NewsViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: ArticleEntity) {
            with(binding) {
                tvItemTitle.text = news.title
                tvItemDate.text = news.publishedAt?.substring(0, 10)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NEWS_DATA, news)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .centerCrop()
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemNewsBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = listNews[position]
        holder.bind(news)
    }

    override fun getItemCount() = listNews.size

}