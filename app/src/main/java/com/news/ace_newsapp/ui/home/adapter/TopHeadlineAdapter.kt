package com.news.ace_newsapp.ui.home.adapter

import android.net.Uri
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.ace_newsapp.R
import com.news.ace_newsapp.data.model.NewsData
import com.news.ace_newsapp.databinding.TopHeadlineItemLayoutBinding
import com.news.ace_newsapp.utils.localDateFormat

class TopHeadlineAdapter(
    private val articleList: MutableList<NewsData>,
     val clickListener: TopHeadlineAdapter.NewsItemClickListener
) : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {

    private var onItemClickListener: ((NewsData) -> Unit)? = null

    inner class DataViewHolder(val binding: TopHeadlineItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(article: NewsData) {
            binding.textViewTitle.text = article.title
            binding.textViewSource.text = localDateFormat(article.publishedAt)
//            binding.textViewSource.text = article.source?.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.urlToImage)
                .into(binding.imageViewBanner)

            binding.ivSave.apply {
                setOnClickListener {
                    clickListener.onSaveItemClick(article)
                }
            }

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val article = articleList[position]
        holder.bind(article)
//        with(holder) {
//            binding.textViewTitle.text = article.title
//            binding.textViewSource.text = localDateFormat(article.publishedAt)
////            binding.textViewSource.text = article.source?.name
//            Glide.with(binding.imageViewBanner.context)
//                .load(article.urlToImage)
//                .into(binding.imageViewBanner)
//
//            binding.ivSave.apply {
//                setOnClickListener {
//                    clickListener.onSaveItemClick(article)
//                }
//            }
//        }
//
//        holder.itemView.apply {
//            setOnClickListener {
//                onItemClickListener?.let {
//                    it(article)
//                }
//            }
//        }

    }

    fun addData(list: List<NewsData>) {
        articleList.addAll(list)
    }

    fun setOnItemClickListener(listener: (NewsData) -> Unit) {
        onItemClickListener = listener
    }

    interface NewsItemClickListener {
        fun onSaveItemClick(data: NewsData)
    }

}