package com.sst.testkumparan.persentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.sst.testkumparan.R
import com.sst.testkumparan.databinding.FragmentUserDetailBinding
import com.sst.testkumparan.databinding.ItemAlbumBinding
import com.sst.testkumparan.databinding.ItemPhotoBinding
import com.sst.testkumparan.domain.models.Album
import com.sst.testkumparan.domain.models.Photo
import com.sst.testkumparan.persentation.adapters.AlbumAdapter
import com.sst.testkumparan.persentation.adapters.PhotoAdapter
import com.sst.testkumparan.persentation.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    lateinit var binding : FragmentUserDetailBinding
    lateinit var albumAdapter: AlbumAdapter
    lateinit var listAlbum : MutableList<Album>

    private val userVM : UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        listAlbum = mutableListOf()
        albumAdapter = AlbumAdapter(listAlbum)

        userVM.userState.observe(viewLifecycleOwner,{
            binding.userName.text = it.users?.username
            binding.userEmail.text = it.users?.email
            binding.userAddress.text = it.users?.address
            binding.userCompany.text = it.users?.company

            it.users?.albums?.let { albums ->
                listAlbum.addAll(albums)
                albumAdapter.notifyItemChanged(0)
            }


        })

        initAlbumRV()
        return binding.root
    }

    private fun initAlbumRV() {
        albumAdapter.eventHandler = object : AlbumAdapter.EventHandler {
            override fun init(binding: ItemAlbumBinding, data: Album) {
                binding.albumTitle.text = data.title
                initPhotoRV(binding, data.photos)
            }

        }

        binding.albumRv.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initPhotoRV(itemAlbumBinding: ItemAlbumBinding, photos: List<Photo>?) {
        val photoAdapter = PhotoAdapter(photos).apply {
            eventHandler = object : PhotoAdapter.EventHandler {
                override fun init(binding: ItemPhotoBinding, data: Photo) {
                    Picasso.get()
                        .load(data.thumbnailUrl)
                        .fit()
                        .noFade()
                        .placeholder(R.drawable.progress_animation)
                        .into(binding.photo)
                    binding.root.setOnClickListener { findNavController().navigate(UserDetailFragmentDirections.actionUserDetailFragmentToPhotoDetailFragment(data)) }
                }

            }
        }

        itemAlbumBinding.photosRv.apply {
            adapter = photoAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }


}