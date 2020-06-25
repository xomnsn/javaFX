import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PGMImage {

    private final int width;
    private final int height;
    private int[][] pixels;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public PGMImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[height][width];
    }


    public void setPixel(int x, int y, int color) {
        pixels[y][x] = color;
    }

    public void saveTo(String filename) throws Exception {
        try (PrintStream ps1 = new PrintStream(filename, StandardCharsets.UTF_8)) {
            ps1.println("P2");
            ps1.println(width + " " + height);
            ps1.println("255");
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    ps1.print(pixels[y][x]);
                    ps1.print(" ");
                }
                ps1.print("\n");
            }
        }
    }
}
