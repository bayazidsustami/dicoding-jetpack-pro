package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.ItemListMovieBinding
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.utils.circularProgress
import com.dicoding.submission.jetpack.utils.inflating

class MovieAdapter(private val list: List<MoviesEntity>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener<MoviesEntity>

    fun setOnItemCLickListener(onItemClickListener: OnItemClickListener<MoviesEntity>){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                parent.inflating(ItemListMovieBinding::inflate)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(
            private val binding: ItemListMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesEntity){
            Glide.with(itemView.context)
                .load(data.posterPath)
                .placeholder(itemView.context.circularProgress())
                .into(binding.imgPoster)
            binding.tvTitle.text = data.title
            binding.root.setOnClickListener {
                onItemClickListener.onClick(data)
            }
        }
    }
}