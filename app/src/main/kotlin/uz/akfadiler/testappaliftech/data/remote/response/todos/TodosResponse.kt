package uz.akfadiler.testappaliftech.data.remote.response.todos

data class TodosResponse(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val completed: Boolean? = null
) : java.io.Serializable
