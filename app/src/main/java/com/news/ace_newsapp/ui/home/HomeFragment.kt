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
import com.google.android.material.tabs.TabLayout
import com.news.ace_newsapp.R
import com.news.ace_newsapp.data.model.NewsData
import com.news.ace_newsapp.data.model.NewsResponse
import com.news.ace_newsapp.databinding.FragmentHomeBinding
import com.news.ace_newsapp.network.Resource
import com.news.ace_newsapp.ui.MainViewModel
import com.news.ace_newsapp.ui.details.DetailsActivity
import com.news.ace_newsapp.ui.home.adapter.TopHeadlineAdapter
import com.news.ace_newsapp.ui.home.adapter.ViewPagerAdapter
import com.news.ace_newsapp.utils.AppConstant

class HomeFragment : Fragment() {

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

        setupTabLayout()
        setupViewPager()


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = ViewPagerAdapter(parentFragmentManager, binding.tabLayout.tabCount)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        }
    }

    private fun setupTabLayout() {
        val titleArray = resources.getStringArray(R.array.news_category)
        binding.tabLayout.apply {
            for(title in titleArray){
                addTab(this.newTab().setText(title))
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        binding.viewPager.currentItem = it
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}