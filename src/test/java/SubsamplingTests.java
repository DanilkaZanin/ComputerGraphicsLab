import org.example.ColorModel;
import org.example.Image;
import org.example.Subsampling;
import org.example.out.ImageSaver;
import org.junit.Test;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class SubsamplingTests {
    @Test
    public void heightAndWidthTest() throws IOException {
        String path = "src/main/java/org/example/Images/Img1.jpg";

        Image myImage = new Image(path);

        int height = myImage.getHeight();
        int width = myImage.getWidth();

        ArrayList<int[][]> list = ColorModel.fromRgbToYCbCr(myImage.getDataRGB());

        AbstractMap.SimpleEntry<int[][], int[][]> abstractMap = Subsampling.subsample420(list.get(1), list.get(2));

        int [][] arr = abstractMap.getKey();

        int cbHeight = arr.length;
        int cbWidth = arr[0].length;

        list.set(1, abstractMap.getKey());
        list.set(2, abstractMap.getValue());

        assertEquals(cbHeight * 2, height );
        assertEquals(cbWidth * 2, width );

        ImageSaver.saveChannelMatrix(arr,"cb.png","CB");
    }
}
