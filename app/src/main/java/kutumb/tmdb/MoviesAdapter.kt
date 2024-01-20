package kutumb.tmdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kutumb.core.data.model.Movie
import kutumb.tmdb.databinding.MovieItemsBinding

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(private val binding: MovieItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.title.text = movie.title
            val poster = "https://image.tmdb.org/t/p/original/${movie.poster_path}"
            Glide.with(binding.root.context).load(poster).into(binding.poster)
        }
    }

    private val diffUtilCallback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val moviesDiffer = AsyncListDiffer(this,diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemsBinding.inflate(LayoutInflater.from(
            parent.context
        ), parent, false)

        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return moviesDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = moviesDiffer.currentList[position]
        holder.bind(movie)
    }
}











