package com.ad.thethetaprectical.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ad.thethetaprectical.R
import com.ad.thethetaprectical.databinding.FragmentHomeBinding
import com.ad.thethetaprectical.model.UsersItem
import com.ad.thethetaprectical.network.RetrofitHelper
import com.ad.thethetaprectical.network.UsersApi
import com.ad.thethetaprectical.ui.adapter.ListsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable


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

    override fun setOnClickListener(obj: UsersItem) {
        if (obj.child.isNotEmpty()) {
            val intent = Intent(activity, UserDetailsActivity::class.java)
            intent.putExtra("list", obj)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = SearchView((context as MainActivity).supportActionBar!!.themedContext)
        // MenuItemCompat.setShowAsAction(item, //MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | //MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        //  MenuItemCompat.setActionView(item, searchView);
        // These lines are deprecated in API 26 use instead
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
        item.setActionView(searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView.setOnClickListener { }
    }

}