import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RunLengthEncoding {
    public static void main(String[] args) throws IOException {
        File file = new File("../input.jpg");
        int[][] mtxImg = imgToMtx(file);
        int[][] imgRes = rle(mtxImg);

        mtxToImg(imgRes);
    }

    public static int[][] rle(int[][] mtxImg){
        int[][] res = {{}};
        int[][] addArr = {{}};
        int max = 0;
        for(int i = 0; i < mtxImg.length; i++){
            for(int j = 0; j < mtxImg[i].length-1; j++){
                if(mtxImg[i][j] != mtxImg[i][j+1]){
                    res[i] = Arrays.copyOf(res[i], res[i].length+1);
                    res[i][res[i].length-1] = mtxImg[i][j];
                }
            }
            if(res[i].length > max) max = res[i].length;
            if(i < mtxImg.length-1){
                res = append(res, addArr);
            }
        }
        res = Arrays.copyOf(res, max);
        for(int i = 0; i < res.length; i++){
            if(res[i].length < max){
                int d = (res[i].length / (max - res[i].length));
                int c = 0;
                int[] temp = new int[max];
                for(int j = 0; j < res[i].length; j++){
                    if(j % d == 0){
                        for(int k = 0; k < d+1; k++){
                            if(c == max)break;
                            temp[c] = res[i][j];
                            c++;
                        }
                    }
                    
                }
                res[i] = Arrays.copyOf(temp, temp.length);
                if(res[i].length < max){
                    for(int j = res[i].length-1; j < max-1; j++){
                        res[i] = Arrays.copyOf(res[i], res[i].length+1);
                        res[i][res[i].length-1] = 0;
                    }
                }
            }
        }
        System.out.printf("Before Compress\t\t: %d x %d\n",mtxImg.length, mtxImg[0].length);
        System.out.printf("After Compress\t\t: %d x %d\n",res.length, res[0].length);
        System.out.printf("Compressing Ratio\t: %.2f%%\n",100d-((double)res.length/(double)mtxImg.length*100d));

        return res;
    }

    public static int[][] append(int[][] a, int[][] b) {
        int[][] result = new int[a.length + b.length][];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
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
            for(int j=0; j<mtx[i].length; j++) {
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