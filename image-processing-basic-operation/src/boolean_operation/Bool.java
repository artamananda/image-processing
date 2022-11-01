package boolean_operation;

public class Bool {
    public static void not(int[][] A, int[][] B, int N, int M){ 
        /* Membuat citra komplemen dari citra biner A. Komplemennya disimpan di
        dalam B. Ukuran citra A adalah N ´ M.
        */
        int i, j;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                if(A[i][j] < 128) B[i][j] = 0;
                else B[i][j] = 255;
            }
    }
}
