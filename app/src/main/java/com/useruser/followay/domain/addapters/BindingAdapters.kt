package com.useruser.followay.domain.addapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.useruser.followay.R

object BindingAdapters {

    private val TAG = "BindingAdapters"

    @BindingAdapter("bind:src")
    @JvmStatic
    fun setImageViewImage(imageView: ImageView, path: String?){

        if (path.isNullOrBlank()){
            imageView.setBackgroundResource(R.drawable.defult_user_image)
        }else{
            imageView.load(path)
        }
    }

    @BindingAdapter("bind:error")
    @JvmStatic
    fun setError(textView: TextView, errorMessage: String?){
        if (!errorMessage.isNullOrBlank())
            textView.error = errorMessage
    }

    @BindingAdapter("bind:visible")
    @JvmStatic
    fun setViewVisible(view: View, visiblity: Int){
        view.visibility = visiblity
    }


    @BindingAdapter("bind:backgraund")
    @JvmStatic
    fun setBackgroundFromResId(view: View, id: Int){
        if (id != 0)
            view.setBackgroundResource(id)
    }

    @BindingAdapter("bind:text")
    @JvmStatic
    fun setTextFromResId(textView: TextView, id: Int){
        if (id != 0)
            textView.setText(id)
    }


    @BindingAdapter("bind:adapter")
    @JvmStatic
    fun setRecyclerAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>){
        recyclerView.adapter = adapter
    }

}