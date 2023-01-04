package uz.akfadiler.testappaliftech.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.akfadiler.testappaliftech.R
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.databinding.ItemTodosBinding

class TodosAdapter : ListAdapter<TodosResponse, TodosAdapter.TodosViewHolder>(TodosDiffUtils) {

    inner class TodosViewHolder(private val binding: ItemTodosBinding) : ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val model = getItem(absoluteAdapterPosition)
            model.title?.let {
                title.text = it
            }
            model.completed?.let {
                if (it) {
                    tvType.text = root.context.getString(R.string.completed)
                    tvType.setBackgroundResource(R.drawable.bg_status_complete)
                } else {
                    tvType.text = root.context.getString(R.string.not_completed)
                    tvType.setBackgroundResource(R.drawable.bg_status_not_complete)
                }
            }
        }
    }

    private object TodosDiffUtils : DiffUtil.ItemCallback<TodosResponse>() {
        override fun areItemsTheSame(oldItem: TodosResponse, newItem: TodosResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodosResponse, newItem: TodosResponse): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        return TodosViewHolder(
            ItemTodosBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        holder.bind()
    }
}