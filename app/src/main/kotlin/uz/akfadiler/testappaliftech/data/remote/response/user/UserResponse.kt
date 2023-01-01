package uz.akfadiler.testappaliftech.data.remote.response.user

data class UserResponse(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: UserAddressResponse? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: CompanyUserResponse? = null
) : java.io.Serializable
