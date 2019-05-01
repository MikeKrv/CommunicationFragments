package com.example.communicationfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_b.*

class FragmentB : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            val sharedViewModel = ViewModelProviders.of(it).get(SharedViewModel::class.java)
            observeInput(sharedViewModel)
        }
    }

    private fun observeInput(sharedViewModel: SharedViewModel) {
        sharedViewModel.liveCounter.observe(this, Observer {
            it?.let{
                tvCounterValue.text = "$it"
            }
        })
    }

    companion object {
        fun newInstance() = FragmentB()
    }
}