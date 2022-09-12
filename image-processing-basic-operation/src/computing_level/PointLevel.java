package computing_level;

public class PointLevel{
    public static void biner(int[][] A, int[][] B, int T, int N, int M){
    /* Membuat citra biner dari citra A berdasarkan nilai ambang (threshold)
    T yang dispesifikasikan. Ukuran citra adalah N ´ M. citra_biner adalah
    tipe data untuk citra biner).
    */
        int i, j;
        for (i=0; i<=N-1; i++){
            for (j=0; j<=M-1; j++){
                if (A[i][j] < T)
                    B[i][j] = 0;
                else
                    B[i][j] = 255; /* atau diisi dengan 255 pada citra 8-bit */
            }
        }
    }

    public static void negatif(int[][] A, int[][] B, int N, int M){
        /* Membuat citra negatif dari citra A. Hasilnya disimpan di dalam citra
        B.
        Ukuran citra adalah N ´ M.
        */
        int i, j;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                B[i][j] = 255 - A[i][j];
            }
    }

    public static void brightening(int[][] A, int b, int[][] B, int N, int M){ 
        /* Pencerahan citra dengan cara menjumlahkan setiap pixel di dalam citra
        A dengan sebuah skalar b. Hasil disimpan di dalam citra B. Citra A
        berukuran N ´ M.
        */
        int i, j, temp;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                temp = A[i][j] + b;
                /* clipping */
                if (temp < 0)
                    B[i][j] = 0;
                else
                if (temp > 255)
                    B[i][j]=255;
                else
                    B[i][j]=temp;
            }
    }
}