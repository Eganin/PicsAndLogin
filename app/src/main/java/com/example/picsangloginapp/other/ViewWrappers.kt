package com.example.picsangloginapp.other

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

interface TextContainer {
    fun show(text: String)
}

class MyTextView : AppCompatTextView, TextContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(text: String) = setText(text)

}

class MySnackBar(private val snackBar: Snackbar) : TextContainer {
    override fun show(text: String) = with(snackBar) {
        setText(text)
        show()
    }
}

interface ImageContainer {
    fun show(url: String)
}

class MyImageView : AppCompatImageView, ImageContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(url: String) = load(url = url)
}

interface ErrorContainer {
    fun show(show: Boolean, message: String)
}

class MyTextInputLayout : TextInputLayout, ErrorContainer {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(show: Boolean, message: String) {
        isErrorEnabled = show
        error = message
    }
}

interface ViewContainer {
    fun show(show: Boolean)

    fun enable(enable: Boolean)
}

class MyFrameLayout : FrameLayout, ViewContainer {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun enable(enable: Boolean) {
        isEnabled = enable
    }
}

class MyButton: AppCompatButton, ViewContainer {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun enable(enable: Boolean) {
        isEnabled = enable
    }
}