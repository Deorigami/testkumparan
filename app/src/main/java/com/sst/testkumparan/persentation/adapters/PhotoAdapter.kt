package com.sst.testkumparan.persentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sst.testkumparan.databinding.ItemPhotoBinding
import com.sst.testkumparan.domain.models.Photo

class PhotoAdapter(val photos: List<Photo>?) : RecyclerView.Adapter<PhotoAdapter.PhotoVH>() {
    class PhotoVH(val binding : ItemPhotoBinding):RecyclerView.ViewHolder(binding.root)
    lateinit var eventHandler: EventHandler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoVH(binding)
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        photos?.get(position)?.let { eventHandler.init(holder.binding, it) }
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    interface EventHandler{
        fun init(binding: ItemPhotoBinding, data: Photo)
    }

}
