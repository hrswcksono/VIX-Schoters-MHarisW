package com.example.myapplication.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBookmarkBinding
import com.example.myapplication.ui.NewsAdapter
import com.example.myapplication.viewmodel.ViewModelFactory
import com.example.myapplication.vo.Resource

class BookmarkFragment : Fragment() {

    private var _fragmentBookmarkBinding: FragmentBookmarkBinding? = null
    private val binding get() = _fragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

            val newsAdapter = NewsAdapter()
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getBookmarkedNews().observe(viewLifecycleOwner) { news ->
                binding?.progressBar?.visibility = View.GONE
                newsAdapter.setNews(news)
                newsAdapter.notifyDataSetChanged()
            }

            with(binding?.rvBookmark) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = newsAdapter
            }
        }
    }

}