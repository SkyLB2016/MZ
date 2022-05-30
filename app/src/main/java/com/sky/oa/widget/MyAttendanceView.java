package com.sky.oa.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.sky.oa.R;
import com.sky.oa.entity.MyAttentanceEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/5/12 2:50 下午
 * @Version: 1.0
 */
public class MyAttendanceView extends View {

    //小圆半径
    int radius = getContext().getResources().getDimensionPixelSize(R.dimen.wh_4);
    //小圆与文字的间隔
    int drawablePadding = getContext().getResources().getDimensionPixelSize(R.dimen.wh_5);
    //每行的间隔
    int padding = getContext().getResources().getDimensionPixelSize(R.dimen.wh_20);

    private List<Integer> colors = new ArrayList<>();//进度条的颜色
    private List<String> attendanceStatus = new ArrayList<>();//考勤状态

    private List<String> weeks = new ArrayList<>();//一周的文字周文字
    private List<MyAttentanceEntity> attentances = new ArrayList<>();//考勤信息

    private int selectDay = -1;//点击后选中的位置
    private List<Rect> rects = new ArrayList<>();//每天日期的点击事件
    private OnItemClickListener itemClickListener;

    public MyAttendanceView(Context context) {
        this(context, null);
    }

    public MyAttendanceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyAttendanceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        circleItemSpace = getContext().getResources().getDimensionPixelSize(R.dimen.wh_30);

        colors.clear();
        colors.add(R.color.color_BFE12A);
        colors.add(R.color.color_F86A55);
        colors.add(R.color.color_FFC512);
        colors.add(R.color.color_4C97EE);
        colors.add(R.color.color_8B6AFF);

        attendanceStatus.clear();
        attendanceStatus.add("正常");
        attendanceStatus.add("缺卡");
        attendanceStatus.add("迟到早退");
        attendanceStatus.add("请假");
        attendanceStatus.add("外勤");

        weeks.clear();
        weeks.add("一");
        weeks.add("二");
        weeks.add("三");
        weeks.add("四");
        weeks.add("五");
        weeks.add("六");
        weeks.add("日");

        attentances.clear();
        long current = System.currentTimeMillis();
//        long date = 24 * 60 * 60 * 1000;
//        long start = current - date * 3;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(current);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int startDay = day;
        if (startDay > 4) {
            startDay -= 3;
        }

        selectDay = day;//当天
        MyAttentanceEntity entity;
        for (int i = 0; i < 7; i++) {
            entity = new MyAttentanceEntity();
            entity.setDate(current);
            entity.setDays(startDay++ + "");
            if (startDay > 31) {
                startDay = 1;
            }
            entity.setToday(startDay == day);
            entity.setWorkTime("早班+晚班   工时：8h");

//            entity.setOver(false);//false,是否加班
//            entity.setLeaves();//false,是否休假
//            entity.setOnBusiness();//false,是否调休
//            entity.setOutdoor();//false是否外勤
            attentances.add(entity);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int height = getPaddingTop() + getPaddingBottom() + 600;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasCircle(canvas);
    }

    private void canvasCircle(Canvas canvas) {
        //起始的左右上下间距
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();

        //占据的宽度
        int width = getMeasuredWidth() - left - right;

        //画圆的画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(getContext().getResources().getDimension(R.dimen.text_12));
        textPaint.setColor(getContext().getResources().getColor(R.color.color_666666));
        Paint.FontMetricsInt metrics = textPaint.getFontMetricsInt();
        int textHeight = metrics.bottom - metrics.top;

        //计算圆心，需要比较半径与文字高度的一半比较大小
        int circleCenterY = Math.max(top + textHeight / 2, top + radius);
        int textWidthTotal = 0;//文字的累计宽度
        int baseline = circleCenterY + textHeight / 2 - metrics.bottom;
        //开始绘制考勤状态
        for (int i = 0; i < attendanceStatus.size(); i++) {
            paint.setColor(getContext().getResources().getColor(colors.get(i)));
            //小圆
            canvas.drawCircle(left + radius + textWidthTotal + (radius * 2 + drawablePadding + padding) * i, circleCenterY, radius, paint);
            //文字
            canvas.drawText(attendanceStatus.get(i), left + textWidthTotal + (radius * 2 + drawablePadding) * (i + 1) + padding * i, baseline, textPaint);
            //文字的宽度需要累加
            textWidthTotal += textPaint.measureText(attendanceStatus.get(i));
        }

        //周文字距离考勤状态的距离
        int topMarg = getContext().getResources().getDimensionPixelSize(R.dimen.wh_16);
        int weekTop = 0;
        //计算周文字开始的Y值
        if (textHeight > radius * 2) {
            weekTop = top + textHeight + topMarg;
        } else {
            weekTop = top + radius * 2 + topMarg;
        }

        textPaint.setTextAlign(Paint.Align.CENTER);
        //weeks把宽分成分七份，文字居中
        int everyWidth = width / 7;
        int weekBaseline = weekTop + textHeight - metrics.bottom;//周文字的基线
        int dayBaseline = weekTop + textHeight + topMarg + textHeight - metrics.bottom;//每天日期的基线
        int dayBottom = (int) (weekTop + textHeight + topMarg + textHeight * 1.5);//日期的底部边

        //每天排班信息的背景框
        Drawable textbg = ContextCompat.getDrawable(getContext(), R.drawable.shape_f5f5f5_radius_10);
        Rect textBgRect = new Rect(left, dayBottom + radius * 2 + topMarg / 2, width, dayBottom + radius * 2 + topMarg / 2 + textHeight * 3);
        textbg.setBounds(textBgRect);
        textbg.draw(canvas);

        MyAttentanceEntity entity;
        //今天的背景框
        Drawable dayBg = ContextCompat.getDrawable(getContext(), R.drawable.shape_circle_e2ebff);
        Rect dayBgRect;//item背景框所在矩形
        rects.clear();//点击事件先清空
        for (int i = 0; i < weeks.size(); i++) {
            int centerX = everyWidth / 2 + everyWidth * i;
            canvas.drawText(weeks.get(i), centerX, weekBaseline, textPaint);
            canvas.drawText(topMarg + "", centerX, weekBaseline + 50, textPaint);

            entity = attentances.get(i);
            int bgCenterY = weekTop + textHeight + topMarg + textHeight / 2;
            dayBgRect = new Rect(centerX - textHeight, bgCenterY - textHeight, centerX + textHeight, bgCenterY + textHeight);
            rects.add(dayBgRect);//点击日期时，需要知道比对点击的位置
            if (selectDay == i) {
                textPaint.setColor(getContext().getResources().getColor(R.color.color_437DFF));
                dayBg.setBounds(dayBgRect);
                dayBg.draw(canvas);
//                canvas.drawLine(left,dayBgRect.top,width,dayBgRect.top,paint);
//                canvas.drawLine(left,dayBgRect.bottom,width,dayBgRect.bottom,paint);

                textPaint.setColor(getContext().getResources().getColor(R.color.color_666666));
                textPaint.setTextAlign(Paint.Align.LEFT);
                int line = dayBottom + radius * 2 + topMarg / 2 + textHeight * 2 - metrics.bottom;
                //当天的排班信息
                canvas.drawText(entity.getWorkTime(), left + topMarg, line, textPaint);

                textPaint.setTextAlign(Paint.Align.CENTER);
            }
            //画对应天的对应日期
            canvas.drawText(entity.getDays(), centerX, dayBaseline, textPaint);

            for (int j = 0; j < attendanceStatus.size(); j++) {
                paint.setColor(getContext().getResources().getColor(colors.get(j)));
                canvas.drawCircle(centerX + radius * (j - 2), dayBottom + radius, radius, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                onClick((int) event.getX(), (int) event.getY());
                invalidate();
                break;
        }
        return true;
    }

    private void onClick(int x, int y) {
        int index = -1;
        for (int i = 0; i < rects.size(); i++) {
            if (rects.get(i).contains(x, y)) {
                index = i;
            }
        }
        if (index < 0) {
            return;
        }
        selectDay = index;
        if (itemClickListener != null)
            itemClickListener.onItemClick(index);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}