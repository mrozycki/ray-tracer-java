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

    public Vector3d reflect(Vector3d normal, Vector3d pos) {
        Vector3d toLight = position.sub(pos).unit();

        return toLight.uminus().sub(normal.mul(2*toLight.uminus().dot(normal)));
    }

    public double diffuse(Vector3d normal, Vector3d pos) {
        Vector3d toLight = position.sub(pos).unit();

        return normal.dot(toLight);
    }

}
