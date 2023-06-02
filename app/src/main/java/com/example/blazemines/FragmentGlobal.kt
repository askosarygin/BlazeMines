package com.example.blazemines

import android.net.Uri
import androidx.navigation.fragment.findNavController
import com.example.common.BlazeMinesFragment

class FragmentGlobal : BlazeMinesFragment(R.layout.fragment_global) {
    override fun onResume() {
        super.onResume()

        findNavController().navigate(
            Uri.parse(
                resources.getString(
                    com.example.common.R.string.deep_link_main
                )
            )
        )
    }
}