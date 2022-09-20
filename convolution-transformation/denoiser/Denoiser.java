import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Denoiser {
    public static void main(String[] args) throws IOException {
        File file = new File("../input.jpg");
        int[][] mtxImg = imgToMtx(file);
        int[][] imgRes = new int[mtxImg.length-2][mtxImg[0].length-2];

        int[][] blur = {
            {1,2,1},
            {2,4,2},
            {1,2,1}
        };

        konvolusi(mtxImg, imgRes, blur, mtxImg.length, mtxImg[0].length);
        mtxToImg(imgRes);
    }

    public static void konvolusi(int[][] Image, int[][] ImageResult, int[][] Mask,int N, int M){
        //Mengkonvolusi citra Image yang berukuran N M dengan mask 3 - 3 .
        // Hasil konvolusi disimpan di dalam matriks ImageResult .
        int pembagi = 0;
        for (int i = 0; i < Mask.length; i++) {
            for (int j = 0; j < Mask[0].length; j++) {
                pembagi += Mask[i][j];
            }
        }

        int i, j;
        for (i = 1; i < N - 2; i++) {
            for (j = 1; j < M - 2; j++) {
                ImageResult[i][j] =
                        ((Image[i - 1][j - 1] * Mask[0][0]) +
                                (Image[i - 1][j] * Mask[0][1]) +
                                (Image[i - 1][j + 1] * Mask[0][2]) +
                                (Image[i][j - 1] * Mask[1][0]) +
                                (Image[i][j] * Mask[1][1]) +
                                (Image[i][j + 1] * Mask[1][2]) +
                                (Image[i + 1][j - 1] * Mask[2][0]) +
                                (Image[i + 1][j] * Mask[2][1]) +
                                (Image[i + 1][j + 1] * Mask[2][2]))/pembagi;
            }
        }

    }

    public static int[][] imgToMtx(File file)throws IOException {
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
                if(a > 255) a = 255;
                else if(a < 0) a = 0;
                Color newColor = new Color(a,a,a);
                image.setRGB(j,i,newColor.getRGB());
            }
        }
        File output = new File("../output.jpg");
        ImageIO.write(image, "jpg", output);
    }
}