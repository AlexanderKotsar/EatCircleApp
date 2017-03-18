package xyz.kots.circleapplication;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by KotsarAA on 15.03.2017.
 */

public class EnemyCircle extends SimpleCircle
{

    public static final int FROM_RADIUS = 10;
    public static final int TO_RADIUS = 90;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOD_COLOR = Color.rgb(0, 200, 0);

    public EnemyCircle(int x, int y, int radius) {
        super(x, y, radius);
    }

    // создаем круг случайного размера
    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        //узнаем ширину и высоту у GameManager что-бы наше число было в пределах екрана
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        //создаем круг с полученными рандомными значениями
        EnemyCircle enemyCircle = new EnemyCircle(x,y,radius);
        enemyCircle.setColor(ENEMY_COLOR);
        return enemyCircle;
    }


    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {
        if (isSmallerThan(mainCircle)){
            setColor(FOOD_COLOR);
        }else
            setColor(ENEMY_COLOR);
    }

    private boolean isSmallerThan(SimpleCircle circle) {
        //сравниваем радиус текущего круга и круга
        if (radius < circle.radius){
            return true;
        }
        return false;
    }
}
