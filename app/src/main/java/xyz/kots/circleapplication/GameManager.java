package xyz.kots.circleapplication;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 Выделение логики приложения в отдельный класс GameManeger
 */

public class GameManager {

    private MainCircle mainCircle;
    private Paint paint;

    public GameManager() {

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

    public void onDraw(Canvas canvas) {
        //рисуем круг
        canvas.drawCircle(mainCircle.getX(),mainCircle.getY(), mainCircle.getRadius(), paint);
    }
}
