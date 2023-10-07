package org.example;

import java.util.ArrayList;
/**
 * Класс реализует модель, которая преобразовывает изображения из RGB в YCbCr и обратно
 * */
public class ColorModel {

    /**Метод преобразует RGB матрицу в YCbCr
     * @return ArrayList<int[][]> - возвращаемым значением буте список матриц:
     * Матрица Y имеет индекс 0.
     * Матрица Cb имеет индекс 1.
     * Матрица Cr имеет индекс 2.
     * */
    public static ArrayList<int[][]> fromRgbToYCbCr(int[][] rgbData, int width, int height){
        ArrayList<int[][]> list = new ArrayList<>();

        int[][] yData = new int[width][height];
        int[][] cbData = new int[width][height];
        int[][] crData = new int[width][height];


        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                /* pixel содержит информацию о цвете в формате RGB,
                где младшие 8 бит представляют синюю компоненту,
                следующие 8 бит — зеленую,
                а старшие 8 бит — красную.*/
                int pixel = rgbData[i][j];

                // Выделяем каждый цвет при помощи сдвига
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                yData[i][j] = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                cbData[i][j] = (int) (-0.168736 * red - 0.331264 * green + 0.5 * blue + 128);
                crData[i][j] = (int) (0.5 * red - 0.418688 * green - 0.081312 * blue + 128);
            }
        }

        list.add(0,yData);
        list.add(1,cbData);
        list.add(2,crData);

        return list;
    }

    public static int[][] fromYCbCrToRgb(int[][] yData,int[][] cbData,int[][] crData, int width, int height){
        int[][] a = {};

        return a;
    }
}
