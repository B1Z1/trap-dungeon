package util.loader.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoaderImage {
    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        InputStream inputStream = LoaderImage.class.getResourceAsStream("/" + path);

        try {
            image = ImageIO.read(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return image;
    }
}
