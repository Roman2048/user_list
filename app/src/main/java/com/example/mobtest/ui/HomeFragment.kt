package com.example.mobtest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mobtest.MobtestApplication
import com.example.mobtest.R
import com.example.mobtest.adapter.UserAdapter
import com.example.mobtest.data.entity.User
import com.example.mobtest.viewmodel.UserViewModel
import com.example.mobtest.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshButton: ImageButton

    private val userViewModel: UserViewModel by activityViewModels {
        val mobtestApplication = activity?.application as MobtestApplication
        UserViewModelFactory(mobtestApplication.database.userDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.home_user_recycler_view)
        refreshButton = view.findViewById(R.id.user_details_refresh_button)
        val navigateToUserDetails: (user: User) -> Unit = {
            Log.i("user_action", "Home screen: navigate to details with id = ${it.id}")
            userViewModel.currentUser = it
            findNavController().navigate(R.id.action_homeFragment_to_userDetailsFragment)
        }
        val userAdapter = UserAdapter(navigateToUserDetails)
        recyclerView.adapter = userAdapter
        lifecycleScope.launch(Dispatchers.IO) {
            userViewModel.users.collect {
                withContext(Dispatchers.Main) {
                    userAdapter.submitList(it.sortedBy { user -> user.id })
                }
            }
        }
        refreshButton.setOnClickListener {
            userViewModel.loadUsersFromNetwork()
        }
    }
}
