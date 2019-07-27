package com.gautam.bennettalert


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.support.v4.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_main, container, false)
        view.newAlertButton.setOnClickListener {
            startActivity<NewAlertActivity>()
        }
        return view
    }


}
