package xyz.kots.circleapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 Рисуем на экране
 */

public class CanvasView extends View{

    private MainCircle mainCircle;
    private Paint paint;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initMainCircle();
        initPaint();
    }

    private void initPaint() {
        // инициализируем кисточку для рисования
        paint = new Paint();
        // устанавливаем сглаживание круга
        paint.setAntiAlias(true);
        // заполняем круг
        paint.setStyle(Paint.Style.FILL);
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(200,500);
    }

    //Содержимое данного метода отображается на экране
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        //рисуем круг
        canvas.drawCircle(mainCircle.getX(),mainCircle.getY(), mainCircle.getRadius(), paint);

    }
}
