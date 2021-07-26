package com.project.usychol

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.usychol.databinding.InputComponentBinding

class InputComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var topText: String? = null
    private var textHint: String? = null
    private var inputType: String? = null

    private val binding = InputComponentBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }

    private fun setLayout(attrs: AttributeSet?){
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.InputComponent
            )

            val topTextSet = attributes.getResourceId(R.styleable.InputComponent_input_top_text, 0)

            if (topTextSet != 0){
                topText = context.getString(topTextSet)

                binding.topText.text = topText
            }

            val textHintSet = attributes.getResourceId(R.styleable.InputComponent_input_hint, 0)

            if(textHintSet != 0){
                textHint = context.getString(textHintSet)


                binding.textInput.hint = textHint
            }

            val inputTypeSet = attributes.getResourceId(R.styleable.InputComponent_input_type, 0)

            if(inputTypeSet != 0){
                inputType = context.getString(inputTypeSet)

                val currentInput = when(inputType) {
                    "email" -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    "password" -> InputType.TYPE_TEXT_VARIATION_PASSWORD
                    "text" -> InputType.TYPE_CLASS_TEXT
                    "date" -> InputType.TYPE_CLASS_TEXT
                    "number" -> InputType.TYPE_CLASS_NUMBER
                    else -> InputType.TYPE_CLASS_TEXT
                }

                binding.textInput.inputType = currentInput
            }

            attributes.recycle()
        }
    }

}