package xyz.kots.circleapplication;

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
        //создаем область неприкосновенности вокруг главного круга(круг большего размера, чем наш)
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();

        circles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < MAX_CIRCLES; i++) {
            //создаем круг
            EnemyCircle circle;
            do {
                //инициализируем круг рандомным кругом
                circle = EnemyCircle.getRandomCircle();
            //если круг пересекается с областью вокругнашего круга создаем его еще раз
            } while (circle.isIntersect(mainCircleArea));
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

        //проверяем пересечение
        cheсkCollision();

        // передвигаем остальные круги при касании
        moveCircles();
    }

    private void cheсkCollision() {
        for (EnemyCircle circle : circles) {
            if (mainCircle.isIntersect(circle)){
                gameEnd();
            }
        }
    }

    //перезапуск игры при касании к вражескому кругу
    private void gameEnd() {
        //восстанавливаем главный круг
        mainCircle.initRadius();
        //инициализируем врожеские круги
        initEnemyCircles();
        //перерисовка View
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle circle : circles) {
            circle.moveOnStep();
        }
    }
}
