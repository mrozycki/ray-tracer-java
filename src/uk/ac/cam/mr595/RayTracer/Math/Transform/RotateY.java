package uk.ac.cam.mr595.RayTracer.Math.Transform;

import Jama.Matrix;

public class RotateY extends Transform {

    public RotateY (double angle) {
        double[][] values = {
                {Math.cos(angle), 0, Math.sin(angle), 0},
                {0, 1, 0, 0},
                {-Math.sin(angle), 0, Math.cos(angle), 0},
                {0, 0, 0, 1}
        };

        this.matrix = new Matrix(values);
    }

}
