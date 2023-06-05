package com.example.blazemines

import android.net.Uri
import androidx.navigation.fragment.findNavController
import com.example.common.BlazeMinesFragment
import com.example.common.ScreenNames

class FragmentGlobal : BlazeMinesFragment(R.layout.fragment_global) {
    override fun onResume() {
        super.onResume()

        findNavController().navigate(
            Uri.parse(
                buildString {
                    append(resources.getString(com.example.common.R.string.deep_link_main_module_without_argument))
                    append(ScreenNames.ScreenStart)
                }
            )
        )
    }
}