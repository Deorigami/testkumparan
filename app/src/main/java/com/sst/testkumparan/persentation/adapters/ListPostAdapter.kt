package com.sst.testkumparan.persentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sst.testkumparan.databinding.ItemPostBinding
import com.sst.testkumparan.domain.models.Post

class ListPostAdapter(val posts : List<Post>) : RecyclerView.Adapter<ListPostAdapter.ListPostVH>() {

    lateinit var eventHandler: EventHandler

    class ListPostVH(val binding : ItemPostBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPostVH {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListPostVH(binding)
    }

    override fun onBindViewHolder(holder: ListPostVH, position: Int) {
        eventHandler.initHandler(holder.binding, posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    interface EventHandler{
        fun initHandler(binding: ItemPostBinding, data : Post)
    }

}
