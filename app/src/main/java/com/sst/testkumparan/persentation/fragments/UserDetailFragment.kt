package com.sst.testkumparan.persentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.sst.testkumparan.R
import com.sst.testkumparan.databinding.FragmentUserDetailBinding
import com.sst.testkumparan.persentation.viewmodels.UserViewModel


class UserDetailFragment : Fragment() {

    lateinit var binding : FragmentUserDetailBinding
    private val args: UserDetailFragmentArgs by navArgs()
    private val userVM : UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        userVM.getUser(args.id)
        userVM.userState.observe(viewLifecycleOwner,{
            binding.test.text = it.toString()
        })
        return binding.root
    }


}