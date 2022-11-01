package geometry_operation;

public class Gmt {
    public static void translation(int[][] A, int[][] B, int N, int M, int m, int n){ 
        /* Mentranslasi citra A sejauh m, n. Hasil translasi disimpan di dala B.
        Ukuran citra adalah N ´ M.
        */
        int i, j;
        for (i=0; i<=N-1; i++)
            for (j=0; j<=M-1; j++){
                if(i+m < N && j+n < M)
                    B[i][j] = A[i+m][j+n];
                else B[i][j] = 0;
            }
    }


    public static void rotation90CCW(int[][] A, int[][] B, int N, int M){ 
        /* Rotasi citra A sejauh 90° berlawanan arah jarum jam (CCW = Clock
        Counter-wise).
        Ukuran citra adalah N ´ M. Hasil rotasi disimpan di dalam citra B.
        */
        int i, j, k;
        for (i=0; i<=N-1; i++){
            k=M-1;
            for (j=0; j<=M-1; j++){
                B[k][i]=A[i][j];
                k--;
            }
        }
    }

    public static void rotation90CW(int[][] A, int[][] B, int N, int M){ 
        /* Rotasi citra A sejauh 90° berlawanan arah jarum jam (CCW = Clock
        Counter-wise).
        Ukuran citra adalah N ´ M. Hasil rotasi disimpan di dalam citra B.
        */
        int i, j, k;
        
        k=M-1;
        for (i=0; i<=N-1; i++){
            for (j=0; j<=M-1; j++){
                B[j][k]=A[i][j];
            }
            k--;
        }
    }

    public static void zoom_out(int[][] A, int[][] B, int N, int M){ 
        /* perbesaran citra A dengan faktor skala 2
        Ukuran citra adalah N ´ M. Hasil perbesaran disimpa d dalam citra B.
        */
        int i, j, m, n;
        m=0;
        n=0;
        
        for (i=0; i<=N-1; i++){
            for (j=0; j<=M-1; j++){
                if(m+1 < N && n+1 < M){    
                    B[m+1][n+1]= A[i][j];
                }
                else if(m+1 < N && n < M){
                    B[m+1][n]= A[i][j];
                }
                else if(m < N && n+1 < M){
                    B[m][n+1]= A[i][j];
                }
                else if(m < N && n < M){
                    B[m][n]= A[i][j];
                }
                n=n+2;
            }
            m=m+2;
            n=0;
        }
    }

    public static void vertical_flip(int[][] A, int[][] B, int N, int M){ 
        /* Flipping vertikal (pencerminan terhadap sumbu-X) terhadap citar A.
        Ukuran citra adalah N ´ M. Hasil flipping disimpan di dalam citra B.
        */
        int i, j, k;
        k=M-1;
        for (i=0; i<=N-1; i++){
            for (j=0; j<=M-1; j++){
                B[k][j]=A[i][j];
            }
            k--;
        }
    }
}
