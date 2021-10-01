package com.sst.testkumparan.persentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.sst.testkumparan.R
import com.sst.testkumparan.databinding.FragmentPhotoDetailBinding


class PhotoDetailFragment : Fragment() {
    lateinit var binding: FragmentPhotoDetailBinding
    private val args : PhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        args.photo.let {
            binding.photoName.text = it.title
            Picasso.get().load(it.url).placeholder(R.drawable.progress_animation).into(binding.photo)
        }

        return binding.root
    }


}