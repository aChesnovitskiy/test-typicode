package com.example.testtypicode.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.loader.app.LoaderManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtypicode.R
import com.example.testtypicode.network.NetworkUtils
import com.example.testtypicode.viewmodels.UsersViewModel
import kotlinx.android.synthetic.main.activity_users.*
import org.json.JSONObject
import java.net.URL

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
//        // Draw divider
//        val customDivider = InsetDrawable(
//            resources.getDrawable(R.drawable.divider, activity?.theme),
//            requireContext().dpToPx(72).toInt(),
//            0,
//            requireContext().dpToPx(16).toInt(),
//            0
//        )

//        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
//            .also { it.setDrawable(customDivider) }

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        with(rv_users) {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(this@UsersActivity)
            addItemDecoration(divider)
        }
    }

    private fun setupViewModel() {
        // TODO
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        usersViewModel.getUsers()
            .observe(this, Observer { usersAdapter.updateUsers(it) })
    }

    companion object {
        const val LOADER_ID = 226

    }
}