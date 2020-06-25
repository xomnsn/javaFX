import java.util.Random;

public class ExamTask1 {

    public static void main(String[] args) throws Exception {

        // Random image

        PGMImage imageRandom = new PGMImage(80, 60);

        Random random = new Random();

        for (int y = 0; y < imageRandom.getHeight(); y++)
            for (int x = 0; x < imageRandom.getWidth(); x++)
                imageRandom.setPixel(x, y, random.nextInt(255));

        imageRandom.saveTo("imageRandom.pgm");


        // Gradient image

        PGMImage imageGradient = new PGMImage(80, 60);

        for (int y = 0; y < imageGradient.getHeight(); y++)
            for (int x = 0; x < imageGradient.getWidth(); x++)
                imageRandom.setPixel(x, y, (x + y) % 256);

        imageRandom.saveTo("imageGradient.pgm");

    }

}
