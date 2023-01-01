package uz.akfadiler.testappaliftech.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.akfadiler.testappaliftech.data.remote.response.user.GeoUser
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse
import uz.akfadiler.testappaliftech.databinding.ItemUsersBinding

class UsersAdapter : ListAdapter<UserResponse, UsersAdapter.UsersViewHolder>(UsersDiffUtils) {

    private var onPostsClickListener: ((Int) -> Unit)? = null
    private var onLocationClickListener: ((GeoUser) -> Unit)? = null
    private var onPhoneClickListener: ((String) -> Unit)? = null

    inner class UsersViewHolder(private val binding: ItemUsersBinding) : ViewHolder(binding.root) {

        init {
            binding.btnLocation.setOnClickListener {
                getItem(absoluteAdapterPosition).address?.geo?.let {
                    onLocationClickListener?.invoke(it)
                }
            }
            binding.btnCall.setOnClickListener {
                getItem(absoluteAdapterPosition).phone?.let {
                    onPhoneClickListener?.invoke(it)
                }
            }
            binding.btnPosts.setOnClickListener {
                getItem(absoluteAdapterPosition).id?.let {
                    onPostsClickListener?.invoke(it)
                }
            }
        }

        fun bind(): Unit = with(binding) {
            val model: UserResponse = getItem(absoluteAdapterPosition)
            model.name?.let {
                nameDescription.text = it
            }
            model.company?.let {
                companyDescription.text = it.name
            }
            model.username?.let {
                usernameDescription.text = it
            }
            model.email?.let {
                emailDescription.text = it
            }
            model.website?.let {
                webSiteDescription.text = it
            }
        }
    }

    private object UsersDiffUtils : DiffUtil.ItemCallback<UserResponse>() {
        override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnPostsClickListener(block: (Int) -> Unit) {
        onPostsClickListener = block
    }

    fun setOnLocationClickListener(block: (GeoUser) -> Unit) {
        onLocationClickListener = block
    }

    fun setOnPhoneClickListener(block: (String) -> Unit) {
        onPhoneClickListener = block
    }
}