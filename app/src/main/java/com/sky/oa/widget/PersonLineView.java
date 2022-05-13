package com.sky.oa.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.sky.oa.R;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/5/12 2:50 下午
 * @Version: 1.0
 */
public class PersonLineView extends View {

    public PersonLineView(Context context) {
        this(context, null);
    }

    public PersonLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PersonLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasCircle(canvas);
    }

    float radius = 200;//大圆半径
    float circleLineSize = 30f;//大圆线粗
    int radiusSmall = 24;//小圆半径
    String total = "190人";
    String mark01 = "正式员工28人";
    String mark02 = "劳务派遣9人";
    String mark03 = "试用员工9人";

    private void canvasCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(circleLineSize);
        float left = getPaddingLeft() + circleLineSize / 2;
        float top = getPaddingTop() + circleLineSize / 2;
        float right = left + radius * 2;
        float bottom = top + radius * 2;
        RectF area = new RectF(left, top, right, bottom);


        float start = -90f;//起始角度
        float sweep = 225f;//旋转角度
        paint.setColor(Color.parseColor("#437DFF"));
        canvas.drawArc(area, start, sweep, false, paint);

        start += sweep + 2;
        sweep = 63f;
        paint.setColor(Color.parseColor("#06CAFD"));
        canvas.drawArc(area, start, sweep, false, paint);


        start += sweep + 2;
        sweep = 66f;
        paint.setColor(Color.parseColor("#FFC512"));
        canvas.drawArc(area, start, sweep, false, paint);

        //小圆
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(area.right + radius, area.centerY() - radius / 2, radiusSmall, paint);

        paint.setColor(Color.parseColor("#06CAFD"));
        canvas.drawCircle(area.right + radius, area.centerY(), radiusSmall, paint);

        paint.setColor(Color.parseColor("#FFC512"));
        canvas.drawCircle(area.right + radius, area.centerY() + radius / 2, radiusSmall, paint);


        TextPaint textP = new TextPaint();
        textP.setTextSize(getContext().getResources().getDimension(R.dimen.text_18));
        textP.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetricsInt metrics = textP.getFontMetricsInt();//文本的基线数据
        int textHeight = metrics.bottom - metrics.top;//文本框所占的高度
//        textP.getTextBounds(text, 0, text.length, textBound)//不建议用

        //计算的文字所在的背景框的左侧，顶部，右侧，底部
        Float leftX = left + radius / 2;
        Float topY = top + radius - textHeight - 10;
        Float rightX = left + radius / 2 * 3;
        Float bottomY = top + radius - 10;
        //文字背景所在矩形
        Rect textRect = new Rect(leftX.intValue(), topY.intValue(), rightX.intValue(), bottomY.intValue());
//        Drawable textbg = ContextCompat.getDrawable(getContext(), R.drawable.shape_ffc107);
//        textbg.setBounds(textRect);//为文字设置背景
//        textbg.draw(canvas);//画入画布中
        //让文字居于背景中间，计算文字的左距离与底部距离
        float baseline = textRect.exactCenterY() + textHeight / 2 - metrics.bottom;
        canvas.drawText(total, textRect.exactCenterX(), baseline, textP);//画入画布中


        textP.setTextSize(getContext().getResources().getDimension(R.dimen.text_12));
//        textP.setPathEffect(new CornerPathEffect(20f));//圆角
//        textP.setTextAlign(Paint.Align.CENTER);
        textP.setColor(getContext().getResources().getColor(R.color.color_666666));


        metrics = textP.getFontMetricsInt();//文本的基线数据
        textHeight = metrics.bottom - metrics.top;//文本框所占的高度
//        textP.getTextBounds(text, 0, text.length, textBound)//不建议用

        //计算的文字所在的背景框的左侧，顶部，右侧，底部
//        leftX = left + radius / 2;
        topY = top + radius + 10;
//        rightX = left + radius / 2 * 3;
        bottomY = top + radius + 10 + textHeight;
        //文字背景所在矩形
        textRect = new Rect(leftX.intValue(), topY.intValue(), rightX.intValue(), bottomY.intValue());
//        Drawable textbg = ContextCompat.getDrawable(getContext(), R.drawable.shape_ffc107);
//        textbg.setBounds(textRect);//为文字设置背景
//        textbg.draw(canvas);//画入画布中
        //让文字居于背景中间，计算文字的左距离与底部距离
        baseline = textRect.exactCenterY() + textHeight / 2 - metrics.bottom;
        canvas.drawText("总在职员工", textRect.exactCenterX(), baseline, textP);//画入画布中


        textP.setTextAlign(Paint.Align.LEFT);
        baseline = area.centerY() - radius / 2 + textHeight / 2 - metrics.bottom;

        float leftMark = area.right + radius + radiusSmall * 3;
        canvas.drawText(mark01, leftMark, baseline, textP);//画入画布中

        baseline = area.centerY() + textHeight / 2 - metrics.bottom;
        canvas.drawText(mark02, leftMark, baseline, textP);//画入画布中


        baseline = area.centerY() + radius / 2 + textHeight / 2 - metrics.bottom;
        canvas.drawText(mark03, leftMark, baseline, textP);//画入画布中

    }
}
