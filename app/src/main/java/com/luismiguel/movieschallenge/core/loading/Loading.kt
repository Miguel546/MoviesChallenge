package com.luismiguel.movieschallenge.core.loading

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.luismiguel.movieschallenge.R
import com.luismiguel.movieschallenge.databinding.LayoutLoadingBinding

class Loading @JvmOverloads constructor(context: Context,
                                        attrs: AttributeSet? = null): ConstraintLayout(context, attrs) {
    private var bindingLoading : LayoutLoadingBinding = LayoutLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    private var backColor: Int = ContextCompat.getColor(context, R.color.tran50)
    init {
        setupUI()
    }

    private fun setupUI(){
        bindingLoading.container.setBackgroundColor(backColor)
    }
}