package uk.ac.cam.mr595.RayTracer.Math.Transform;

import Jama.Matrix;

public class RotateZ extends Transform {

    public RotateZ (double angle) {
        double[][] values = {
                {Math.cos(angle), -Math.sin(angle), 0, 0},
                {Math.sin(angle), Math.cos(angle), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        this.matrix = new Matrix(values);
    }

}
