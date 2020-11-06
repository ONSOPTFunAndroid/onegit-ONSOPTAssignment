package com.hjh96.sopt1

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment


class ProfileInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_info, container, false)
        return view
    }

}