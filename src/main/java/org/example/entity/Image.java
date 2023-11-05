package org.example.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Image {
    private int width;

    private int height;


    int[][] dataRGB;
    //int[][] dataYCbCr;

    public Image(String path) throws IOException {
        File file = new File(path);
        BufferedImage bufferedImage = ImageIO.read(file);

        if (bufferedImage != null) {
            this.width = bufferedImage.getWidth();
            this.height = bufferedImage.getHeight();

            this.dataRGB = new int[height][width];

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
    public Image(int x, int y ){
        width = x;
        height = y;

        dataRGB = new int[y][x];
    }

    public int getRGB(int x, int y){
        if(x > width + 1  || y > height + 1 ){
            throw new NoSuchElementException("OutOfIndexException");
        }
        else
            return dataRGB[y][x];
    }

    public void setRGB(int x, int y, int rgb){
        if(x > width || y > height){
            throw new NoSuchElementException("OutOfIndexException");
        }
        else
            dataRGB[y][x] = rgb;
    }

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



}
