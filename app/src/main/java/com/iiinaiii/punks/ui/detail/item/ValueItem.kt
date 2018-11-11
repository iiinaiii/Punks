package com.iiinaiii.punks.ui.detail.item

import com.iiinaiii.punks.R
import com.iiinaiii.punks.databinding.ItemDetailValueBinding
import com.xwray.groupie.databinding.BindableItem

class ValueItem(
    private val title: String,
    private val value: String
) : BindableItem<ItemDetailValueBinding>() {
    override fun getLayout() = R.layout.item_detail_value

    override fun bind(viewBinding: ItemDetailValueBinding, position: Int) {
        viewBinding.title.text = title
        viewBinding.value.text = value
    }
}