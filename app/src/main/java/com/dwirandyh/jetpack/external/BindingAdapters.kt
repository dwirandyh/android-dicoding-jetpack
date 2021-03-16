package com.dwirandyh.jetpack.external

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dwirandyh.jetpack.R
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("date")
    fun bindDateFormat(textView: TextView, date: Date) {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        textView.text = formatter.format(date)
    }

    @JvmStatic
    @BindingAdapter("url")
    fun bindImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_image)
            .into(imageView)
    }
}