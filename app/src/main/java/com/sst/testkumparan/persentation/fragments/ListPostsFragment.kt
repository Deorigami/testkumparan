package com.sst.testkumparan.persentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sst.testkumparan.databinding.FragmentListPostsBinding
import com.sst.testkumparan.databinding.ItemPostBinding
import com.sst.testkumparan.domain.models.Post
import com.sst.testkumparan.persentation.adapters.ListPostAdapter
import com.sst.testkumparan.persentation.viewmodels.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPostsFragment : Fragment() {
    lateinit var binding : FragmentListPostsBinding
    private val postVM : PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPostsBinding.inflate(inflater, container, false)


        postVM.postsState.observe(viewLifecycleOwner,{ postState ->
            binding.imBadRequest.visibility = if (postState.error == "") View.GONE else View.VISIBLE
            binding.swipeRefresh.isRefreshing = postState.isLoading
            initRV(postState.posts)
        })

        binding.swipeRefresh.setOnRefreshListener {
            postVM.getPosts()
        }


        return binding.root
    }

    private fun initRV(postList: List<Post>) {
        val adapter = ListPostAdapter(postList)

        adapter.eventHandler = object : ListPostAdapter.EventHandler {
            override fun initHandler(binding: ItemPostBinding, data: Post) {
                binding.userName.text = data.user?.username
                binding.userCompanyName.text = data.user?.company
                binding.postTitle.text = data.title
                binding.postBody.text = data.body
                binding.root.setOnClickListener {
                    findNavController().navigate(ListPostsFragmentDirections.actionListPostsFragmentToPostDetailFragment(data))
                }
            }
        }
        binding.postsRv.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}