package com.nycschools.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nycschools.R
import com.nycschools.databinding.FragmentNycSchoolsListBinding
import com.nycschools.mvvm.model.NycSchoolData
import com.nycschools.mvvm.viewmodel.NycViewModel
import com.nycschools.room.NycSchool
import com.nycschools.ui.adapter.NycSchoolItemListAdapter
import com.nycschools.ui.listeners.ListItemClickListener


class NycSchoolsListFragment : Fragment(), ListItemClickListener {
    private lateinit var binding: FragmentNycSchoolsListBinding

    private val viewModel: NycViewModel by activityViewModels()

    private val listOfItems = ArrayList<NycSchool>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNycSchoolsListBinding.inflate(layoutInflater, container, false)

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        viewModel.schoolData.observe(viewLifecycleOwner) {
            when (it) {
                is NycSchoolData.Loading -> {
                    binding.progressBar.apply {
                        visibility = if (it.loading) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                    }
                }
                is NycSchoolData.ListOfSchools-> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    listOfItems.clear()
                    listOfItems.addAll(it.listOfSchools!!)
                    binding.recyclerView.adapter = NycSchoolItemListAdapter(listOfItems, this)
                }
                is NycSchoolData.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.errorText.text ="Something went wrong, Please try again later."
                }
                else -> {}
            }
        }

        return binding.root
    }


    override fun onItemClick(schoolId: String) {
        findNavController().navigate(
            NycSchoolsListFragmentDirections.nycSchoolsListFragmentToNycSchoolDetailFragment(schoolId)
        )
    }
}