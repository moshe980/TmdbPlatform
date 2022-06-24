package com.giniapps.tmdbplatform.ui.media

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.SharedViewmodel
import com.giniapps.tmdbplatform.databinding.FragmentMediaBinding
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaFragment : Fragment() {
    private var _binding: FragmentMediaBinding? = null
    private val sharedViewmodel: SharedViewmodel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val args: MediaFragmentArgs by navArgs()
        val currentMedia = args.media

        val mediaViewModel =
            ViewModelProvider(this)[MediaViewModel::class.java]

        mediaViewModel.apply {
            getVideo(currentMedia)
            getCasts(currentMedia)
            setMedia(currentMedia)
        }

        initCastRecycleView(mediaViewModel)

        setMediaPoster(currentMedia)

        binding.collapsingToolBar.title = currentMedia.name
        binding.ratingTV.text = currentMedia.voteAverage.toString()
        binding.descriptionTV.text = currentMedia.overview
        binding.dateTV.text = "Release date: ${currentMedia.releaseDate}"

        lifecycle.addObserver(binding.youtubePlayerView)

        favoritToggle(currentMedia)


        binding.favoritToggle.setOnClickListener {
            onToggleTapped(it, currentMedia)
        }

        mediaViewModel.video.observe(viewLifecycleOwner) {
            binding.youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = it.videos[0].videoId
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

        }


        onBackTapped()



        return root
    }

    private fun favoritToggle(currentMedia: TmdbItem) {
        if (sharedViewmodel.favoriteList.value?.contains(currentMedia) == true) {
            binding.favoritToggle.isChecked = true
        }
    }

    private fun onToggleTapped(
        it: View?,
        currentMedia: TmdbItem
    ) {
        with(it as ToggleButton) {
            if (it.isChecked) {
                sharedViewmodel.addFavorite(currentMedia)
            } else {
                sharedViewmodel.removeFavorite(currentMedia)
            }
        }
    }

    private fun onBackTapped() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setMediaPoster(currentMedia: TmdbItem) {
        Glide.with(binding.mediaImageView)
            .load(Uri.parse(currentMedia.getImageUrl()))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.mediaImageView)
    }

    private fun initCastRecycleView(mediaViewModel: MediaViewModel) {
        binding.casts.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = mediaViewModel.getCastsAdapter()
        binding.casts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}