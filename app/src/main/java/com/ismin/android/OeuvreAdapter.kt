import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ismin.android.Oeuvre
import com.ismin.android.OeuvreCreator
import com.ismin.android.OeuvreDetailActivity
import com.ismin.android.OeuvreListFragmentLessDetail
import com.ismin.android.OeuvreViewHolder
import com.ismin.android.R

class OeuvreAdapter(
    private var oeuvres: List<Oeuvre>,
    private val listener: OeuvreListFragmentLessDetail.OnFavoriteButtonClickListener?
) : RecyclerView.Adapter<OeuvreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OeuvreViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_oeuvre_less_details, parent, false)

        return OeuvreViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: OeuvreViewHolder, position: Int) {
        val oeuvre = oeuvres[position]

        // Bind data to the ViewHolder
        holder.txvIdOeuvre.text = oeuvre.id_oeuvre
        holder.txvIdExposition.text = oeuvre.id_exposition
        holder.txvTitre.text = oeuvre.titre
        holder.txvAnnee.text = oeuvre.annee

        // Load image from URL using Glide
        Glide.with(holder.imgPhotoUrl2.context)
            .load(oeuvre.photo_url2)
            .into(holder.imgPhotoUrl2)

        // Update the favorite button icon based on the favorite status
        updateFavoriteIcon(holder, oeuvre)

        // Set up button click listener for details
        holder.detailButton.setOnClickListener {
            showDetails(oeuvre, holder.itemView.context)
        }

        holder.favoriteButton.setOnClickListener {
            listener?.onPutFavoriteCreated(oeuvre)
            ajouterFavorite(oeuvre, holder)
        }
    }


    private fun ajouterFavorite(oeuvre: Oeuvre, holder: OeuvreViewHolder) {
        // Reorder the list so favorites come to the top
        oeuvres = oeuvres.sortedByDescending { it.favorite }.toMutableList()

        // Change the icon based on whether it's a favorite
        updateFavoriteIcon(holder, oeuvre)

        // Use the Context from the ViewHolder to show the Toast
        val context = holder.itemView.context
        Toast.makeText(context, if (oeuvre.favorite) "Added to Favorites" else "Removed from Favorites", Toast.LENGTH_SHORT).show()

        // Notify the adapter that the list has changed
        notifyDataSetChanged()
    }

    private fun updateFavoriteIcon(holder: OeuvreViewHolder, oeuvre: Oeuvre) {
        Log.d("put","Did it enter updateFavoriteICone??? ${oeuvre.id_oeuvre} ${oeuvre.id_oeuvre}")

        if (oeuvre.favorite) {
            holder.favoriteButton.setImageResource(R.drawable.ic_star_filled) // Filled star
        } else {
            holder.favoriteButton.setImageResource(R.drawable.ic_star_empty) // Empty star
        }

        //notifyDataSetChanged()
    }

    private fun showDetails(oeuvre: Oeuvre, context: Context) {
        // Create an intent to launch OeuvreDetailActivity
        val intent = Intent(context, OeuvreDetailActivity::class.java)

        intent.putExtra("oeuvre", oeuvre)

        // Start the activity
        context.startActivity(intent)
    }

    override fun getItemCount(): Int = oeuvres.size

    fun updateData(allOeuvres: List<Oeuvre>) {
        oeuvres = allOeuvres
        notifyDataSetChanged()
    }

}
