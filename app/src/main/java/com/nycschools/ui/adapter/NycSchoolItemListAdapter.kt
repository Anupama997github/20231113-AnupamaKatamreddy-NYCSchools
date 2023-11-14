package com.nycschools.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nycschools.R
import com.nycschools.databinding.ItemNycSchoolBinding
import com.nycschools.room.NycSchool
import com.nycschools.ui.listeners.ListItemClickListener

class NycSchoolItemListAdapter(var items: List<NycSchool>, var listItemClickListener: ListItemClickListener) :
    RecyclerView.Adapter<NYCSchoolViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NYCSchoolViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNycSchoolBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_nyc_school, parent, false
        )
        return NYCSchoolViewHolder(binding, listItemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NYCSchoolViewHolder, position: Int) {
        holder.bind(items[position])
    }
}