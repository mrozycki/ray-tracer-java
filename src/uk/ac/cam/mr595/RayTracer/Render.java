package uk.ac.cam.mr595.RayTracer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Render {
    private Color[][] pixels;
    private final int width;
    private final int height;

    public Render(int width, int height) {
        pixels = new Color[width][height];
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

    public Render subsample(int level) {
        Render result = new Render(width/level, height/level);

        for (int i = 0; i < width; i+=level) {
            for (int j = 0; j < height; j+=level) {
                int redSum = 0, greenSum = 0, blueSum = 0;
                for (int a = 0; a < level; a++) {
                    for (int b = 0; b < level; b++) {
                        redSum += pixels[i+a][j+b].getRed();
                        greenSum += pixels[i+a][j+b].getGreen();
                        blueSum += pixels[i+a][j+b].getBlue();
                    }
                }

                redSum /= level*level;
                greenSum /= level*level;
                blueSum /= level*level;

                result.putPixel(i/level, j/level, new Color(redSum, greenSum, blueSum));
            }
        }

        return result;
    }
}
