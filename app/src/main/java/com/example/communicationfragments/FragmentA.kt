package com.example.communicationfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*
import java.lang.ClassCastException

class FragmentA : Fragment() {

    private var listener: OnButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnButtonClickListener
        if (listener == null) {
            throw ClassCastException("$context must implement OnButtonClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonClick.setOnClickListener { listener?.onButtonClick() }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnButtonClickListener{
        fun onButtonClick()

    }
    companion object{
        fun newInstance() = FragmentA()
    }
}