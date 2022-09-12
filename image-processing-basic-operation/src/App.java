
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        File file = new File("../input/example.jpg");
        int[][] res = converter.Citra.imgToMtx(file);
        int[][] biner = new int[res.length][res[0].length];

        // computing_level.PointLevel.biner(res, biner, 128, res.length, res[0].length);
        computing_level.PointLevel.brightening(res, 2, biner, res.length, res[0].length);

        converter.Citra.mtxToImg(biner);
    }
}
