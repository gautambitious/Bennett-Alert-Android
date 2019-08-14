package com.gautam.bennettalert


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_phone_login.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.*
import kotlin.concurrent.thread

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
        view.pastAlertButton.setOnClickListener {
            Thread {
                Thread.sleep(5000)
                toast("Nice")
            }
            toast("This works")
        }
        return view
    }


}
