package com.example.mobtest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.mobtest.MobtestApplication
import com.example.mobtest.R
import com.example.mobtest.data.UserViewModelFactory
import com.example.mobtest.viewmodel.UserViewModel

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private val userViewModel: UserViewModel by activityViewModels {
        val mobtestApplication = activity?.application as MobtestApplication
        UserViewModelFactory(mobtestApplication.database.userDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = view.findViewById<TextView>(R.id.user_details_user_name_text_view)
        val userAvatar = view.findViewById<ImageView>(R.id.user_details_user_avatar_image_view)
        val userEmail = view.findViewById<TextView>(R.id.user_details_user_email_text_view)
        val currentUser = userViewModel.currentUser
        if (currentUser != null) {
            userName.text = currentUser.firstName
            userEmail.text = currentUser.email
            userAvatar.load(currentUser.avatarUrl)
        }
    }
}