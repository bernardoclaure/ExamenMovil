package app.bo.com.examen3bernardoclaure


class BookRepository(private val bookDao: IBookDao) {
    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }
    suspend fun delete(book: Book)
    {
        bookDao.delete(book)
    }

    fun getListBooks(): List<Book> {
        return bookDao.getAll()
    }
    suspend fun update(book: Book){
        bookDao.update(book)
    }
    suspend fun findById(id: String): Book{
        return bookDao.findById(id)
    }
}