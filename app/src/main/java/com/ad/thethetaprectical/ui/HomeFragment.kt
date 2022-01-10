package com.ad.thethetaprectical.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ad.thethetaprectical.databinding.FragmentHomeBinding
import com.ad.thethetaprectical.network.RetrofitHelper
import com.ad.thethetaprectical.network.UsersApi
import com.ad.thethetaprectical.ui.adapter.ListsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), ListsAdapter.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var adapter: ListsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.apply {
            val userApi = RetrofitHelper.getInstance().create(UsersApi::class.java)
            // launching a new coroutine
            adapter = ListsAdapter(this@HomeFragment)
            recycleView.layoutManager = LinearLayoutManager(context)
            recycleView.adapter = adapter

            GlobalScope.launch(Dispatchers.Main) {
                val result = userApi.getUsers()
                if (result != null)
                // Checking the results
                {
                    // adapter!!.submitList(result.body())
                    Log.d("RESRRRRdfdf", result.body().toString())
                    adapter!!.submitList(result.body())

                }


            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setOnClickListener(position: Int) {
        val intent = Intent(activity, UserDetailsActivity::class.java)
        startActivity(intent)
    }


}