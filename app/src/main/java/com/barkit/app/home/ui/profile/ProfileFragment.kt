package com.barkit.app.home.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.barkit.app.R
import com.barkit.app.addstore.AddStoreActivity
import com.barkit.app.authentication.LoginActivity
import com.barkit.app.core.domain.model.Renter
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.FragmentProfileBinding
import com.barkit.app.store.home.StoreHomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentProfileBinding? = null
    private var isLessor = false

    private val binding get() = _binding!!
    private val profileViewModel by viewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.renterProfile.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> initRenterProfile(it.data)
                is Resource.Loading -> {}
                is Resource.Error -> {}
            }
        }

        binding.btnMyStore.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_my_store -> {
                if (isLessor) {
                    val storeIntent = Intent(activity, StoreHomeActivity::class.java)
                    startActivity(storeIntent)
                } else {
                    val addStoreIntent = Intent(activity, AddStoreActivity::class.java)
                    startActivity(addStoreIntent)
                }
            }

            R.id.btn_logout -> {
                profileViewModel.logout().observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                            val loginIntent = Intent(activity, LoginActivity::class.java)
                            startActivity(loginIntent)
                            activity?.finish()
                        }

                        is Resource.Loading -> {}

                        is Resource.Error -> {
                            Toast.makeText(requireContext(), "Error Logout!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun initRenterProfile(renter: Renter?) {
        if (renter != null) {
            with(binding) {
                isLessor = renter.isLessor

                tvFullName.text = renter.fullName
                tvUsername.text = renter.username

                tvVerifiedStatus.text = resources.getString(
                    if (renter.emailVerified)
                        R.string.email_verified
                    else
                        R.string.email_not_verified
                )
                tvVerifiedStatus.setBackgroundResource(
                    if (renter.emailVerified)
                        R.color.green_success
                    else
                        R.color.yellow_warning
                )

                btnMyStore.isEnabled = renter.emailVerified
            }
        }
    }
}