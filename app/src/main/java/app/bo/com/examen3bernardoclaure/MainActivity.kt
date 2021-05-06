package app.bo.com.examen3bernardoclaure


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
        val repository = BookRepository(bookDao)

        GlobalScope.launch {

            //repository.insert(Book("Duendes", "58489", "Bernardo Claure","07/05/2021","202", "el protagonista es un duente","https://image.freepik.com/vector-gratis/linda-duende-verde-posando_29190-2148.jpg"))
           //repository.insert(Book("Dragones", "52222", "Julio Ponce","15/02/2021","100", "Los dragones atacan la ciudad","https://upload.wikimedia.org/wikipedia/commons/d/d8/Friedrich-Johann-Justin-Bertuch_Mythical-Creature-Dragon_1806.jpg"))

            val lista = repository.getListBooks()
            lista.forEach {
                Log.d("prueba base de datos","Id book = ${it.id}, Title:${it.title}, Pages:${it.pages},isbn:${it.isbn},Date:${it.date},author:${it.author},description:${it.description},photoUrl:${it.photoUrl}")
            }
            recycleViewBooks.adapter= BookListAdapter(ArrayList(lista) , applicationContext)
            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recycleViewBooks.layoutManager = linearLayoutManager
        }


        addBook.setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startActivity(intent);

        }
    }
}