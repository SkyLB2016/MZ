package com.sky.oa.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.sky.common.utils.ScreenUtils;
import com.sky.oa.R;

import java.util.ArrayList;
import java.util.List;

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

    private void canvasCircle(Canvas canvas) {
        int left = getPaddingLeft() + 50;
        int right = getMeasuredWidth() - getPaddingRight() - 50;
        int top = getPaddingTop() + 100;
        int height = getMeasuredHeight();

        TextPaint textP = new TextPaint();
        textP.setTextSize(getContext().getResources().getDimension(R.dimen.text_12));
        textP.setTextAlign(Paint.Align.LEFT);

        Paint.FontMetricsInt metrics = textP.getFontMetricsInt();//文本的基线数据
        int textHeight = metrics.bottom - metrics.top;//文本框所占的高度
        //计算的文字所在的背景框的左侧，顶部，右侧，底部
        int leftX = left;
        int topY = top - textHeight / 2;
        float baseline = top + textHeight / 2 - metrics.bottom;
        List<String> leftText = new ArrayList<>();
        for (int i = 7; i >= 0; i--) {
            leftText.add(i + "");
        }
        for (int i = 0; i < 8; i++) {
            canvas.drawText(leftText.get(i), leftX, baseline + 50 * i, textP);//画入画布中
        }


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
//        canvas.drawCircle(area.right + radius, area.centerY() + radius / 2, radiusSmall, paint);

        leftX += 60;
        paint.setColor(Color.parseColor("#B4B4B4"));
        canvas.drawLine(leftX, 450, right, 450, paint);

        //画虚线
        paint.setPathEffect(new DashPathEffect(new float[]{10f, 20f}, 0f));//虚线
        for (int i = 0; i < 8; i++) {
            canvas.drawLine(leftX, top + 50 * i, right, top + 50 * i, paint);
        }

        List<String> bottomText = new ArrayList<>();
        bottomText.add("入职");
        bottomText.add("转正");
        bottomText.add("调薪");
        bottomText.add("调岗");
        bottomText.add("离职");
        int part = (right - leftX) / 5;

        textP.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < 5; i++) {
            canvas.drawText(bottomText.get(i), leftX + part / 2 + part * i, baseline + 50 * 7 + 50, textP);//画入画布中
        }


        RectF rectF = new RectF();
        for (int i = 0; i < 5; i++) {

            rectF.left = leftX + part / 2 - 5 - 18 + part * i;
//            rectF.right = leftX + part / 2 - 5;
            rectF.right = rectF.left + 18;
            rectF.top = top + 50 * 4;
            rectF.bottom = top + 50 * 7;

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor("#437DFF"));
            canvas.drawRect(rectF, paint);

            //柱形图上的小圆盖
            paint.setColor(Color.parseColor("#437DFF"));
            canvas.drawCircle(rectF.left + 9, rectF.top, 9, paint);


            rectF.left = leftX + part / 2 + 5 + part * i;
            rectF.right = rectF.left + 18;
            rectF.top = top + 50 * 2;
            paint.setColor(Color.parseColor("#06CAFD"));
            canvas.drawRect(rectF, paint);
            //柱形图上的小圆盖
            paint.setColor(Color.parseColor("#06CAFD"));
            canvas.drawCircle(rectF.left + 9, rectF.top, 9, paint);

        }


    }
}
