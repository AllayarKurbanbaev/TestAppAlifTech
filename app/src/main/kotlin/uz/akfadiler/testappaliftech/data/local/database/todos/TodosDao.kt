package uz.akfadiler.testappaliftech.data.local.database.todos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodosDao {
    @Query("SELECT * FROM TodosEntity")
    suspend fun getAllTodos(): List<TodosEntity>

    @Query("SELECT * FROM TodosEntity WHERE userId = :userId")
    suspend fun getTodosByUserId(userId: Int): List<TodosEntity>

    @Query("DELETE FROM TodosEntity WHERE userId = :userId")
    suspend fun deleteTodosByUserId(userId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(list: List<TodosEntity>)

    @Query("DELETE FROM TodosEntity")
    suspend fun deleteAllTodos()
}