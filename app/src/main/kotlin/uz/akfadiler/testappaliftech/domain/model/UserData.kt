package uz.akfadiler.testappaliftech.domain.model

data class UserData(
    val id: Int?,
    val name: String?,
    val username: String?,
    val email: String?,
    val phone: String?,
    val website: String?,
    val nameCompany: String?,
    val lat: Double?,
    val lng: Double?
) : java.io.Serializable
