package com.news.ace_newsapp.ui.saved_news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.ace_newsapp.data.model.NewsData
import com.news.ace_newsapp.databinding.FragmentSavedNewsBinding
import com.news.ace_newsapp.ui.MainViewModel
import com.news.ace_newsapp.ui.details.DetailsActivity
import com.news.ace_newsapp.ui.home.adapter.TopHeadlineAdapter

class SavedNewsFragment : Fragment(),TopHeadlineAdapter.NewsItemClickListener {

    private var _binding: FragmentSavedNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var savedAdapter: TopHeadlineAdapter
    private lateinit var savedList: MutableList<NewsData>
    private lateinit var manager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        savedList = mutableListOf()

        setupUI()
        observe()



        return root
    }

    private fun observe() {
        viewModel.savedNewList.observe(this, Observer {
            if(it.isNullOrEmpty()){
                binding.tvNoData.visibility = View.VISIBLE
            }else{
                binding.tvNoData.visibility = View.GONE
//                savedList.clear()
//                savedList = it as MutableList<NewsData>
//                savedAdapter = TopHeadlineAdapter(it as MutableList<NewsData>,this)
//                savedAdapter.notifyDataSetChanged()
                savedList = it as MutableList<NewsData>
                Log.i("saved_list","Size is : ${savedList.size}")
                setupUI()
                savedAdapter.notifyDataSetChanged()
            }

        })
    }

    private fun setupUI() {
        manager = LinearLayoutManager(requireContext())
        savedAdapter = TopHeadlineAdapter(savedList,this)
        binding.rvSavedNews.apply {
            layoutManager = manager
            adapter = savedAdapter
        }
        savedAdapter.notifyDataSetChanged()

        savedAdapter.setOnItemClickListener {news ->
            val intent = Intent(requireActivity(), DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("news", news)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveItemClick(data: NewsData) {

    }
}