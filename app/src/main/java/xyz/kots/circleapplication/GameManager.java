package xyz.kots.circleapplication;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 Выделение логики приложения в отдельный класс GameManager
 */

public class GameManager {

    public static final int MAX_CIRCLES = 10;
    private MainCircle mainCircle;

    // коллекция для хранения вражеских кругов
    private ArrayList<EnemyCircle> circles;

    private CanvasView canvasView;
    private static int width;
    private static int height;


    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;

        width = w;
        height = h;

        initMainCircle();
        initEnemyCircles();
    }

    //инициализируем вражеские круги
    private void initEnemyCircles() {
        circles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < MAX_CIRCLES; i++) {
            //создаем круг
            EnemyCircle circle;
            //инициализируем круг рандомным кругом
            circle = EnemyCircle.getRandomCircle();
            //добавляем созданный круг в коллекцию
            circles.add(circle);
        }

        calculateAndSetCircleColor();
    }

    //рассчитываем и устанавливаем цвета наших кругов в зависимости от главного круга
    private void calculateAndSetCircleColor() {
        for (EnemyCircle circle : circles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    private void initMainCircle() {
        mainCircle = new MainCircle(width/2, height/2);
    }

    public void onDraw() {
        // рисуем главный круг
        canvasView.drawCircle(mainCircle);

        // рисуем вражеские круги
        for (EnemyCircle circle : circles) {
            canvasView.drawCircle(circle);
        }

    }

    public void onTouchEvent(int x, int y) {

        // передвигаем главный круг при касании
        mainCircle.moveMainCircleWhenTouch(x,y);
    }
}
