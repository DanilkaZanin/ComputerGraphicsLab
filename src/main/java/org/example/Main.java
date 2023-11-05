package org.example;

import org.example.easyLabV2.Operator;
import org.example.entity.Image;
import org.example.out.ImageSaver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = "src/main/java/org/example/images/Img1.jpg";

        Image myImage = new Image(path);

        Image newImage = Operator.applySobelOperator(myImage);

        ImageSaver.saveImage(newImage,"Jopa.png");
    }
}