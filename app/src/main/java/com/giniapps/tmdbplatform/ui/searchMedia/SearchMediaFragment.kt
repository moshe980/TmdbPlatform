package com.giniapps.tmdbplatform.ui.searchMedia

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.SharedViewmodel
import com.giniapps.tmdbplatform.databinding.FragmentSearchMediaBinding
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.giniapps.tmdbplatform.ui.media.MediaFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMediaFragment : Fragment(), SearchView.OnQueryTextListener {
    private val sharedViewmodel: SharedViewmodel by activityViewModels()

    private var _binding: FragmentSearchMediaBinding? = null
    private lateinit var searchMediaViewModel: SearchMediaViewModel
    lateinit var root: View
    var queryTextChangedJob: Job? = null

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

        searchMediaViewModel = ViewModelProvider(this).get(SearchMediaViewModel::class.java)

        initRecyclerview(searchMediaViewModel)

        setHasOptionsMenu(true)
        return root
    }

    private fun initRecyclerview(dashboardViewModel: SearchMediaViewModel) {
        val adapter = dashboardViewModel.getAdapter()
        adapter.onItemClickListener = MediaInfoAdapter.OnItemClickListener(::action)
        binding.searchRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.searchRecyclerView.adapter = adapter
    }

    private fun action(item: TmdbItem) {
        findNavController().navigate(R.id.navigation_media, MediaFragmentArgs(item).toBundle())
    }

    private fun tvShowsSearchChecked(query: String) {
        searchMediaViewModel.getSeriesFromApi(query)
    }

    private fun movieSearchChecked(query: String) {
        searchMediaViewModel.getMoviesFromApi(query)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            root.hideKeyboard()
            search(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            search(query)
        }
        return true
    }

    private fun search(query: String) {
        val searchQuery = query
        if (binding.chipMovies.isChecked) {
            movieSearchChecked(searchQuery)
        } else if (binding.chipSeries.isChecked) {
            tvShowsSearchChecked(searchQuery)

        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}