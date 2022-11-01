package converter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Citra {
    public static int[][] imgToMtx(File file)throws IOException{
        BufferedImage image = ImageIO.read(file);
        Color[][] colors = new Color[image.getWidth()][image.getHeight()];
        int[][] colorMatrix = new int[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                colors[y][x] = new Color(image.getRGB(x, y));
            }
        }

        for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				colorMatrix[i][j] = (colors[i][j].getRed()+colors[i][j].getGreen()+colors[i][j].getBlue())/3;
			}
		}

        return colorMatrix;
    }

    public static void mtxToImg(int[][] mtx)throws IOException{
        BufferedImage image = new BufferedImage(mtx.length, mtx[0].length, BufferedImage.TYPE_BYTE_GRAY); 
        for(int i=0; i<mtx.length; i++) {
            for(int j=0; j<mtx[0].length; j++) {
                int a = mtx[i][j];
                Color newColor = new Color(a,a,a);
                image.setRGB(j,i,newColor.getRGB());
            }
        }
        File output = new File("../output/export.jpg");
        ImageIO.write(image, "jpg", output);
    }
}
