package com.example.testtypicode.ui.users

import android.app.Application
import android.content.Intent
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtypicode.R
import com.example.testtypicode.extensions.dpToPx
import com.example.testtypicode.ui.photos.PhotosActivity
import com.example.testtypicode.viewmodels.users.UsersViewModel
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        setupRecyclerView()
        setupViewModel()

        usersViewModel.loadUsers()
    }

    private fun setupRecyclerView() {
        usersAdapter = UsersAdapter { user ->
            val intent = Intent(this@UsersActivity, PhotosActivity::class.java)
            intent.putExtra("userId", user.id)
            startActivity(intent)
        }

        val customDivider = InsetDrawable(
            resources.getDrawable(R.drawable.divider, this.theme),
            this.dpToPx(20).toInt(),
            0,
            0,
            0
        )

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            .also { it.setDrawable(customDivider) }

        with(rv_users) {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(this@UsersActivity)
            addItemDecoration(divider)
        }
    }

    private fun setupViewModel() {
        val vmFactory = UsersViewModel.ViewModelFactory(Application())
        usersViewModel = ViewModelProvider(this, vmFactory).get(UsersViewModel::class.java)

        usersViewModel.getUsers()
            .observe(this, Observer { usersAdapter.updateUsers(it) })
    }

    override fun onDestroy() {
        usersViewModel.disposeDisposables()
        super.onDestroy()
    }
}