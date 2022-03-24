package com.sky.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 *
 * @Description: 流式布局
 * @Author: 李彬
 * @CreateDate: 2022/3/24 6:18 下午
 * @Version: 1.0
 */
class FlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {


    val allViews = mutableListOf<List<View>>()//所有的View，分行记录
    val lineHeights = mutableListOf<Int>()//每行的高度


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val layoutWidth = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val layoutHeight = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)


        allViews.clear()
        lineHeights.clear()
        val childCount = childCount

        var measureWidth = 0
        var measureHeight = 0
        var lineViews = mutableListOf<View>()
        //每行的宽高
        var lineWidth: Int = 0
        var lineHeight: Int = 0

        var child: View
        var childWidth: Int
        var childHeight: Int
        var lp: MarginLayoutParams

        val realWidth = layoutWidth - paddingLeft - paddingRight//刨除左右间距的实际宽高
        for (i in 0 until childCount) {
            child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            lp = child.layoutParams as MarginLayoutParams
            childWidth = child.measuredWidth + lp.leftMargin + lp.rightMargin
            childHeight = child.measuredHeight + lp.topMargin + lp.bottomMargin

            //计算每行的宽度，超过实际宽度，需要换行
            if (lineWidth + childWidth > realWidth) {
                //统计流布局的宽高,AT_MOST 模式需要使用
                measureWidth = measureWidth.coerceAtLeast(lineWidth)
                measureHeight += lineHeight

                //把此行的数据保存到集合里
                allViews.add(lineViews)
                lineHeights.add(lineHeight)
                lineViews = mutableListOf()

                //重置每行的宽高
                lineWidth = childWidth
                lineHeight = childHeight
            } else {
                lineWidth += childWidth
                lineHeight = lineHeight.coerceAtLeast(childHeight)
            }

            //最后一行的宽高不会被统计到，所以需要单独捕捉
            if (i===childCount-1){
                measureWidth=measureWidth.coerceAtLeast(lineWidth)
                measureHeight+=lineHeight
            }

            lineViews.add(child)


        }

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    override fun generateLayoutParams(attrs: AttributeSet?) = MarginLayoutParams(context, attrs)
    override fun generateLayoutParams(p: LayoutParams?) = MarginLayoutParams(p)
}