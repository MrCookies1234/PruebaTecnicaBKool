package com.example.pruebatecnicabkool.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pruebatecnicabkool.databinding.FragmentYoutubeBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubeFragment : Fragment() {

    private var _binding : FragmentYoutubeBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<YoutubeFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        _binding = FragmentYoutubeBinding.inflate(inflater)
        setUpPlayer()
        subscribeToObservables()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubeVideoPlayer.release()
        _binding = null
    }

    private fun setUpPlayer(){
         binding.youtubeVideoPlayer.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
             override fun onReady(youTubePlayer: YouTubePlayer) {
                 youTubePlayer.loadVideo(args.youtubeId, 0f)
             }
        })
    }

    private fun subscribeToObservables(){
        lifecycle.addObserver(binding.youtubeVideoPlayer)
    }

}