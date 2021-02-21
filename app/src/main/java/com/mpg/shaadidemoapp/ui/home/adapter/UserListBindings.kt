package com.mpg.shaadidemoapp.ui.home.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mpg.shaadidemoapp.R
import com.mpg.shaadidemoapp.data.entity.UserEntity
import kotlinx.android.synthetic.main.home_fragment.view.*
import java.util.*


private const val TAG = "DeviceListBindings"

/**
 * [BindingAdapter]s for the [UserEntity]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<UserEntity>?) {
    items?.let {
        (listView.adapter as UserListAdapter).submitList(items)
    }
}

@BindingAdapter("app:imageUrl")
fun setImageWithUrl(
    imageView: ImageView,
    url: String
) {
    Glide.with(imageView.context).load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(imageView)

}
//@BindingAdapter("app:completedTask")
//fun setStyle(textView: TextView, enabled: Boolean) {
//    if (enabled) {
//        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//    } else {
//        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
//    }
//}

@BindingAdapter("app:text")
fun setStringFromAny(textView: TextView, any: Int) {
    textView.text = any.toString()
}

@BindingAdapter("app:text")
fun setStringFromAny(textView: TextView, date: Date) {
    textView.text = date.toString()
}