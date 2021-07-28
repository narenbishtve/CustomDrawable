package com.nsb.customdrawables.helper
import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView

class FieldView(context: Context, attrs: AttributeSet? = null) : CardView(context, attrs) {





    init {
        background = FootballField(context)
    }

}