package com.best.core.other

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

fun ImageView.load(url : String){
    Glide.with(this).load(url).into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutResId : Int) : View=
    LayoutInflater.from(context).inflate(layoutResId,this,false)

fun EditText.listenChanges(blockAction : () -> Unit){
    addTextChangedListener(object : SimpleTextChangeListener() {
        override fun afterTextChanged(p0: Editable?) {
            blockAction()
        }
    })
}

suspend fun ResponseBody.stringSuspending() : String =
    withContext(Dispatchers.IO){string()}