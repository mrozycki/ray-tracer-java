package uk.ac.cam.mr595.RayTracer.Objects;

import uk.ac.cam.mr595.RayTracer.Math.Transform.Scale;
import uk.ac.cam.mr595.RayTracer.Math.Transform.Translate;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Ray;

import java.awt.*;

public class Sphere extends AbstractObject {

    public Sphere(Vector3d position, double radius) {
        this.localToWorld = new Scale(radius).add(new Translate(position));
    }

    @Override
    protected Vector3d localIntersect(Ray ray) {
        double a = Math.pow(ray.direction.length(), 2);
        double b = ray.direction.dot(ray.origin) * 2;
        double c = Math.pow(ray.origin.length(), 2) - 1;

        double delta = b*b - 4*a*c;
        if (delta < 0) {
            return null;
        }
        double t1 = (-b-Math.sqrt(delta))/(2*a);
        double t2 = (-b+Math.sqrt(delta))/(2*a);

        Vector3d i1 = ray.origin.add(ray.direction.mul(t1));
        Vector3d i2 = ray.origin.add(ray.direction.mul(t2));

        if (i1.distanceTo(ray.origin) < i2.distanceTo(ray.origin)) {
            return i1;
        } else {
            return i2;
        }
    }

    @Override
    protected Vector3d localNormal(Vector3d pos) {
        if (Math.abs(pos.length() - 1) > 0.001) return null;
        else return pos.unit();
    }

    @Override
    public Color getColorAt(Vector3d intersectionPoint) {
        return Color.RED;
    }
}
