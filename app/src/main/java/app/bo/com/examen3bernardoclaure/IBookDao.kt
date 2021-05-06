package app.bo.com.examen3bernardoclaure

import androidx.room.*

@Dao
interface IBookDao {
    @Query("SELECT * FROM book_table")
    fun getAll(): List<Book>

    @Query("SELECT * FROM book_table WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: String): Book

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book:Book)

    @Delete
    suspend fun delete(book: Book)
    @Update
    suspend fun update(book: Book)
}