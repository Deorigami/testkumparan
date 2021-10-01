package com.sst.testkumparan.persentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sst.testkumparan.R
import com.sst.testkumparan.databinding.FragmentPostDetailBinding
import com.sst.testkumparan.databinding.ItemCommentBinding
import com.sst.testkumparan.domain.models.Comment
import com.sst.testkumparan.domain.models.Post
import com.sst.testkumparan.persentation.adapters.CommentsAdapter
import com.sst.testkumparan.persentation.adapters.ListPostAdapter
import com.sst.testkumparan.persentation.viewmodels.UserViewModel

class PostDetailFragment : Fragment() {

    lateinit var binding : FragmentPostDetailBinding
    private val args : PostDetailFragmentArgs by navArgs()
    private val userVM : UserViewModel by activityViewModels()

    lateinit var inclBody : TextView
    lateinit var inclCompany : TextView
    lateinit var inclTitle : TextView
    lateinit var inclUserName : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container,false)
        inclTitle = binding.includedPost.postTitle
        inclCompany = binding.includedPost.userCompanyName
        inclUserName = binding.includedPost.userName
        inclBody = binding.includedPost.postBody


        initIncluded(args.post)
        initComments(args.post)
        return binding.root
    }

    private fun initComments(post: Post) {
        post.comments?.let {comments ->
            val adapter = CommentsAdapter(comments)
            adapter.handler = object : CommentsAdapter.EventHandler {
                override fun init(binding: ItemCommentBinding, data: Comment) {
                    binding.commentAuthor.text = data.email
                    binding.commentBody.text = data.body

                }

            }
            binding.commentsRv.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun initIncluded(post: Post) {
        inclBody.text = post.body
        inclCompany.text = post.user?.company
        inclTitle.text = post.title
        inclUserName.apply {
            text = post.user?.username
            setOnClickListener {
                post.user?.let {
                    userVM.getUser(it.id)
                    findNavController().navigate(PostDetailFragmentDirections.actionPostDetailFragmentToUserDetailFragment())
                }
            }
        }
    }

}