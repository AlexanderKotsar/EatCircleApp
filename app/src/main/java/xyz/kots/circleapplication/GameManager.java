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


    public GameManager(CanvasView canvasView, int w, int h) {

        this.canvasView = canvasView;
        width = w;
        height = h;

        initMainCircle();
    }


    private void initMainCircle() {
        mainCircle = new MainCircle(width/2, height/2);
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);
    }
}
