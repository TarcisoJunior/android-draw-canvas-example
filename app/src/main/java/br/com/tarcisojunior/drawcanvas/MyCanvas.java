package br.com.tarcisojunior.drawcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyCanvas extends View {


    private Paint paint;
    private Path path;

    public int mode;

    public static int NONE = 0;
    public static int MARK = 1;
    public static int DRAW = 2;

    private static String TAG = "MyCanvas";

    public void setMode(int mode){
        this.mode = mode;
        //invalidate();
    }


    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (this.isClickable()) {
            float xPos = event.getX();
            float yPos = event.getY();

            Log.i(TAG, String.valueOf(mode));
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mode == DRAW) {
                        path.moveTo(xPos, yPos);
                        return true;
                    }else if (mode == MARK) {
                        path.addCircle(xPos, yPos, 5, Path.Direction.CCW);
                        return true;
                    }
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAW)
                        path.lineTo(xPos, yPos);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    return false;

            }

            invalidate();
        }
        return true;
    }

    public Paint getPaint(){
        return this.paint;
    }

    public Path getPath(){
        return this.path;
    }

    public void clear(){
        path.reset();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        paint.setStyle(Paint.Style.STROKE);

        invalidate();
    }
}
