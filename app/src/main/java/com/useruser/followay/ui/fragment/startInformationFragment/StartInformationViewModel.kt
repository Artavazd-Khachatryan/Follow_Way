package com.useruser.followay.ui.fragment.startInformationFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.useruser.followay.R
import com.useruser.followay.domain.addapters.StartInfoListAdapter
import com.useruser.followay.domain.models.StartInformationItemModel
import javax.inject.Inject

class StartInformationViewModel @Inject constructor(context: Context) : ViewModel() {

    val infoListAdapter = StartInfoListAdapter().apply {
        submitList(
            listOf(
                StartInformationItemModel(R.drawable.info_page_1_bg, R.string.start_info_page_1),
                StartInformationItemModel(R.drawable.info_page_2_bg, R.string.start_info_page_2)
            )
        )
    }

    val layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
}
