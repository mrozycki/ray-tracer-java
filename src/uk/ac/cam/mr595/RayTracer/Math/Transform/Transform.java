package uk.ac.cam.mr595.RayTracer.Math.Transform;

import Jama.Matrix;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

public class Transform {
    protected Matrix matrix;

    public Transform() {
        this.matrix = Matrix.identity(4,4);
    }

    protected Transform(double[][] matrix) {
        this.matrix = new Matrix(matrix);
    }

    private Transform(Matrix matrix) throws IncorrectTransformSizeException {
        if (matrix.getColumnDimension() != 4 || matrix.getRowDimension() != 4) {
            throw new IncorrectTransformSizeException();
        }

        this.matrix = matrix;
    }

    public final Transform add(Transform t) {
        return new Transform(t.matrix.times(matrix));
    }

    public final Transform inverse() {
        return new Transform(matrix.inverse());
    }

    public final Vector3d applyTo(Vector3d v) {
        double[][] t = {{v.x}, {v.y}, {v.z}, {1}};
        Matrix vec = new Matrix(t);
        Matrix hvec = matrix.times(vec);
        return new Vector3d(hvec.get(0,0), hvec.get(1,0), hvec.get(2,0)).mul(1.0/hvec.get(3,0));
    }

    public final Transform noTranslation() {
        Matrix sub = matrix.getMatrix(0,2,0,2).inverse().transpose();
        Matrix result = Matrix.identity(4,4);
        result.setMatrix(0,2,0,2, sub);
        return new Transform(result);
    }
}
