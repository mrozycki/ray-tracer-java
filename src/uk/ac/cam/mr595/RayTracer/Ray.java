package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

public class Ray {
    public final Vector3d origin;
    public final Vector3d direction;

    public Ray(Vector3d origin, Vector3d direction) {
        this.origin = origin;
        this.direction = direction;
    }
}
