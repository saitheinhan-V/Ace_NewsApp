package com.news.ace_newsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.ace_newsapp.data.model.NewsData
import com.news.ace_newsapp.data.model.NewsResponse
import com.news.ace_newsapp.databinding.FragmentHomeBinding
import com.news.ace_newsapp.network.Resource
import com.news.ace_newsapp.ui.MainViewModel
import com.news.ace_newsapp.ui.details.DetailsActivity
import com.news.ace_newsapp.ui.home.adapter.TopHeadlineAdapter
import com.news.ace_newsapp.utils.AppConstant

class HomeFragment : Fragment(),TopHeadlineAdapter.NewsItemClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var pageNumber: Int = 1
    private lateinit var articleList: MutableList<NewsData>
    private lateinit var manager: LinearLayoutManager
    private var totalItems: Int = 0
    private var scrollOutItems: Int = 0
    private var isScrolling: Boolean = false
    private var currentItems = 0
    private lateinit var topHeadlineAdapter: TopHeadlineAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        fetchNews(pageNumber)
    }

    private fun setUpUI() {
        articleList = arrayListOf()
        manager = LinearLayoutManager(requireContext())
        topHeadlineAdapter = TopHeadlineAdapter(articleList,this)
        binding.rvNews.apply {
            layoutManager = manager
            adapter = topHeadlineAdapter
        }

        topHeadlineAdapter.setOnItemClickListener { news ->
//            val bundle = Bundle().apply {
//                putSerializable("news", news)
//            }
//            findNavController().navigate(
//                R.id.action_navigation_top_headline_to_navigation_news_detail,
//                bundle
//            )
            val intent = Intent(requireActivity(),DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("news", news)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrollOutItems = manager.findFirstVisibleItemPosition()
                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false
                    if (articleList.size % AppConstant.PAGE_SIZE == 0) {
                        pageNumber += 1
                        fetchNews(pageNumber)
                    }
                }
            }
        })
    }

    private fun fetchNews(pageSize: Int) {
        viewModel.getNew(pageSize).observe(this, Observer {
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE

                    setContentView(it.data!!)
                }
            }
        })
    }

    private fun setContentView(data: NewsResponse) {
        articleList.addAll(data.articles)
        topHeadlineAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onSaveItemClick(data: NewsData) {
        viewModel.saveNews(data)
        Toast.makeText(requireActivity(), "Successfully saved!", Toast.LENGTH_SHORT).show()

    }
}