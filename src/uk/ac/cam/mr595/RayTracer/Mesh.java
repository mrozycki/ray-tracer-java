package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

import java.awt.*;

public abstract class Mesh {

    public abstract Vector3d normal(Vector3d pos);
    public abstract Vector3d intersect(Ray ray);

    public abstract Color getColorAt(Vector3d intersectionPoint);

}
