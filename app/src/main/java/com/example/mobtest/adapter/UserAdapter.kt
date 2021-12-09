package com.example.mobtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mobtest.R
import com.example.mobtest.data.entity.User
import com.google.android.material.textview.MaterialTextView

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback) {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarUrl: AppCompatImageView = view.findViewById(R.id.home_user_avatar_image_view)
        val name: MaterialTextView = view.findViewById(R.id.home_user_name_text_view)
        val email: MaterialTextView = view.findViewById(R.id.home_user_email_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = getItem(position)
        holder.avatarUrl.load(user.avatarUrl)
        holder.name.text = "${user.firstName} ${user.lastName}"
        holder.email.text = user.email
    }

    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return  oldItem.firstName == newItem.firstName
        }
    }
}


