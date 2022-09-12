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
}