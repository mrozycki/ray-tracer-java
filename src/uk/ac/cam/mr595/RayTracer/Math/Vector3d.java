package uk.ac.cam.mr595.RayTracer.Math;

public class Vector3d {
    private final double x, y, z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double dot(Vector3d v) {
        return this.x*v.x + this.y*v.y + this.z*v.z;
    }

    public double distanceTo(Vector3d v) {
        return 0;
    }
}
