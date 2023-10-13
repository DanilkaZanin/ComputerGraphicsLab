package org.example;

import org.example.out.ImageSaver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = "src/main/java/org/example/Images/Img1.jpg";

        Image myImage = new Image(path);

        ArrayList<int[][]> list = ColorModel.fromRgbToYCbCr(myImage.getDataRGB());

        ImageSaver.saveChannelMatrix(list.get(1),"cbImage.png","RGB");
    }
}
/*
    int pixelData[][] = list.get(0); // МЕНЯТЬ ТУТ


        int height = pixelData.length;
        int width = pixelData[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = pixelData[i][j];
                image.setRGB(j, i, rgb);
            }
        }

        try {
            File output = new File("imageY.png");
            ImageIO.write(image, "png", output);
            System.out.println("Изображение успешно создано.");
        } catch (Exception e) {
            e.printStackTrace();
        }
 */

/*  Для каждого из цветов


        int pixelData[][] = list.get(0); // МЕНЯТЬ ТУТ
        // Получение массива значений Y из списка
        int[][] yData = list.get(0);

        int height = pixelData.length;
        int width = pixelData[1].length;

        // Создание нового изображения с использованием значений Y
        BufferedImage yImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR); //TYPE_BYTE_GRAY
        WritableRaster raster = yImage.getRaster();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                raster.setSample(j, i, 0, yData[i][j]);
            }
        }
        yImage.setData(raster);

        // Сохранение изображения в файл
        File outputY = new File("cb_channel.png");
        ImageIO.write(yImage, "png", outputY);
*/

/*int pixelData[][] = ColorModel.fromYCbCrToRgb(list); // МЕНЯТЬ ТУТ


        int height = pixelData.length;
        int width = pixelData[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = pixelData[i][j];
                image.setRGB(j, i, rgb);
            }
        }

        try {
            File output = new File("imageY.png");
            ImageIO.write(image, "png", output);
            System.out.println("Изображение успешно создано.");
        } catch (Exception e) {
            e.printStackTrace();
        }*/