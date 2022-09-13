
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        File file = new File("../input/example.jpg");
        ///File file2 = new File("../input/example2.jpg");
        int[][] mtxImg = converter.Citra.imgToMtx(file);
        //int[][] mtxImg2 = converter.Citra.imgToMtx(file2);
        int[][] imgRes = new int[mtxImg.length][mtxImg[0].length];

        //computing_level.PointLevel.biner(mtxImg, imgRes, 128, mtxImg.length, mtxImg[0].length);
        //computing_level.PointLevel.negatif(mtxImg, imgRes, mtxImg.length, mtxImg[0].length);
        //computing_level.PointLevel.brightening(mtxImg, 2, imgRes, mtxImg.length, mtxImg[0].length);
        

        //arithmethic_operation.Operation.addition(mtxImg, mtxImg2, imgRes, mtxImg.length, mtxImg[0].length);
        //arithmethic_operation.Operation.substraction(mtxImg, mtxImg2, imgRes, mtxImg.length, mtxImg[0].length);

        // //multiplication operation
        // float[][] mr = new float[mtxImg.length][mtxImg[0].length];
        // for(int i = 0; i < mr.length; i++){
        //     for(int j = 0; j < mr[0].length; j++){
        //         mr[i][j] = 0.012f;
        //     }
        // }
        // arithmethic_operation.Operation.multiplication(mtxImg, mr, imgRes, mtxImg.length);


        //boolean_operation.Bool.not(mtxImg, imgRes, mtxImg.length, mtxImg[0].length);


        //geometry_operation.Gmt.translation(mtxImg, imgRes, mtxImg.length, mtxImg[0].length, 20, 20);
        //geometry_operation.Gmt.rotation90CCW(mtxImg, imgRes, mtxImg.length, mtxImg[0].length);
        //geometry_operation.Gmt.rotation90CW(mtxImg, imgRes, mtxImg.length, mtxImg[0].length);
        //geometry_operation.Gmt.zoom_out(mtxImg, imgRes, mtxImg.length, mtxImg[0].length);
        //geometry_operation.Gmt.vertical_flip(mtxImg, imgRes, mtxImg.length, mtxImg[0].length);

        converter.Citra.mtxToImg(imgRes);
    }
}
