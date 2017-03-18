package xyz.kots.circleapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 Рисуем на экране
 */

public class CanvasView extends View implements ICanvasView{

    private static int width;
    private static int height;

    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initWidthAndHeight(context);
        initPaint();
        //выделяем память -> передаем ссылку на себя, что-бы менеджер мог обращаться к View
        gameManager = new GameManager(this, width, height);
    }


    private void initPaint() {
        // инициализируем кисточку для рисования
        paint = new Paint();
        // устанавливаем сглаживание круга
        paint.setAntiAlias(true);
        // заполняем круг
        paint.setStyle(Paint.Style.FILL);
    }

    private void initWidthAndHeight(Context context) {
        //получаем у контекста системный сервис
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //получаем дислей устройства
        Display display = windowManager.getDefaultDisplay();
        //создаем точку
        Point point = new Point();
        //передаем display точку -> возвращается точка с координатами правого нижнего угла-> значение в point меняется по ссылке
        display.getSize(point);
        //получаем координаты
        width = point.x;
        height = point.y;
    }

    //Содержимое данного метода отображается на экране
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // сохраняем ссылку на сам canvas -> создаем поле для временного хранения canvas
        this.canvas = canvas;

        gameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        //устанавливаем цвет кисточки
        paint.setColor(circle.getColor());
        //рисуем круг
        canvas.drawCircle(circle.getX(),circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    // метод вызывается при касании пальца экрана
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // координаты касания пальца через event
        int x = (int) event.getX();
        int y = (int) event.getY();

        //узнаем, какое именно событие произошло и даигается ли палец по экрану
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            gameManager.onTouchEvent(x,y);
        }
        //перерисовываем View после касания
        invalidate();
        return true;
    }
}
