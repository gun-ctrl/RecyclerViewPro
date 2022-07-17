package com.example.recyclerviewpro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Description
 * @Author PC
 * @QQ 1578684787
 */
class ModelDecoration extends RecyclerView.ItemDecoration {
    private final int groupHeaderHeight;

    private final Paint headPaint;
    private final Paint textPaint;

    private final Rect textRect;
    public ModelDecoration(Context context) {
        groupHeaderHeight = dp2px(context, 100);

        headPaint = new Paint();
        headPaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.WHITE);

        textRect = new Rect();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() instanceof ModelAdapter){
            ModelAdapter adapter = (ModelAdapter) parent.getAdapter();
            int count  = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right  = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < count; i++) {
                //获取对应的View
                View view = parent.getChildAt(i);
                //获取View的布局位置
                int position = parent.getChildLayoutPosition(view);
                //判断是不是头部
                boolean isGroupHeader = adapter.isGroupHeader(position);
                if (isGroupHeader && view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0){
                    c.drawRect(left,view.getTop()-groupHeaderHeight,right,view.getTop(),headPaint);
                    String groupName = adapter.getGroupName(position);
                    textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                    c.drawText(groupName,left+20,view.getTop()-groupHeaderHeight/2+textRect.height()/2,textPaint);

                }else if (view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0){
                    //分割线
                    c.drawRect(left,view.getTop()-4,right,view.getTop(),headPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter() instanceof ModelAdapter){
            ModelAdapter adapter = (ModelAdapter) parent.getAdapter();
            //返回可见区域的第一个item的position
            int position = ((LinearLayoutManager)parent.getLayoutManager()).findFirstVisibleItemPosition();
            //获取对应的position的View
            View itemView = parent.findViewHolderForAdapterPosition(position).itemView;
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = parent.getPaddingTop();
            //当下一个是组的头部时
            boolean isGroupHeader = adapter.isGroupHeader(position+1);
            String groupName = adapter.getGroupName(position);
            textPaint.getTextBounds(groupName,0,groupName.length(),textRect);
            if (isGroupHeader){
                //对顶部进行缩小效果，在视觉上是被下一个组的顶部推进去的
                int bottom = Math.min(groupHeaderHeight,itemView.getBottom()-parent.getPaddingTop());
                c.drawRect(left,top,right,top+bottom,headPaint);
                c.clipRect(left,top,right,top+bottom);
                c.drawText(groupName,left+20,top+bottom-groupHeaderHeight/2+textRect.height()/2,textPaint);
            }else {
                c.drawRect(left,top,right,top+groupHeaderHeight,headPaint);
                c.drawText(groupName,left+20,top+groupHeaderHeight/2+textRect.height()/2,textPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() instanceof ModelAdapter){
            ModelAdapter adapter = (ModelAdapter) parent.getAdapter();
            int position = parent.getChildAdapterPosition(view);
            boolean isGroupHeader = adapter.isGroupHeader(position);
            //判断itemView是不是头部
            if (isGroupHeader){
                //如果是头部，预留更大的地方
                outRect.set(0,groupHeaderHeight,0,0);
            }else {
                outRect.set(0,4,0,0);
            }
        }
    }

    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5f);
    }
}
