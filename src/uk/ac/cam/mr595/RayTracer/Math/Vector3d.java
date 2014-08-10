package uk.ac.cam.mr595.RayTracer.Math;

public class Vector3d {
    public final double x, y, z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double dot(Vector3d v) {
        return this.x*v.x + this.y*v.y + this.z*v.z;
    }

    public Vector3d cross(Vector3d v) {
        return new Vector3d ( y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x );
    }

    public Vector3d add(Vector3d v) {
        return new Vector3d (x+v.x, y+v.y, z+v.z);
    }

    public Vector3d sub(Vector3d v) {
        return new Vector3d (x-v.x, y-v.y, z-v.z);
    }

    public Vector3d mul(double k) {
        return new Vector3d (k*x, k*y, k*z);
    }

    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3d unit() {
        return this.mul(1.0/this.length());
    }

    public Vector3d uminus() {
        return this.mul(-1);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Vector3d)) {
            return false;
        }

        Vector3d v = (Vector3d)o;

        return x == v.x && y == v.y && z == v.z;
    }

    public double distanceTo(Vector3d v) {
        return this.sub(v).length();
    }
}
