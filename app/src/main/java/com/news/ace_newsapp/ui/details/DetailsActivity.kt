package com.news.ace_newsapp.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.news.ace_newsapp.R
import com.news.ace_newsapp.data.model.NewsData
import com.news.ace_newsapp.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailsBinding.inflate(layoutInflater)

//        val fragment = NewsDetailsFragment
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(binding.frameLayout,fragment)

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, NewsDetailsFragment())
                .commitAllowingStateLoss()
        }

        val intent = this.intent
        val bundle = intent.extras
//
        if(bundle != null){
            val data = bundle.getSerializable("news") as NewsData
//            val data = intent.getSerializableExtra("news") as NewsData
            viewModel.news = data
        }

        setContentView(binding.root)
    }

}