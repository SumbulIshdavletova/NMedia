package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_border_24 else R.drawable.ic_baseline_favorite_24
                )
                likeCount?.text = post.viewFormat(post.likes)
                shareCount?.text = post.viewFormat(post.shares)
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()

        }
        binding.share.setOnClickListener {
            viewModel.share()

        }

    }
}