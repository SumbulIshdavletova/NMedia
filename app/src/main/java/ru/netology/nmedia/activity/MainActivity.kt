package ru.netology.nmedia.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.card_post.*
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnLikeListener
import ru.netology.nmedia.adapter.OnShareListener
import ru.netology.nmedia.adapter.PostViewHolder
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter({ viewModel.likeById(it.id) }, { viewModel.shareById(it.id) })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts //обновления данных. команда об обнновлении данных
            adapter.notifyDataSetChanged()
        }
    }


}