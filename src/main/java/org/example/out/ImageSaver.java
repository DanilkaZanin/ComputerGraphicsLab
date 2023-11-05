package org.example.out;

import org.example.entity.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageSaver {
    public static void saveChannelMatrix(int[][] channelMatrix, String outputFileName, String type) {
        int width = channelMatrix[0].length;
        int height = channelMatrix.length;

        // Создаем новое изображение с размерами матрицы и типом цветовой модели
        BufferedImage image;

        if(type.equals("GRAY")){
            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        }else if(type.equals("CB")|| type.equals("CR")){
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }else{
            return;
        }

        // Проходим по каждому пикселю и устанавливаем значения из матрицы
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int value = channelMatrix[y][x];
                if(type.equals("GRAY")){
                    int rgb = (value << 16) | (value << 8) | value;
                    image.setRGB(x, y, rgb);
                }else if(type.equals("CR")){
                    int rgb = value << 16;
                    image.setRGB(x, y, rgb);
                }else {
                    image.setRGB(x,y,value);
                }

            }
        }

        // Сохраняем изображение в файл
        try {
            File outputFile = new File(outputFileName);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Изображение сохранено в " + outputFileName);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении изображения: " + e.getMessage());
        }
    }

    public static void saveImage(Image image, String fileName){
        // Создаем новое изображение с размерами матрицы и типом цветовой модели
        BufferedImage newImage =  new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int value = image.getRGB(x,y);
                newImage.setRGB(x,y,value);
            }
        }

        // Сохраняем изображение в файл
        try {
            File outputFile = new File(fileName);
            ImageIO.write(newImage, "png", outputFile);
            System.out.println("Изображение сохранено в " + fileName);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении изображения: " + e.getMessage());
        }
    }
}