package org.example;

import java.util.ArrayList;
/**
 * Класс реализует модель, которая преобразовывает изображения из RGB в YCbCr и обратно
 * */
public class ColorModel {

    /**Метод преобразует RGB матрицу в YCbCr
     * @return ArrayList<int[][]> - возвращаемым значением будет список матриц:
     * Матрица Y имеет индекс 0.
     * Матрица Cb имеет индекс 1.
     * Матрица Cr имеет индекс 2.
     * */
    public static ArrayList<int[][]> fromRgbToYCbCr(int[][] rgbData){

        int height = rgbData.length;
        int width = rgbData[0].length;
        ArrayList<int[][]> list = new ArrayList<>();

        int[][] yData = new int[height][width];
        int[][] cbData = new int[height][width];
        int[][] crData = new int[height][width];


        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                /* pixel содержит информацию о цвете в формате RGB,
                где младшие 8 бит представляют синюю компоненту,
                следующие 8 бит — зеленую,
                а старшие 8 бит — красную.*/
                int pixel = rgbData[i][j];

                // Выделяем каждый цвет при помощи сдвига
                int red = (pixel & 0xFF0000) >> 16;
                int green = (pixel & 0x00FF00) >> 8;
                int blue = pixel & 0x0000FF;

                yData[i][j] = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                cbData[i][j] = (int) (-0.169 * red - 0.331 * green + 0.5 * blue + 128);
                crData[i][j] = (int) (0.5 * red - 0.419 * green - 0.081 * blue + 128);
            }
        }

        list.add(0,yData);
        list.add(1,cbData);
        list.add(2,crData);

        return list;
    }

    public static int[][] fromYCbCrToRgb(ArrayList<int[][]> yCbCrData) {
        int height = yCbCrData.get(0).length;
        int width = yCbCrData.get(0)[0].length;
        int[][] rgbData = new int[height][width];

        int[][] yData = yCbCrData.get(0);
        int[][] cbData = yCbCrData.get(1);
        int[][] crData = yCbCrData.get(2);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int y = yData[i][j];
                int cb = cbData[i][j] - 128;
                int cr = crData[i][j] - 128;

                int red = (int) (y + 1.402 * cr);
                int green = (int) (y - 0.344136 * cb - 0.714136 * cr);
                int blue = (int) (y + 1.772 * cb);

                // Ограничиваем значения компонентов цвета в пределах от 0 до 255
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                // Комбинируем значения компонентов цвета
                rgbData[i][j] = (red << 16) | (green << 8) | blue;
            }
        }

        return rgbData;
    }
}
