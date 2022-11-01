package arithmethic_operation;

public class Operation {
    public static void addition(int[][] A, int[][] B, int[][] C, int N, int M){ 
        /* Menjumlahkan dua buah citra A dan B menjadi citra baru, C.
        Citra A, B, dan C masing-masing berukuran N ´ M.
        */
        int i, j, temp;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                temp=A[i][j] + B[i][j];
                if (temp > 255) C[i][j]=255;
                else C[i][j]=temp;
            }
    }


    public static void substraction (int[][] A, int[][] B, int[][] C, int N, int M){ 
        /* Mengurangkan dua buah citra A dan B menajdi citra baru, C.
        Citra A, B, dan C berukuran N ´ M.
        */
        int i, j;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                C[i][j]=A[i][j] - B[i][j];
                if (C[i][j] != 0) C[i][j]=255; /* nyatakan objek berwarna putih */
            }
    }

    public static void multiplication(int[][] A, float[][] B, int[][] C, int N){ 
        /* Mengalikan buah citra A dengan matriks koreksi B menjadi citra C.
        Citra A, matriks B, dan hasil perkalian C berukuran N ´ N.
        */
        int i, j, k, temp;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=N-1; j++){
                temp=0;
                for (k=0; k<=N-1; k++){
                    temp += A[i][k]*(float)B[k][j];
                    /* clipping */
                    if (temp < 0)
                        C[i][j] = 0;
                    else if (temp > 255)
                        C[i][j]=255;
                    else
                        C[i][j]=temp;
                }
            }
    }
}
