package uz.akfadiler.testappaliftech.data.remote.response.user

import uz.akfadiler.testappaliftech.data.local.database.users.UserEntity
import uz.akfadiler.testappaliftech.domain.model.UserData

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

fun UserResponse.toUserData(): UserData = UserData(
    this.id,
    this.name,
    this.username,
    this.email,
    this.phone,
    this.website,
    this.company?.name,
    this.address?.geo?.lat,
    this.address?.geo?.lng
)

fun UserResponse.toUserEntity(): UserEntity = UserEntity(
    this.id,
    this.name,
    this.username,
    this.email,
    this.phone,
    this.website,
    this.company?.name,
    this.address?.geo?.lat,
    this.address?.geo?.lng
)
