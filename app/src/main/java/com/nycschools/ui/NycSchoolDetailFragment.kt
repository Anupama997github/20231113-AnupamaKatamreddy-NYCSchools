package com.nycschools.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.nycschools.R
import com.nycschools.databinding.FragmentNycSchoolDetailBinding
import com.nycschools.mvvm.viewmodel.NycViewModel

class NycSchoolDetailFragment : Fragment() {

    private lateinit var binding: FragmentNycSchoolDetailBinding

    private val viewModel: NycViewModel by activityViewModels()

    val args: NycSchoolDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNycSchoolDetailBinding.inflate(inflater, container, false)

        viewModel.schoolInfo.observe(viewLifecycleOwner) {
            binding.nycSchool = it
        }

        viewModel.getNycSchoolInfo(args.schoolId)

        return binding.root
    }
}