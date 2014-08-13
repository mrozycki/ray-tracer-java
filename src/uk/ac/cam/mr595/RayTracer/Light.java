package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

public class Light {
    private Vector3d position;

    public Light(Vector3d position) {
        this.position = position;
    }

    public Vector3d getPosition() {
        return position;
    }

}
