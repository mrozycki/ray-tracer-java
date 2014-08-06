package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

public class Camera {
    private Vector3d position;
    private Vector3d direction;

    public Camera() {
        this.position = new Vector3d(0, 0, 0);
        this.direction = new Vector3d(0, 0, 1);
    }

    public Camera(Vector3d position) {
        this.position = position;
        this.direction = new Vector3d(0, 0, 1);
    }

    public Camera(Vector3d position, Vector3d direction) {
        this.position = position;
        this.direction = direction;
    }

    public Vector3d getPosition() {
        return position;
    }

    public Vector3d getDirection() {
        return direction;
    }
}
