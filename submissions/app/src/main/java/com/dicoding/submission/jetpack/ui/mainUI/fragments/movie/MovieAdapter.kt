package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.ItemListMovieBinding
import com.dicoding.submission.jetpack.utils.inflating
import com.dicoding.submission.jetpack.utils.loadImage

class MovieAdapter: PagedListAdapter<MoviesEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemSelected: ((MoviesEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                parent.inflating(ItemListMovieBinding::inflate)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null){
            holder.bind(movies)
        }
    }

    inner class ViewHolder(
            private val binding: ItemListMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesEntity){
            binding.imgPoster.loadImage(itemView.context, data.posterPath)
            binding.tvTitle.text = data.title
            binding.root.setOnClickListener {
                onItemSelected?.invoke(data)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MoviesEntity>(){
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}