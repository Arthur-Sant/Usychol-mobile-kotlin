package com.project.usychol

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.core.content.ContextCompat
import com.project.usychol.databinding.ButtonComponentBinding

class ButtonComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var title: String? = null
    private var bgColor: String? = null

    private val binding = ButtonComponentBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }

    private fun setLayout(attrs: AttributeSet?){
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.ButtonComponent
            )

            val backgroundSet = attributes.getResourceId(R.styleable.ButtonComponent_btn_bg_color, 0)
            if(backgroundSet != 0) {

                bgColor = context.getString(backgroundSet)

                val drawable = if(bgColor == "#ffffffff" || bgColor == "#ff626262"){
                    R.drawable.button_background_white_to_black
                }else{
                    R.drawable.button_background_purple
                }
                setBackgroundResource(drawable)
            }

            val titleSet = attributes.getResourceId(R.styleable.ButtonComponent_btn_title, 0)

            if(titleSet != 0){
                title = context.getString(titleSet)
                binding.btnDsView.text = title
            }

            val textColorSet = attributes.getResourceId(R.styleable.ButtonComponent_btn_text_color, 0)

            if(textColorSet != 0){
                binding.btnDsView.setTextColor(
                    (ContextCompat.getColor(
                        context,
                        textColorSet
                    ))
                )
            }

            attributes.recycle()
        }
    }

}
