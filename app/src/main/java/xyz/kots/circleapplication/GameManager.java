package xyz.kots.circleapplication;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 Выделение логики приложения в отдельный класс GameManager
 */

public class GameManager {

    private MainCircle mainCircle;

    private CanvasView canvasView;
    private static int width;
    private static int height;

    private Paint paint;

    public GameManager(CanvasView canvasView, int w, int h) {

        this.canvasView = canvasView;
        width = w;
        height = h;

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
        mainCircle = new MainCircle(width/2, height/2);
    }

    public void onDraw(Canvas canvas) {
        //рисуем круг
        canvas.drawCircle(mainCircle.getX(),mainCircle.getY(), mainCircle.getRadius(), paint);
    }
}
