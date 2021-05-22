package com.goldball.app.ui.main.players

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goldball.app.R
import com.goldball.app.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment: Fragment(R.layout.main_list_fragment) {

    private lateinit var recyclerView: RecyclerView
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: PlayersViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = PlayersAdapter(mainViewModel)
        recyclerView.adapter = adapter
        viewModel.getPlayers().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}
