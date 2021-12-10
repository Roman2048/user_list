package com.example.mobtest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mobtest.MobtestApplication
import com.example.mobtest.R
import com.example.mobtest.adapter.UserAdapter
import com.example.mobtest.data.UserViewModelFactory
import com.example.mobtest.data.entity.User
import com.example.mobtest.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshButton: ImageButton

    private val userViewModel: UserViewModel by activityViewModels {
        val mobtestApplication = activity?.application as MobtestApplication
        UserViewModelFactory(mobtestApplication.database.userDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.home_user_recycler_view)
        refreshButton = view.findViewById(R.id.user_details_refresh_button)
        val navigateToUserDetails: (user: User) -> Unit = {
            userViewModel.currentUser = it
            findNavController().navigate(R.id.action_homeFragment_to_userDetailsFragment)
        }
        val userAdapter = UserAdapter(navigateToUserDetails)
        recyclerView.adapter = userAdapter
        lifecycle.coroutineScope.launch {
            userViewModel.users.collect {
                userAdapter.submitList(it)
            }
        }
        refreshButton.setOnClickListener {
            userViewModel.loadUsersFromNetwork()
        }
    }
}