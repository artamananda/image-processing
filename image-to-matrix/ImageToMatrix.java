import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.PrintWriter;

public class ImageToMatrix {

    public static Color[][] loadPixelsFromFile(File file) throws IOException {

        BufferedImage image = ImageIO.read(file);
        Color[][] colors = new Color[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                colors[y][x] = new Color(image.getRGB(x, y));
            }
        }

        return colors;
    }

    public static void main(String[] args) throws IOException {
        Color[][] colors = loadPixelsFromFile(new File("image0.jpg"));
        int angka = 0;
        PrintWriter out = new PrintWriter("image0.txt"); 

        for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				angka = (colors[i][j].getRed()+colors[i][j].getGreen()+colors[i][j].getBlue())/3;
				out.print(angka);
				if(angka<10) {
					out.print("  ");
				}else if(angka<100) {
					out.print(" ");
				}else if(angka<1000) {
					out.print("");
				}
			}
            out.println();
		}

        out.close();
    }
}