package uz.akfadiler.testappaliftech.data.local.database.todos

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.akfadiler.testappaliftech.domain.model.TodosData

@Entity
data class TodosEntity(
    @PrimaryKey
    val id: Int?,
    val userId: Int?,
    val title: String?,
    val completed: Boolean?
)

fun TodosEntity.toTodosData() : TodosData = TodosData(
    this.id, this.userId, this.title, this.completed
)