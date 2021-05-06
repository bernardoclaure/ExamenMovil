package app.bo.com.examen3bernardoclaure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_create_book.*
import kotlinx.android.synthetic.main.row_book.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EditBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
        setContentView(R.layout.activity_create_book)
        val repository = BookRepository(bookDao)
        var bundle=intent.extras
        var atributes=bundle?.getString("id")
        if(atributes!=null)
        {
            GlobalScope.launch {
                var book:Book=repository.findById(atributes)
                Log.d("prueba base de datos","Id book = ${book.id}, Title:${book.title}, Pages:${book.pages},isbn:${book.isbn},Date:${book.date},author:${book.author},description:${book.description},photoUrl:${book.photoUrl}")

                editTextAuthor.setText(book.author)
                editTextTitle.setText(book.title)
                editTextDescription.setText(book.description)
                editTextIsbn.setText(book.isbn)
                editTextPhotoUrl.setText(book.photoUrl)
                editTextPages.setText(book.pages)
                editTextDate.setText(book.date)
            }
            val save = findViewById<Button>(R.id.btn_save)
            save.setOnClickListener {
                GlobalScope.launch {
                    var book:Book=repository.findById(atributes)
                     book.title = editTextTitle.text.toString()
                     book.pages = editTextPages.text.toString()
                    book.isbn = editTextIsbn.text.toString()
                    book.date=editTextDate.text.toString()
                    book.author = editTextAuthor.text.toString()
                    book.description = editTextDescription.text.toString()
                    book.photoUrl = editTextPhotoUrl.text.toString()

                    repository.update(book)
                }
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


        }


    }
}