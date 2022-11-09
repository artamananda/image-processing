import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Ihs {
    public static void main(String[] args) throws IOException {
        File file = new File("../input.jpg");
        int[][] mtxImgRed = imgToMtxRed(file);
        int[][] mtxImgGreen = imgToMtxGreen(file);
        int[][] mtxImgBlue = imgToMtxBlue(file);
        RGB_toIHS(mtxImgRed, mtxImgGreen, mtxImgBlue);
    }

    public static void RGB_toIHS(int[][] r, int[][] g, int[][] b) throws IOException{
        int N = r.length;
        int M = r[0].length;
        int[][] I = new int[N][M];
        int[][] H = new int[N][M];
        int[][] S = new int[N][M];
        int i, j;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                int[] arr = {r[i][j], g[i][j], b[i][j]};
                Arrays.sort(arr);
                I[i][j] = (r[i][j] + g[i][j] + b[i][j])/3;
                
                if(I[i][j] > 0 && arr[0] > 0)
                    S[i][j] = 1-((3/r[i][j]+g[i][j]+b[i][j])*arr[0]);
                else
                    S[i][j] = 0;

                if(g[i][j] >= b[i][j])
                    H[i][j] = (int)Math.acos(2*r[i][j]-g[i][j]-b[i][j]/2*Math.sqrt(Math.pow(r[i][j]-g[i][j], 2)+(r[i][j]-b[i][j])*(g[i][j]-b[i][j])));
                else
                    H[i][j] = 360 - (int)Math.acos(2*r[i][j]-g[i][j]-b[i][j]/2*Math.sqrt(Math.pow(r[i][j]-g[i][j], 2)+(r[i][j]-b[i][j])*(g[i][j]-b[i][j])));
            }

        mtxToImg(I, H, S);
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