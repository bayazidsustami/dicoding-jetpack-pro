package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.databinding.ItemListMovieBinding
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.utils.inflating

class TvShowAdapter(private val list: List<TvShowsEntity>)
    :RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener<TvShowsEntity>

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<TvShowsEntity>){
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
        fun bind(data: TvShowsEntity){
            Glide.with(itemView.context)
                .load(data.posterPath)
                .into(binding.imgPoster)
            binding.tvTitle.text = data.title
            binding.root.setOnClickListener {
                onItemClickListener.onClick(data)
            }
        }
    }
}