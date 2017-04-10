package com.ankoma88.app.moviegrid.ui.views;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class AspectRatioLayout extends FrameLayout {

    private static final double WIDTH_RATIO = 3;
    private static final double HEIGHT_RATIO = 4;

    public AspectRatioLayout(Context context) {
        super(context);
    }

    public AspectRatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = (int) (HEIGHT_RATIO / WIDTH_RATIO * widthSize);
        int newHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightSpec);
    }

}
