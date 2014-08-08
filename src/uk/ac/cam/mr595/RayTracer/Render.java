package uk.ac.cam.mr595.RayTracer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class Render {
    private Color[][] pixels;
    private final int width;
    private final int height;

    public Render(int width, int height) {
        pixels = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = new Color(0, 0, 64);
            }
        }
        this.width = width;
        this.height = height;
    }

    public void savePNG(String filename) throws IOException {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, width, height, toIntArray(), 0, width);
        ImageIO.write(img, "png", new File(filename));
    }

    private int[] toIntArray() {
        int[] result = new int[width*height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[j*width + i] = pixels[i][j].getRGB();
            }
        }
        return result;
    }

    public void putPixel(int x, int y, Color color) {
        pixels[x][y] = color;
    }

}
