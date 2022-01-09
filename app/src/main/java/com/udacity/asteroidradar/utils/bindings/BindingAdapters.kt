package com.udacity.asteroidradar.utils.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.udacity.asteroidradar.R
import com.squareup.picasso.Picasso


@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: String) {
    textView.text = String.format(textView.context.getString(R.string.astronomical_unit_format_string), number)
}

@BindingAdapter("astronomicalUnitDouble")
fun bindTextViewToAstronomicalUnitAsDouble(textView: TextView, number: Double) {
    textView.text = String.format(textView.context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    textView.text = String.format(textView.context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: String) {
    textView.text = String.format(textView.context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("imageUrl", "imageErrorResource")
fun bindImageWithError(view: ImageView, imageUrl: String, imageErrorResource: String) {
    Picasso
        .get()
        .load(if(imageUrl.isNotBlank()) { imageUrl } else { "https://nn.com/i" })
        .placeholder(R.drawable.infinite_progress_bar)
        .error(view.context.resources.getIdentifier(imageErrorResource, "drawable", view.context.packageName))
        .into(view)
}

@BindingAdapter("errorText")
fun setErrorText(view: TextView, errorText: Int) {
    if(errorText != 0) view.text = view.context.getString(errorText)
}