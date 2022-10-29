package com.example.myapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.data.source.local.entity.ArticleEntity
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.ui.home.HomeViewModel
import com.example.myapplication.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val NEWS_DATA = "news_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showData()
    }

    private fun showData() {
        val data = intent.getParcelableExtra<ArticleEntity>(NEWS_DATA) as ArticleEntity
        val state = data.bookmarked
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = data.title
        }
        binding.tvTitleDetail.text = data.title
        binding.tvDescriptionDetail.text = data.description
        binding.tvAuthorDetail.text = data.author
        binding.tvDateDetail.text = data.publishedAt?.substring(0, 10)
        Glide.with(this)
            .load(data.urlToImage)
            .centerCrop()
            .into(binding.imgView)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        state?.let { setButtonBookmarked(it) }

        binding.fbtnBookmarked.setOnClickListener {
            viewModel.setBookmarkedNews(data, false)
            setButtonBookmarked(false)
        }
        binding.fbtnUnbookmarked.setOnClickListener {
            viewModel.setBookmarkedNews(data, true)
            setButtonBookmarked(true)
        }

    }

    private fun setButtonBookmarked(state: Boolean){
        if (state) {
            binding.fbtnUnbookmarked.hide()
            binding.fbtnBookmarked.show()
        }else{
            binding.fbtnBookmarked.hide()
            binding.fbtnUnbookmarked.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}