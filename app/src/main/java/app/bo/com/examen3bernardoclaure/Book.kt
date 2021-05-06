package app.bo.com.examen3bernardoclaure


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
class Book (@ColumnInfo(name = "title") var title: String,
            @ColumnInfo(name = "isbn") var  isbn: String,
            @ColumnInfo(name = "author") var  author: String,
            @ColumnInfo(name = "date") var  date: String,
            @ColumnInfo(name = "pages") var  pages: String,
            @ColumnInfo(name = "description") var  description: String,
            @ColumnInfo(name = "photoUrl") var  photoUrl: String
)

{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}