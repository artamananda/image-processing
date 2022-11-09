import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cmyk {
    public static void main(String[] args) throws IOException {
        File file = new File("../input.jpg");
        int[][] mtxImgRed = imgToMtxRed(file);
        int[][] mtxImgGreen = imgToMtxGreen(file);
        int[][] mtxImgBlue = imgToMtxBlue(file);
        RGB_toCMYK(mtxImgRed, mtxImgGreen, mtxImgBlue);
        //CMYK_toRGB(mtxImgRed, mtxImgGreen, mtxImgBlue);
    }

    public static void RGB_toCMYK(int[][] r, int[][] g, int[][] b) throws IOException{
        int N = r.length;
        int M = r[0].length;
        int[][] c = new int[N][M];
        int[][] m = new int[N][M];
        int[][] y = new int[N][M];
        int[][] k = new int[N][M];
        int i, j;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                c[i][j]=(int)255 - r[i][j];
                m[i][j]=(int)255 - g[i][j];
                y[i][j]=(int)255 - b[i][j];
                k[i][j]=c[i][j];
                if (m[i][j]<k[i][j]) k[i][j]=m[i][j];
                if (y[i][j]<k[i][j]) k[i][j]=y[i][j];
                c[i][j]=c[i][j]-k[i][j];
                m[i][j]=m[i][j]-k[i][j];
                y[i][j]=y[i][j]-k[i][j];
            }

        mtxToImg(c, m, y);
    }

    public static void CMYK_toRGB(int[][] c, int[][] m, int[][] y) throws IOException{
        int N = c.length;
        int M = c[0].length;
        int[][] k = new int[N][M];
        int[][] r = new int[N][M];
        int[][] g = new int[N][M];
        int[][] b = new int[N][M];
        int i, j;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                c[i][j]=c[i][j]+k[i][j];
                m[i][j]=m[i][j]+k[i][j];
                y[i][j]=y[i][j]+k[i][j];
                k[i][j]=c[i][j];
                r[i][j]=(int)255 - c[i][j];
                g[i][j]=(int)255 - m[i][j];
                b[i][j]=(int)255 - y[i][j];
            }
        mtxToImg(r, g, b);
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
        BufferedImage image = new BufferedImage(r.length, r[0].length, BufferedImage.TYPE_INT_RGB);
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