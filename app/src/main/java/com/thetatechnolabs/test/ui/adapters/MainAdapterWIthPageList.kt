package com.thetatechnolabs.test.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexandrius.accordionswipelayout.library.SwipeLayout
import com.bumptech.glide.Glide
import com.thetatechnolabs.test.datamodel.User
import com.thetatechnolabs.test.R
import com.thetatechnolabs.test.ui.fragments.DashboardFragment
import com.thetatechnolabs.test.utils.Utils
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapterWIthPageList(val dashboardFragment: DashboardFragment) : PagedListAdapter<User,MainAdapterWIthPageList.UserViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return UserViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }
    inner  class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), SwipeLayout.OnSwipeItemClickListener {
        init {
            (itemView as SwipeLayout).setOnSwipeItemClickListener(this)
        }
        private val imageView = view.imageViewAvatar
        private val userName = view.textViewUserName
        private val userEmail = view.textViewUserEmail
        fun bind(user: User) {
            userName.text = user.userName
            userEmail.text = user.userEmail
            Glide.with(imageView.context)
                .load(user.image)
                //.placeholder(R.drawable.loading)
                .into(imageView);
        }

        override fun onSwipeItemClick(left: Boolean, index: Int) {
            if (left) {

            } else {
                when (index) {
                    0 -> {

                        Utils.showToast(context =dashboardFragment.requireContext() ,"Swipe to Edit",Toast.LENGTH_LONG)

                    }
                    1 -> {


                        Utils.showToast(context =dashboardFragment.requireContext() ,"Swipe to Delete",Toast.LENGTH_LONG)

                    }

                }
            }
        }

    }
    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.userId == newItem.userId
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                newItem == oldItem
        }
    }
}