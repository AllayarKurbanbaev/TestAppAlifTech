package uz.akfadiler.testappaliftech.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.akfadiler.testappaliftech.data.remote.response.posts.PostResponse
import uz.akfadiler.testappaliftech.databinding.ItemPostsBinding
import uz.akfadiler.testappaliftech.domain.model.PostsData

class PostsAdapter : ListAdapter<PostsData, PostsAdapter.PostsViewHolder>(PostsDiffUtils) {

    private var onItemLongClickListener: ((Int) -> Unit)? = null

    inner class PostsViewHolder(private val binding: ItemPostsBinding) : ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                onItemLongClickListener?.invoke(absoluteAdapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun bind() = with(binding) {
            val model = getItem(absoluteAdapterPosition)
            model.title?.let {
                title.text = "${absoluteAdapterPosition + 1}) ${it}"
            }
            model.body?.let {
                body.text = it
            }
        }
    }

    private object PostsDiffUtils : DiffUtil.ItemCallback<PostsData>() {
        override fun areItemsTheSame(oldItem: PostsData, newItem: PostsData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostsData, newItem: PostsData): Boolean {
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

    fun setOnItemLongClickListener(block: (Int) -> Unit) {
        onItemLongClickListener = block
    }
}