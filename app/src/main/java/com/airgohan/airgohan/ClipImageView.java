package com.airgohan.airgohan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by masaya3 on 15/08/29.
 * http://awwa500.blogspot.jp/2010/05/view.html
 */
public class ClipImageView extends ImageView {
    public ClipImageView(Context context) {
        super(context);
    }
    public ClipImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Path pathCircle = new Path();

    /**
     * Viewのサイズ確保
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
    // 丸いパス
        pathCircle.addCircle(w/2, h/2, w/2, Path.Direction.CW);
    }

    /**
     * 描画処理
     */
    protected void onDraw(Canvas canvas)
    {
        // パスに沿って切り取り
        canvas.clipPath(pathCircle);
        /*
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(200);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, paint);
        */
        super.onDraw(canvas);
    }

}
