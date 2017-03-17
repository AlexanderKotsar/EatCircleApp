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

    private GameManager gameManager;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //выделяем память
        gameManager = new GameManager();
    }

    //Содержимое данного метода отображается на экране
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        gameManager.onDraw(canvas);
    }
}
