package com.example.testtypicode.ui

import android.app.Application
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtypicode.R
import com.example.testtypicode.extensions.dpToPx
import com.example.testtypicode.viewmodels.UsersViewModel
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        usersAdapter = UsersAdapter {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }

        // TODO
        // Draw divider
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

        usersViewModel.loadUsers()
    }
}