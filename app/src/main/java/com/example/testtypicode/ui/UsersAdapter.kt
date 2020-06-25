package com.example.testtypicode.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtypicode.R
import com.example.testtypicode.data.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class UsersAdapter(val listener: (User) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var users = listOf<User>()

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(users[position], listener)

    inner class UserViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(user: User, listener: (User) -> Unit) {
            tv_user_name.text = user.name

            itemView.setOnClickListener() {
                listener.invoke(user)
            }
        }
    }
}