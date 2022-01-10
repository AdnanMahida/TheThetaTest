package com.ad.thethetaprectical.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ad.thethetaprectical.databinding.RecycleListBinding
import com.ad.thethetaprectical.model.UsersItem

class ListsAdapter(
    private val listener: OnClickListener
) :
    ListAdapter<UsersItem, ListsAdapter.ProductViewHolder>(Diff), Filterable {
    private var list = mutableListOf<UsersItem>()


    class ProductViewHolder(val binding: RecycleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(usr: UsersItem) {
            binding.apply {
                var name: String? = null
                var email: String? = null
                name = if (usr.name.startsWith("@")) {
                    usr.name.drop(1)
                } else {
                    usr.name
                }
                email = if (usr.email.startsWith("@")) {
                    usr.email.drop(1)
                } else {
                    usr.email
                }
                txtName.text = "Name: $name"
                txtAge.text = "Age: ${usr.age}"
                txtEmail.text = "Email: $email"
                Log.d("fgfgfg", usr.parent + " hail")
            }
        }

    }

    fun setData(list: MutableList<UsersItem>?) {
        this.list = list!!
        submitList(list)
    }


    object Diff : DiffUtil.ItemCallback<UsersItem>() {
        override fun areItemsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            RecycleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
            holder.binding.root.setOnClickListener {
                listener.setOnClickListener(position)
            }
        }
    }

    interface OnClickListener {
        fun setOnClickListener(position: Int)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<UsersItem>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(list)
                } else {
                    for (item in list) {
                        if (item.name.toString().toLowerCase()
                                .startsWith(constraint.toString().toLowerCase())
                        ) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
                submitList(filterResults?.values as MutableList<UsersItem>)
            }


        }
    }


}