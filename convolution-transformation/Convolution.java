public class Convolution{
    public static void main(String[] args) {
        int[][] Image = {
            {4,4,6,8,10,11},
            {4,6,15,9,9,10},
            {12,13,1,0,0,2},
            {7,7,8,10,12,12},
            {12,11,9,0,0,6},
            {8,8,8,10,0,9}
        };
        int[][] ImageResult = new int[4][4];
        int[][] Mask = {
            {1,2,1},
            {2,4,2},
            {1,2,1}
        };

        konvolusi(Image, ImageResult, Mask, 6, 6);

        for(int i = 0; i < ImageResult.length; i++){
            for(int j = 0; j < ImageResult[0].length; j++){
                if(ImageResult[i][j]/10 == 0){
                    System.out.print("  ");
                }
                else if(ImageResult[i][j]/100 == 0){
                    System.out.print(" ");
                }
                System.out.print(ImageResult[i][j] + "  ");
            }
            System.out.println();
        }
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
}