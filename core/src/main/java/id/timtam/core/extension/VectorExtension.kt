package id.timtam.core.extension

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

val Int.dp : Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun ImageView.place(image: Any) {
    Glide.with(this.context)
        .load(image)
        .thumbnail(0.3f)
        .placeholder(ColorDrawable(Color.LTGRAY))
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions.centerCropTransform())
        .into(this)
}