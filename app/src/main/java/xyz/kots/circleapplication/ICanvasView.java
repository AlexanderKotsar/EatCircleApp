package xyz.kots.circleapplication;

/**
 * Created by KotsarAA on 15.03.2017.
 */

public interface ICanvasView {
    void drawCircle(SimpleCircle mainCircle);

    void redraw();

    void showMessage(String text);

    //void redraw();
}
