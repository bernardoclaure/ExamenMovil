package app.bo.com.examen3bernardoclaure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.GlobalScope
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_book.*
import kotlinx.coroutines.async

class CreateBookActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)
        val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
        val repository = BookRepository(bookDao)

        val save = findViewById<Button>(R.id.btn_save)
        save.setOnClickListener {
            val title = editTextTitle.text.toString()
            val pages = editTextPages.text.toString()
            val isbn = editTextIsbn.text.toString()
            val date=editTextDate.text.toString()
            val author = editTextAuthor.text.toString()
            val description = editTextDescription.text.toString()
            val photoUrl = editTextPhotoUrl.text.toString()
            val intent:Intent = Intent(this, MainActivity::class.java)
            GlobalScope.async {
                repository.insert(Book(title,isbn,author,date, pages ,description,photoUrl))
            }
            startActivity(intent)
        }
    }
}

