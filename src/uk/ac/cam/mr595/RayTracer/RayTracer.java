package uk.ac.cam.mr595.RayTracer;

import java.awt.*;
import java.io.IOException;

public class RayTracer {

    private Scene scene;
    private static final int width = 1920;
    private static final int height = 1080;

    public RayTracer() {
        this.scene = new Scene();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world!");

        Render r = new Render(width, height);
        Color white = new Color(255, 255, 255);
        for (int i = width/2-50; i < width/2+50; i++) {
            for (int j = height/2-50; j < height/2+50; j++) {
                r.putPixel(i, j, white);
            }
        }
        r.savePNG("test.png");
    }

}
