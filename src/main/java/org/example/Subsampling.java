package org.example;

import java.util.AbstractMap;
public class Subsampling {

    // Метод для применения субдискретизации 4:2:0 к изображению
    public static AbstractMap.SimpleEntry<int[][],int[][]> subsample420(int[][] cbChannel, int[][] crChannel) {
        int height = cbChannel.length;
        int width = cbChannel[0].length;

        // Новая высота и ширина - половина оригинальных значений
        int newHeight = height / 2;
        int newWidth = width / 2;

        // Создаем новые матрицы для каналов Cb и Cr
        int[][] subsampledCb = new int[newHeight][newWidth];
        int[][] subsampledCr = new int[newHeight][newWidth];

        // Производим 4:2:0 субдескретизацию
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                // Берем значения из оригинальной матрицы, обычно это левый верхний пиксель
                subsampledCb[y][x] = cbChannel[y * 2][x * 2];
                subsampledCr[y][x] = crChannel[y * 2][x * 2];
            }
        }

        // Возвращаем результат субдискретизации
        return new AbstractMap.SimpleEntry<>(subsampledCb,subsampledCr);
    }

}
