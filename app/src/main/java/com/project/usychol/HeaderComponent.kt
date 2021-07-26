package com.project.usychol

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.project.usychol.databinding.HeaderComponentBinding

class HeaderComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = HeaderComponentBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.HeaderComponent
            )

            val textColorSet = attributes.getResourceId(R.styleable.HeaderComponent_header_text_color, 0)

            if(textColorSet != 0){
                binding.textView.setTextColor(
                    (ContextCompat.getColor(
                        context,
                        textColorSet
                    ))
                )

                binding.textView2.setTextColor(
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