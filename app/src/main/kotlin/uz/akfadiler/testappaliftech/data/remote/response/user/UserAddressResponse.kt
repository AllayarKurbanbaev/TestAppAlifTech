package uz.akfadiler.testappaliftech.data.remote.response.user

data class UserAddressResponse(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: GeoUser? = null
) : java.io.Serializable
