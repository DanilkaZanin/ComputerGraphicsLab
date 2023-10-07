package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private int width;

    private int height;


    int[][] dataRGB;
    int[][] dataYCbCr;


    public int[][] getDataRGB() {
        return dataRGB;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void setDataRGB(int[][] dataRGB) {
        this.dataRGB = dataRGB;
    }



    public Image(String path) throws IOException {
        File file = new File(path);
        BufferedImage bufferedImage = ImageIO.read(file);

        if (bufferedImage != null) {
            this.width = bufferedImage.getWidth();
            this.height = bufferedImage.getHeight();

            this.dataRGB = new int[width][height];

            // Обход каждого пикселя изображения и запись его значения в матрицу
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int rgb = bufferedImage.getRGB(j, i); // Получение значения цвета пикселя
                    dataRGB[i][j] = rgb; // Запись значения в матрицу
                }
            }
        }else {
            throw new IOException("Failed to read image: " + path);
        }

    }

}
