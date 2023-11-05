package org.example.easyLabV2.laplasianOperator;

import org.example.entity.Image;

import java.awt.*;

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
    public static Image applyRobertsOperator(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();

        Image result = new Image(width, height);

        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width - 1; x++) {
                // Получение цвета пикселей
                Color color1 = new Color(image.getRGB(x, y));
                Color color2 = new Color(image.getRGB(x + 1, y));
                Color color3 = new Color(image.getRGB(x, y + 1));

                // Конвертация цвета в оттенки серого
                int gray1 = (color1.getRed() + color1.getGreen() + color1.getBlue()) / 3;
                int gray2 = (color2.getRed() + color2.getGreen() + color2.getBlue()) / 3;
                int gray3 = (color3.getRed() + color3.getGreen() + color3.getBlue()) / 3;

                // Вычисление разностей интенсивности пикселей
                int gx = gray2 - gray1;
                int gy = gray3 - gray1;

                // Вычисление общей разности интенсивности
                int gradient = (int) Math.sqrt(gx * gx + gy * gy);

                // Нормализация значения градиента в диапазоне от 0 до 255
                gradient = Math.min(gradient, 255);

                // Установка значения цвета пикселя
                result.setRGB(x, y, new Color(gradient, gradient, gradient).getRGB());
            }
        }

        return result;
    }

    public static Image applySobelOperator(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();

        Image result = new Image(width, height);

        int[][] gx = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] gy = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int pixelX = 0;
                int pixelY = 0;

                // Применение масок gx и gy к окрестности пикселя
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int rgb = image.getRGB(x + i, y + j);
                        int gray = (int) (0.2989 * ((rgb >> 16) & 0xFF) + 0.5870 * ((rgb >> 8) & 0xFF) + 0.1140 * (rgb & 0xFF));

                        pixelX += gx[j + 1][i + 1] * gray;
                        pixelY += gy[j + 1][i + 1] * gray;
                    }
                }

                // Вычисление общей градиентной величины
                int gradient = (int) Math.sqrt(pixelX * pixelX + pixelY * pixelY);

                // Установка значения пикселя в результатирующем изображении
                result.setRGB(x, y, gradient << 16 | gradient << 8 | gradient);
            }
        }

        return result;
    }
}