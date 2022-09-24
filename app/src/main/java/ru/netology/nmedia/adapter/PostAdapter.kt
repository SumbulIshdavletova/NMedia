package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
//import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_post.view.*
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import java.util.*

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onRemove(post: Post) {}
    fun onEdit(post: Post) {}
    fun cancelEdit(post: Post) {}
}

class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding,  onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {



    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )
            share.setImageResource(R.drawable.ic_baseline_share_24)
            likeCount?.text = post.viewFormat(post.likes)
            shareCount?.text = post.viewFormat(post.shares)
            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }

                    }

                }.show()
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}