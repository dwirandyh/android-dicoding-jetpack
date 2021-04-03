package com.dwirandyh.jetpack.external

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dwirandyh.jetpack.R
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("date")
    fun bindDateFormat(textView: TextView, date: Date?) {
        date?.let {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            textView.text = formatter.format(date)
        }
    }

    @JvmStatic
    @BindingAdapter("url")
    fun bindImageUrl(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView)
                .load(it)
                .centerCrop()
                .placeholder(R.drawable.ic_image)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("rating")
    fun bindRating(ratingBar: RatingBar, rating: Double) {
        ratingBar.rating = rating.toFloat()
    }
}