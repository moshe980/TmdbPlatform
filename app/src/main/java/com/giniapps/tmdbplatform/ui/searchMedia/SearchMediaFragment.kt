package com.giniapps.tmdbplatform.ui.searchMedia

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.databinding.FragmentSearchMediaBinding
import com.giniapps.tmdbplatform.model.response.Media
import com.giniapps.tmdbplatform.ui.media.MediaFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class SearchMediaFragment : Fragment(), MediaInfoAdapter.OnItemClickListener {

    private var _binding: FragmentSearchMediaBinding? = null
    private lateinit var searchMediaViewModel: SearchMediaViewModel
    private lateinit var root: View

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchMediaBinding.inflate(inflater, container, false)
        root = binding.root

        searchMediaViewModel = ViewModelProvider(this)[SearchMediaViewModel::class.java]

        val adapter = MediaInfoAdapter(this)
        val spanSize = 2
        val glm = GridLayoutManager(activity, spanSize)
        glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                when (position) {
                    adapter.itemCount -> spanSize
                    else -> 1
                }


        }
        binding.apply {
            searchRecyclerView.layoutManager = glm
            searchRecyclerView.itemAnimator = null
            searchRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MediaInfoLoadStateAdapter { adapter.retry() },
                footer = MediaInfoLoadStateAdapter { adapter.retry() }
            )

            retryBtn.setOnClickListener {
                adapter.retry()
            }
        }



        searchMediaViewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                searchRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryBtn.isVisible = loadState.source.refresh is LoadState.Error
                errorTv.isVisible = loadState.source.refresh is LoadState.Error

                //empty view
                if (loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
                    && adapter.itemCount < 1
                ) {
                    searchRecyclerView.isVisible = false
                    noResultsTv.isVisible = true
                } else {
                    noResultsTv.isVisible = false

                }
            }
        }




        setHasOptionsMenu(true)

        return root
    }


    private fun action(item: Media) {
        findNavController().navigate(R.id.navigation_media, MediaFragmentArgs(item).toBundle())
    }

    /*  private fun tvShowsSearchChecked(query: String) {
          searchMediaViewModel.getSeries(query)
      }*/

    private fun movieSearchChecked(query: String) {
        //   searchMediaViewModel.getMovies(query)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchView.clearFocus()
                    binding.searchRecyclerView.scrollToPosition(0)
                    searchMediaViewModel.searchMovies(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    binding.searchRecyclerView.scrollToPosition(0)
                    searchMediaViewModel.searchMovies(query)
                }
                return true
            }

        })
    }


    private fun search(query: String) {
        val searchQuery = query
        if (binding.chipMovies.isChecked) {

        } else if (binding.chipSeries.isChecked) {
            //   tvShowsSearchChecked(searchQuery)

        }
    }

    /*  private fun View.hideKeyboard() {
          val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
          imm.hideSoftInputFromWindow(windowToken, 0)
      }
  */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun callback(item: Media) {
        action(item)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }


}