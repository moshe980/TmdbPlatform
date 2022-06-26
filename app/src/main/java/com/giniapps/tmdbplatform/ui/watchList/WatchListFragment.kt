package com.giniapps.tmdbplatform.ui.watchList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.SharedViewmodel
import com.giniapps.tmdbplatform.databinding.FragmentWatchListBinding
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.giniapps.tmdbplatform.ui.media.MediaFragmentArgs
import com.giniapps.tmdbplatform.ui.searchMedia.MediaInfoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private var _binding: FragmentWatchListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewmodel: SharedViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val watchListViewModel =
            ViewModelProvider(this).get(WatchListViewModel::class.java)

        _binding = FragmentWatchListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerview(watchListViewModel)

        watchListViewModel.setData()



        return root
    }

    private fun initRecyclerview(watchListViewModel: WatchListViewModel) {
        val adapter = watchListViewModel.mediaInfoAdapter
        adapter.onItemClickListener = MediaInfoAdapter.OnItemClickListener(::action)
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.favoritesRecyclerView.adapter = adapter

    }

    private fun action(item: TmdbItem) {
        findNavController().navigate(R.id.navigation_media, MediaFragmentArgs(item).toBundle())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}