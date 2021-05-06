package app.bo.com.examen3bernardoclaure


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_book.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class BookListAdapter(var list : ArrayList<Book>, applicationContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
    val repository = BookRepository(bookDao)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return BookListViewHolder(view)
    }

    class BookListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = list[position]
        holder.itemView.textViewTitle.text = book.title
        holder.itemView.textViewDescription.text = book.description
        holder.itemView.textViewAuthor.text=book.author
        holder.itemView.deleteButton.setOnClickListener {
            GlobalScope.async {
                repository.delete(book)

            }
            val context=holder.itemView.context
            val intent = Intent( context, MainActivity::class.java)
            context.startActivity(intent)
        }
        holder.itemView.editButton.setOnClickListener {
        }
        holder.itemView.editButton.setOnClickListener {
            val context=holder.itemView.context
            val intent = Intent( context, EditBookActivity::class.java)
            var bundle: Bundle = Bundle()
            var atributos:String=book.id.toString()
            bundle.putString("id",atributos)
            intent.putExtras(bundle)

            context.startActivity(intent)
        }
        val picasso = Picasso.get()
        picasso.load(book.photoUrl).into(holder.itemView.my_image_view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
