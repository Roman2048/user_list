package com.example.mobtest.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.mobtest.MobtestApplication
import com.example.mobtest.R
import com.example.mobtest.data.UserViewModelFactory
import com.example.mobtest.data.entity.validate
import com.example.mobtest.viewmodel.UserViewModel

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private lateinit var userFirstName: EditText
    private lateinit var  userLastName: EditText
    private lateinit var  userAvatar: ImageView
    private lateinit var  userEmail: EditText
    private lateinit var  backButton: ImageButton

    private val userViewModel: UserViewModel by activityViewModels {
        val mobtestApplication = activity?.application as MobtestApplication
        UserViewModelFactory(mobtestApplication.database.userDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        val currentUser = userViewModel.currentUser
        currentUser?.apply {
            userFirstName.setText(firstName)
            userLastName.setText(lastName)
            userEmail.setText(email)
            userAvatar.load(avatarUrl)
        }
        backButton.setOnClickListener {
            currentUser?.run {
                firstName = userFirstName.text.toString()
                lastName = userLastName.text.toString()
                email = userEmail.text.toString()
                userViewModel.insert(this.validate())
                userViewModel.currentUser = null
            }
            view.hideKeyboard()
            findNavController().navigate(R.id.action_userDetailsFragment_to_homeFragment)
        }
    }

    private fun bindViews(view: View) {
        userFirstName = view.findViewById(R.id.user_details_user_first_name_text_view)
        userLastName = view.findViewById(R.id.user_details_user_last_name_text_view)
        userAvatar = view.findViewById(R.id.user_details_user_avatar_image_view)
        userEmail = view.findViewById(R.id.user_details_user_email_text_view)
        backButton = view.findViewById(R.id.user_details_back_button)
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }
}