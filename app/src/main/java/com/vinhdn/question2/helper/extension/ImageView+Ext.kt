package com.vinhdn.question2.helper.extension

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.transition.Transition

//Extension for ImageView to load image using Glide
fun ImageView.load(resource: Any?,
                   animate: Boolean = true,
                   placeHolder: Any? = null,
                   error: Any? = null,
                   thumbnail: Float = 0f,
                   fitCenter: Boolean = false,
                   centerCrop: Boolean = false,
                   isCirular: Boolean = false,
                   diskCacheStrategy: DiskCacheStrategy? = null, callback: ((Bitmap) -> Unit)? = null) {

    val request = Glide.with(context).load(resource)
    val options = RequestOptions()
    if (!animate) {
        options.dontAnimate()
    }

    if (fitCenter) {
        options.fitCenter()
    }

    if (centerCrop) {
        options.centerCrop()
    }

    if (thumbnail != 0f) {
        request.thumbnail(thumbnail)
    }

    diskCacheStrategy?.apply {
        options.diskCacheStrategy(diskCacheStrategy)
    }

    placeHolder?.apply {
        if (placeHolder is Drawable) {
            options.placeholder(placeHolder)
        } else if (placeHolder is Int) {
            options.placeholder(placeHolder)
        }
    }

    error?.apply {
        if (error is Drawable) {
            options.error(error)
        } else if (error is Int) {
            options.error(error)
        }
    }

    if (isCirular) {
        options.circleCrop()
                .autoClone()
    }
    request.apply(options)

    if (callback != null) {
        request.into(object : ImageViewTarget<Drawable>(this) {
            override fun setResource(resource: Drawable?) {
                this@load.setImageDrawable(resource)
            }

            override fun onResourceReady(resource: Drawable?, transition: Transition<in Drawable>?) {
                super.onResourceReady(resource, transition)
                val bitmap = (resource as? BitmapDrawable)?.bitmap
                bitmap?.let {
                    this@load.setImageBitmap(it)
                    callback.invoke(it)
                }
            }
        })
    } else {
        request.into(this)
    }

}