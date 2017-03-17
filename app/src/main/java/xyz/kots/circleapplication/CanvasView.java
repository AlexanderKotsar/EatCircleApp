package xyz.kots.circleapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 Рисуем на экране
 */

public class CanvasView extends View{

    private static int width;
    private static int height;

    private GameManager gameManager;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initWidthAndHeight(context);

        //выделяем память -> передаем ссылку на себя, что-бы менеджер мог обращаться к View
        gameManager = new GameManager(this, width, height);
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
        gameManager.onDraw(canvas);
    }
}
