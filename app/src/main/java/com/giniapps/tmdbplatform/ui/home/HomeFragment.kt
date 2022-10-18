package com.giniapps.tmdbplatform.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.SharedViewmodel
import com.giniapps.tmdbplatform.databinding.FragmentHomeBinding
import com.giniapps.tmdbplatform.model.response.Media
import com.giniapps.tmdbplatform.ui.media.MediaFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val sharedViewmodel: SharedViewmodel by activityViewModels()
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        
        initRecyclerview(homeViewModel)

        homeViewModel.getMovies()

        return root
    }

    private fun initRecyclerview(homeViewModel: HomeViewModel) {
        val adapter = homeViewModel.getAdapter()

        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.categoryRecyclerview.adapter = adapter
        binding.categoryRecyclerview.recycledViewPool
        adapter.onParentItemClickListener = CategoryAdapter.OnParentItemClickListener(::action)

    }


    private fun action(tmdbItem: Media) {
        findNavController().navigate(R.id.navigation_media, MediaFragmentArgs(tmdbItem).toBundle())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}