package com.ad.thethetaprectical.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ad.thethetaprectical.databinding.ActivityUserDetailsBinding
import com.ad.thethetaprectical.model.Child
import com.ad.thethetaprectical.model.UsersItem

import com.ad.thethetaprectical.ui.adapter.ListsDetailsAdapter

class UserDetailsActivity : AppCompatActivity(),
    ListsDetailsAdapter.OnClickListener {
    private lateinit var binding: ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myAdapter = ListsDetailsAdapter(this)

        binding.apply {
            try {
            val userItem: UsersItem = intent.getSerializableExtra("list") as UsersItem
            recycleView.adapter = myAdapter
            myAdapter.submitList(userItem.child)
            recycleView.layoutManager = LinearLayoutManager(this@UserDetailsActivity)
            }catch (e:Exception){}
        }

    }



    override fun setOnClickListener(obj: Child) {

    }




}