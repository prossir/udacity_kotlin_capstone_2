package com.udacity.asteroidradar.platform.views.list_asteroids

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding
import com.udacity.asteroidradar.platform.views.common.model.AsteroidModel


class AsteroidsAdapter(
    private val listener: OnAsteroidClicked
): ListAdapter<AsteroidModel, AsteroidsAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position), listener)
    }

    class ViewHolder(private val binding: ItemAsteroidBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: AsteroidModel, listener: OnAsteroidClicked) {
            binding.asteroid = asteroid
            binding.listener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAsteroidBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val diffUtil = object :
            DiffUtil.ItemCallback<AsteroidModel>() {
            override fun areItemsTheSame(old: AsteroidModel, new: AsteroidModel) = false
            override fun areContentsTheSame(old: AsteroidModel, new: AsteroidModel) = false
        }
    }

}