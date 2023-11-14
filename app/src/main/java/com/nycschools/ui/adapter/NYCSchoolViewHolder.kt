package com.nycschools.ui.adapter

import com.nycschools.databinding.ItemNycSchoolBinding
import com.nycschools.ui.listeners.ListItemClickListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nycschools.room.NycSchool
class NYCSchoolViewHolder(
    var binding: ItemNycSchoolBinding,
    var listItemClickListener: ListItemClickListener
) : ViewHolder(binding.root) {

    fun bind(nycSchool: NycSchool) {
        binding.nycSchool = nycSchool
        binding.parentLayout.setOnClickListener {
            listItemClickListener.onItemClick(nycSchool.dbn)
        }
    }
}