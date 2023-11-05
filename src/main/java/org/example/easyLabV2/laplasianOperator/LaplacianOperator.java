package org.example.easyLabV2.laplasianOperator;

import org.example.entity.Image;

public class LaplacianOperator {
    public static Image applyLaplacianOperator(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();

        Image result = new Image(width, height);

        int[][] laplacianMask = {
                {0, 1, 0},
                {1, -4, 1},
                {0, 1, 0}
        };

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sum = 0;

                // Применяем маску оператора Лапласа к каждому пикселю
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int pixel = image.getRGB(x + i, y + j);
                        int gray = (int) (0.299 * ((pixel >> 16) & 0xFF) + 0.587 * ((pixel >> 8) & 0xFF) + 0.114 * (pixel & 0xFF));
                        sum += gray * laplacianMask[i + 1][j + 1];
                    }
                }

                // Обрезаем значения, чтобы они находились в пределах 0-255
                int newValue = Math.min(Math.max(sum, 0), 255);

                // Устанавливаем новое значение пикселя в результирующем изображении
                result.setRGB(x, y, (newValue << 16) | (newValue << 8) | newValue);
            }
        }

        return result;
    }
}