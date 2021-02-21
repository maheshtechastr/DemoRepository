package com.mpg.shaadidemoapp.ui.home.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mpg.shaadidemoapp.R
import com.mpg.shaadidemoapp.data.entity.UserEntity
import com.mpg.shaadidemoapp.data.entity.UserStatus
import com.mpg.shaadidemoapp.ui.utils.Utils
import timber.log.Timber


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

@BindingAdapter("app:text")
fun setStringFromAny(textView: TextView, any: Int) {
    textView.text = any.toString()
}

@BindingAdapter("app:userStatus")
fun setStringStatus(view: LinearLayout, user: UserEntity) {
    if (user.userStatus == Utils.getUserStatusInt(UserStatus.NONE))
        view.visibility = View.VISIBLE
    else
        view.visibility = View.INVISIBLE
}

@BindingAdapter("app:userStatus")
fun setStringForUStatus(textView: TextView, user: UserEntity) {
    Log.i("Bing","Status == ${user.userStatus}")
    if (user.userStatus == Utils.getUserStatusInt(UserStatus.NONE))
        textView.visibility = View.INVISIBLE
    else {
        if (user.userStatus == Utils.getUserStatusInt(UserStatus.ACCEPTED))
            textView.text = "Member Accepted"
        else
            textView.text = "Member Declined"
        textView.visibility = View.VISIBLE
    }
}