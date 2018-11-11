package com.iiinaiii.punks.ui.detail.item

import com.iiinaiii.punks.R
import com.iiinaiii.punks.databinding.ItemDetailSimpleTextBinding
import com.xwray.groupie.databinding.BindableItem

class SimpleTextItem(private val text: String) : BindableItem<ItemDetailSimpleTextBinding>() {
    override fun getLayout() = R.layout.item_detail_simple_text


    override fun bind(viewBinding: ItemDetailSimpleTextBinding, position: Int) {
        viewBinding.bodyText.text = text
    }
}