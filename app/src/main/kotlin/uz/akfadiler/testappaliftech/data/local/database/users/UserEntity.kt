package uz.akfadiler.testappaliftech.data.local.database.users

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.akfadiler.testappaliftech.domain.model.UserData

@Entity
data class UserEntity(
    @PrimaryKey val id: Int?,
    val name: String?,
    val username: String?,
    val email: String?,
    val phone: String?,
    val website: String?,
    val nameCompany: String?,
    val lat: Double?,
    val lng: Double?
)

fun UserEntity.toUserData(): UserData = UserData(
    this.id,
    this.name,
    this.username,
    this.email,
    this.phone,
    this.website,
    this.nameCompany,
    this.lat,
    this.lng
)