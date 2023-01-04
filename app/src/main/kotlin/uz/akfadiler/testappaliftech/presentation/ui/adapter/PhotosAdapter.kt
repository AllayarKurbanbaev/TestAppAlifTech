package uz.akfadiler.testappaliftech.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.databinding.ItemPhotosBinding


class PhotosAdapter : ListAdapter<PhotosResponse, PhotosAdapter.PhotosViewHolder>(PhotosDiffUtils) {

    private var onDownloadClickListener: ((PhotosResponse) -> Unit)? = null

    inner class PhotosViewHolder(private val binding: ItemPhotosBinding) :
        ViewHolder(binding.root) {

        init {
            binding.btnDownload.setOnClickListener {
                onDownloadClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind(): Unit = with(binding) {
            val model = getItem(absoluteAdapterPosition)
            model.title?.let {
                title.text = it
            }
            model.thumbnailUrl?.let {
                val url = GlideUrl(
                    it, LazyHeaders.Builder().addHeader("User-Agent", "your-user-agent").build()
                )
                Glide.with(root.context).load(url).centerCrop().into(imageView)
            }
        }
    }

    private object PhotosDiffUtils : DiffUtil.ItemCallback<PhotosResponse>() {
        override fun areItemsTheSame(oldItem: PhotosResponse, newItem: PhotosResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotosResponse, newItem: PhotosResponse): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemPhotosBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnDownloadClickListener(block: (PhotosResponse) -> Unit) {
        onDownloadClickListener = block
    }
}