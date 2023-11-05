package org.example;

import org.example.easyLabV2.laplasianOperator.LaplacianOperator;
import org.example.entity.Image;
import org.example.nenyjnoe.ColorModel;
import org.example.out.ImageSaver;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = "src/main/java/org/example/images/Img1.jpg";

        Image myImage = new Image(path);

        Image newImage = LaplacianOperator.applyLaplacianOperator(myImage);

        ImageSaver.saveImage(newImage,"Jopa.png");
    }
}