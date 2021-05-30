package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.databinding.ItemListMovieBinding
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.utils.inflating
import com.dicoding.submission.jetpack.utils.loadImage

class TvShowAdapter :PagedListAdapter<TvShowsEntity, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

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
        val tvShows = getItem(position)
        if (tvShows != null){
            holder.bind(tvShows)
        }
    }


    inner class ViewHolder(
        private val binding: ItemListMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowsEntity){
            binding.imgPoster.loadImage(itemView.context, data.posterPath)
            binding.tvTitle.text = data.title
            binding.root.setOnClickListener {
                onItemClickListener.onClick(data)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>(){
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TvShowsEntity,
                newItem: TvShowsEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}