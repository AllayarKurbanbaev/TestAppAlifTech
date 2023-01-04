package uz.akfadiler.testappaliftech.data.remote.response.todos

import uz.akfadiler.testappaliftech.data.local.database.todos.TodosEntity
import uz.akfadiler.testappaliftech.domain.model.TodosData

data class TodosResponse(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val completed: Boolean? = null
) : java.io.Serializable

fun TodosResponse.toTodosEntity(): TodosEntity = TodosEntity(
    this.id, this.userId, this.title, this.completed
)

fun TodosResponse.toTodosData(): TodosData = TodosData(
    this.id, this.userId, this.title, this.completed
)