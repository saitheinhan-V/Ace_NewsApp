package com.news.ace_newsapp.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.news.ace_newsapp.ui.home.HeadlineFragment

class ViewPagerAdapter(fm: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HeadlineFragment.newInstance("business")
            1 -> HeadlineFragment.newInstance("entertainment")
            2 -> HeadlineFragment.newInstance("general")
            3 -> HeadlineFragment.newInstance("health")
            4 -> HeadlineFragment.newInstance("science")
            5 -> HeadlineFragment.newInstance("sports")
            6 -> HeadlineFragment.newInstance("technology")
            else -> HeadlineFragment.newInstance("business")
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Tab " + (position + 1)
    }
}
