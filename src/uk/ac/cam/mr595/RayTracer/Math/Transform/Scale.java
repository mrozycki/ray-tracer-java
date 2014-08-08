package uk.ac.cam.mr595.RayTracer.Math.Transform;

public class Scale extends Transform {

    public Scale(double k) {
        this.matrix = this.matrix.times(k);
        this.matrix.set(3, 3, 1);
    }

}
