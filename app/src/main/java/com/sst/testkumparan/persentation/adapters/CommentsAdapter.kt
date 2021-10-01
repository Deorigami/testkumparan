package com.sst.testkumparan.persentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sst.testkumparan.databinding.ItemCommentBinding
import com.sst.testkumparan.databinding.ItemPostBinding
import com.sst.testkumparan.domain.models.Comment

class CommentsAdapter(val comments : List<Comment>): RecyclerView.Adapter<CommentsAdapter.CommentsVH>() {

    lateinit var handler : EventHandler

    interface EventHandler {
        fun init(binding : ItemCommentBinding, data : Comment)
    }

    class CommentsVH (val binding: ItemCommentBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsVH {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CommentsVH(binding)
    }

    override fun onBindViewHolder(holder: CommentsVH, position: Int) {
        handler.init(holder.binding, comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }



}
