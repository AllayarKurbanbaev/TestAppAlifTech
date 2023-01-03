package uz.akfadiler.testappaliftech.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.databinding.ItemPostsBinding

class PostsAdapter : ListAdapter<PostResponse, PostsAdapter.PostsViewHolder>(PostsDiffUtils) {

    inner class PostsViewHolder(private val binding: ItemPostsBinding) : ViewHolder(binding.root) {

        fun bind() = with(binding) {
            val model = getItem(absoluteAdapterPosition)
            title.text = "${absoluteAdapterPosition+1}) ${model.title}"
            body.text = model.body
        }
    }

    private object PostsDiffUtils : DiffUtil.ItemCallback<PostResponse>() {
        override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            ItemPostsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind()
    }
}