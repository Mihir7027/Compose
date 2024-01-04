package com.enrich.enrich_news.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.enrich.enrich_news.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) providing access to user-related database operations.
 */
@Dao
interface UserDao {

    /**
     * Inserts or replaces a user in the database.
     *
     * @param user The user to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User)

    /**
     * Retrieves all users from the database as a Flow.
     *
     * @return Flow emitting a list of users.
     */
    @Query("Select * FROM User")
    fun getUsers(): Flow<List<User>>

    /**
     * Retrieves a user from the database by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return User object corresponding to the provided ID, or null if not found.
     */
    @Query("SELECT * FROM User WHERE id=:id")
    suspend fun getUserById(id: Int): User?

    /**
     * Deletes a specific user from the database.
     *
     * @param user The user to be deleted.
     */
    @Delete
    suspend fun deleteUser(user: User)

    /**
     * Updates an existing user's information in the database.
     *
     * @param user The updated user object.
     */
    @Update
    suspend fun updateUser(user: User)
}
