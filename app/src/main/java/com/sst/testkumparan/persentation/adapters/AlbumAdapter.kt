package com.sst.testkumparan.persentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sst.testkumparan.databinding.ItemAlbumBinding
import com.sst.testkumparan.databinding.ItemPostBinding
import com.sst.testkumparan.domain.models.Album

class AlbumAdapter(val listAlbum: MutableList<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumVH>() {

    lateinit var eventHandler : EventHandler

    class AlbumVH (val binding : ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AlbumVH(binding)
    }

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        eventHandler.init(holder.binding, listAlbum[position])
    }

    override fun getItemCount(): Int {
        return listAlbum.size
    }

    interface EventHandler{
        fun init(binding : ItemAlbumBinding, data : Album)
    }

}
