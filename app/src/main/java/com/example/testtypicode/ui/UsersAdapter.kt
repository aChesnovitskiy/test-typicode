package com.example.testtypicode.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class UserAdapter(val listener: () -> Unit) :
    RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {
    private var users = listOf<CharacterItem>()

    fun updateData(data: List<CharacterItem>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldPos: Int, newPos: Int) =
                characters[oldPos].id == data[newPos].id

            override fun areContentsTheSame(oldPos: Int, newPos: Int) =
                characters[oldPos] == data[newPos]

            override fun getOldListSize() = characters.size
            override fun getNewListSize() = data.size
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characters = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_character, parent, false
            )
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(characters[position], listener)
    }

    // Get summary string from titles and aliases
    private fun getTitlesAndAliases(character: CharacterItem): MutableList<String> {
        val result = mutableListOf<String>()
        if (character.titles.isNotEmpty()) result.addAll(character.titles)
        if (character.aliases.isNotEmpty()) result.addAll(character.aliases)
        return result
    }

    inner class UsersViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(character: CharacterItem, listener: (CharacterItem) -> Unit) {
            val context = App.applicationContext()
            val name = character.name
            val info = getTitlesAndAliases(character).joinWithDots()
            val logo = HouseUtils.getLogo(character.house)

            tv_character_name.text = if (name.isNotEmpty()) name else
                context.getString(R.string.information_is_unknown)
            tv_character_info.text = if (info.isNotEmpty()) info else
                context.getString(R.string.information_is_unknown)
            iv_house_logo.setImageDrawable(logo)

            itemView.setOnClickListener() {
                listener.invoke(character)
            }
        }
    }
}