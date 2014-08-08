package uk.ac.cam.mr595.RayTracer.Math.Transform;

import Jama.Matrix;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

public class Translate extends Transform {

    public Translate(Vector3d v) {
        double[][] mat = new double[4][4];
        for (int i = 0; i < 4; i++) {
            mat[i][i] = 1;
        }
        mat[0][3] = v.x;
        mat[1][3] = v.y;
        mat[2][3] = v.z;

        matrix = new Matrix(mat);
    }

}
