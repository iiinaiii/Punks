package com.iiinaiii.punks.ui.detail.item

import com.iiinaiii.punks.R
import com.iiinaiii.punks.databinding.ItemDetailHeaderBinding
import com.xwray.groupie.databinding.BindableItem

class HeaderItem(private val text: String) : BindableItem<ItemDetailHeaderBinding>() {
    override fun getLayout() = R.layout.item_detail_header

    override fun bind(viewBinding: ItemDetailHeaderBinding, position: Int) {
        viewBinding.headerText.text = text
    }
}