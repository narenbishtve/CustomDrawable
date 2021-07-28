package com.nsb.customdrawables.helper

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

class FootballField(val context: Context) : Drawable() {

    /// COLOR PATTERN 1
    private val grassColorDark = ResourcesCompat.getColor(context.resources, android.R.color.holo_green_dark, context.theme)
    private val grassColorLight = ResourcesCompat.getColor(context.resources, android.R.color.holo_green_light, context.theme)


    private val grassPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3.0f
        color = ResourcesCompat.getColor(context.resources, android.R.color.white, context.theme)
    }

    private val rect = Rect()
    private val halfInnerGoalWidth = 26f
    private val halfInnerGoalHeight = 22f

    private val halfGoalWidth = 62f
    private val halfGoalHeight = 42f

    private val cornerSize = 20f
    private val middleCircleSize = 34f
    private val middleInnerCircleSize = 8f

    /// FIELD PADDING
    private val fieldPadding = 8


    /// NUMBER OF ROWS
    private val NUM_OF_GRASS_ROWS = 10


    override fun draw(canvas: Canvas) {

        val width = bounds.width()
        val height = bounds.height()

        val rowHeight = height / NUM_OF_GRASS_ROWS

        canvas.apply {

            /// DRAWING BACKGROUND GRASS LAYOUT
            for (i in 0..NUM_OF_GRASS_ROWS) {
                val top = i * rowHeight
                rect.set(0, top, width, top + rowHeight)
                grassPaint.color = if (i % 2 == 0) grassColorDark else grassColorLight
                drawRect(rect, grassPaint)
            }

            /// DRAWING OUTER RECTANGLE
            linePaint.style = Paint.Style.STROKE
            rect.set(fieldPadding, fieldPadding, width - fieldPadding, height - fieldPadding)
            drawRect(rect, linePaint)


            /// DRAWING GOAL
            /// DRAWING INNER BOUNDRY
            rect.set((width / 2 - halfInnerGoalWidth.toInt()), fieldPadding, ((width / 2 + halfInnerGoalWidth).toInt()), (halfInnerGoalHeight + fieldPadding).toInt())
            drawRect(rect, linePaint)

            /// DRAWING OUTER BOUNDRY
            rect.set((width / 2 - halfGoalWidth.toInt()), fieldPadding, ((width / 2 + halfGoalWidth).toInt()), (halfGoalHeight + fieldPadding).toInt())
            drawRect(rect, linePaint)


            /// DRAWING CORNER ARCS
            /// LEFT TOP CORNER
            drawArc((fieldPadding - cornerSize).toFloat(), (fieldPadding - cornerSize).toFloat(), (fieldPadding + cornerSize).toFloat(), (fieldPadding + cornerSize).toFloat(), 0f, 90f, false, linePaint)

            /// RIGHT TOP CORNER
            drawArc((width - fieldPadding - cornerSize).toFloat(), (fieldPadding - cornerSize).toFloat(), (width - fieldPadding + cornerSize).toFloat(), (fieldPadding + cornerSize).toFloat(), 90f, 90f, false, linePaint)


            /// DRAWING CENTER BOTTOM OUTER CIRCLE
            drawArc((width / 2 - middleCircleSize), (height - fieldPadding - middleCircleSize).toFloat(), (width / 2 + middleCircleSize).toFloat(), (height - fieldPadding + middleCircleSize).toFloat(), 180f, 180f, false, linePaint)


            /// DRAWING CENTER BOTTOM INNER CIRCLE
            linePaint.style = Paint.Style.FILL

            drawArc(
                    (width / 2 - middleInnerCircleSize).toFloat(),
                    (height - fieldPadding - middleInnerCircleSize).toFloat(),
                    (width / 2 + middleInnerCircleSize).toFloat(),
                    (height - fieldPadding + middleInnerCircleSize).toFloat(),
                    180f,
                    180f,
                    false,
                    linePaint
            )


        }


    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE

    }

}