import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Convolution{
    public static void main(String[] args) throws IOException {
        // int[][] Image = {
        //     {4,4,6,8,10,11},
        //     {4,6,15,9,9,10},
        //     {12,13,1,0,0,2},
        //     {7,7,8,10,12,12},
        //     {12,11,9,0,0,6},
        //     {8,8,8,10,0,9}
        // };
        // int[][] ImageResult = new int[4][4];
        // int[][] Mask = {
        //     {1,2,1},
        //     {2,4,2},
        //     {1,2,1}
        // };

        File file = new File("input.jpg");
        int[][] mtxImg = imgToMtx(file);
        int[][] imgRes = new int[mtxImg.length-2][mtxImg[0].length-2];
        int[][] sharpening = {
                {0,-1,0},
                {-1,5,-1},
                {0,-1,0}
            };

        convolution(mtxImg, sharpening, imgRes);

        mtxToImg(imgRes);

        //konvolusi(Image, ImageResult, Mask, 6, 6);
        //convolution(Image, Mask, ImageResult);

        // for(int i = 0; i < ImageResult.length; i++){
        //     for(int j = 0; j < ImageResult[0].length; j++){
        //         if(ImageResult[i][j]/10 == 0){
        //             System.out.print("  ");
        //         }
        //         else if(ImageResult[i][j]/100 == 0){
        //             System.out.print(" ");
        //         }
        //         System.out.print(ImageResult[i][j] + "  ");
        //     }
        //     System.out.println();
        // }
    }

    public static void konvolusi(int[][] Image, int[][] ImageResult, int[][] Mask, int N, int M){ 
    /* Mengkonvolusi citra Image yang berukuran N ´ M dengan mask 3 ´ 3.
    Hasil konvolusi disimpan di dalam matriks ImageResult.
    */
        int i, j;
        for (i=1; i<=N-3; i++)
            for(j=1; j<=M-3; j++)
                ImageResult[i][j]=
                    Image[i-1][j-1]*Mask[0][0] +
                    Image[i-1][j+1]*Mask[0][1] +
                    Image[i-1][j]*Mask[0][2] +
                    Image[i][j-1]*Mask[1][0] +
                    Image[i][j]*Mask[1][1] +
                    Image[i][j+1]*Mask[1][2] +
                    Image[i+1][j-1]*Mask[2][0] +
                    Image[i+1][j]*Mask[2][1] +
                    Image[i+1][j+1]*Mask[2][2];
    }

    public static void convolution(int[][] a, int[][] b, int[][] c){
        int penampung = 0;
        for (int i = 1; i < a.length-1; i++) {
			for (int j = 1; j < a[0].length-1; j++) {
				penampung = 0;
				int bx =0;
				int by =0;
				for (int i2 = i-1; i2 <= i+1; i2++) {
					for (int j2 = j-1; j2 <= j+1; j2++) {
						penampung+= (a[i2][j2]*b[bx][by]);
						bx++;
						if(bx>2) {
							bx=0;
							by++;
						}
					}
					if(by>2) {
						by=0;
					}
				}
				c[i-1][j-1] = penampung;
			}
			
		}
    }

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
                if(a > 255) a = 255;
                else if(a < 0) a = 0;
                Color newColor = new Color(a,a,a);
                image.setRGB(j,i,newColor.getRGB());
            }
        }
        File output = new File("output.jpg");
        ImageIO.write(image, "jpg", output);
    }
}