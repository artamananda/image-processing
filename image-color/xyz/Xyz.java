import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Xyz {
    public static void main(String[] args) throws IOException {
        File file = new File("../input.jpg");
        int[][] mtxImgRed = imgToMtxRed(file);
        int[][] mtxImgGreen = imgToMtxGreen(file);
        int[][] mtxImgBlue = imgToMtxBlue(file);
        cieRGB_toXYZ(mtxImgRed, mtxImgGreen, mtxImgBlue);
    }

    public static void cieRGB_toXYZ(int[][] r, int[][] g, int[][] b) throws IOException{
        int N = r.length;
        int M = r[0].length;
        int[][] x = new int[N][M];
        int[][] y = new int[N][M];
        int[][] z = new int[N][M];
        int i, j; double R, G, B; double X, Y, Z;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                R = (double)r[i][j]; G=(double)g[i][j]; B=(double)b[i][j];
                X = 0.490*R+0.310*G+0.200*B;
                Y = 0.177*R+0.813*G+0.011*B;
                Z = 0.010*G+0.990*B;
                if (X > 255.0) x[i][j]=255;
                else x[i][j]=(int)X;
                if (Y > 255.0) y[i][j]=255;
                else y[i][j]=(int)Y;
                if (Z > 255.0) z[i][j]=255; 
                else z[i][j]=(int)Z;
            }

        mtxToImg(x, y, z);
    }

    public static int[][] imgToMtxRed(File file)throws IOException {
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
                colorMatrix[i][j] = colors[i][j].getRed();
            }
        }

        return colorMatrix;
    }

    public static int[][] imgToMtxGreen(File file)throws IOException {
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
                colorMatrix[i][j] = colors[i][j].getGreen();
            }
        }

        return colorMatrix;
    }

    public static int[][] imgToMtxBlue(File file)throws IOException {
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
                colorMatrix[i][j] = colors[i][j].getBlue();
            }
        }

        return colorMatrix;
    }

    public static void mtxToImg(int[][] r, int[][] g, int[][] b)throws IOException{
        BufferedImage image = new BufferedImage(r.length, r[0].length, BufferedImage.TYPE_BYTE_GRAY);
        for(int i=0; i<r.length; i++) {
            for(int j=0; j<r[i].length; j++) {
                int x = r[i][j];
                int y = g[i][j];
                int z = b[i][j];
                if(x > 255) x = 255;
                else if(x < 0) x = 0;
                if(y > 255) y = 255;
                else if(y < 0) y = 0;
                if(z > 255) z = 255;
                else if(z < 0) z = 0;
                Color newColor = new Color(x,y,z);
                image.setRGB(j,i,newColor.getRGB());
            }
        }
        File output = new File("../output.jpg");
        ImageIO.write(image, "jpg", output);
    }
}