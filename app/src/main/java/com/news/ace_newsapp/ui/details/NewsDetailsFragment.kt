package com.news.ace_newsapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.news.ace_newsapp.R
import com.news.ace_newsapp.databinding.FragmentNewsDetailsBinding
import com.news.ace_newsapp.ui.MainViewModel

class NewsDetailsFragment : Fragment() {

    companion object{
        fun newInstance() = NewsDetailsFragment()

    }

    private var _binding: FragmentNewsDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private val detailsViewModel: DetailsViewModel by activityViewModels()
//    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val onBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // Handle the back button event
////                findNavController().popBackStack()
////                findNavController().navigateUp()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,onBackPressedCallback)

//        val news = args.news
        val news = detailsViewModel.news
        binding.tvTitle.text = news.title
        binding.webView.apply {
            webViewClient = WebViewClient()
            news.url?.let {
                loadUrl(it)
            }
        }

        binding.ivBack.setOnClickListener {
            requireActivity().finish()
        }

        binding.fab.setOnClickListener {
            viewModel.saveNews(news)
            Toast.makeText(requireActivity(), "Successfully saved!", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}