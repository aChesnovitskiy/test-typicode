package com.example.testtypicode.ui.photos

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtypicode.R
import com.example.testtypicode.viewmodels.photos.PhotosViewModel
import kotlinx.android.synthetic.main.activity_photos.*

class PhotosActivity : AppCompatActivity() {
    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var photosAdapter: PhotosAdapter
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        tv_back_to_users.setOnClickListener {
            finish()
        }

        setupRecyclerView()
        setupViewModel()

        userId = getUserIdFromIntent()
        photosViewModel.loadPhotos(userId)
    }

    private fun getUserIdFromIntent(): Int {
        var userId = 0

        val intent = intent
        if (intent != null && intent.hasExtra("userId")) {
            userId = intent.getIntExtra("userId", -1)
        } else {
            finish()
        }

        return userId
    }

    private fun setupRecyclerView() {
        photosAdapter = PhotosAdapter()

        with(rv_photos) {
            adapter = photosAdapter
            layoutManager = LinearLayoutManager(this@PhotosActivity)
        }
    }

    private fun setupViewModel() {
        val vmFactory = PhotosViewModel.ViewModelFactory(Application())
        photosViewModel = ViewModelProvider(this, vmFactory).get(PhotosViewModel::class.java)

        photosViewModel.getPhotos()
            .observe(this, Observer { photosAdapter.updatePhotos(it) })
    }

    override fun onDestroy() {
        photosViewModel.disposeDisposables()
        super.onDestroy()
    }
}