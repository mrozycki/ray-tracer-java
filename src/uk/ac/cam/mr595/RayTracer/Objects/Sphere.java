package uk.ac.cam.mr595.RayTracer.Objects;

import uk.ac.cam.mr595.RayTracer.Math.Polynomial;
import uk.ac.cam.mr595.RayTracer.Math.Transform.Scale;
import uk.ac.cam.mr595.RayTracer.Math.Transform.Translate;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Ray;

import java.awt.*;
import java.util.Set;

public class Sphere extends AbstractObject {

    private Color color;

    public Sphere(Vector3d position, double radius) {
        this.localToWorld = new Scale(radius).add(new Translate(position));
    }

    public Sphere(Vector3d position, double radius, Color color) {
        this(position, radius);
        this.color = color;
    }

    @Override
    protected Vector3d localIntersect(Ray ray) {
        double a = Math.pow(ray.direction.length(), 2);
        double b = ray.direction.dot(ray.origin) * 2;
        double c = Math.pow(ray.origin.length(), 2) - 1;

        Set<Double> solutions = new Polynomial(c, b, a).solve();
        Double[] ts = solutions.toArray(new Double[solutions.size()]);

        double minimumDistance = Double.POSITIVE_INFINITY;
        Vector3d closestIntersect = null;

        for (Double t: ts) {
            Vector3d intersect = ray.origin.add(ray.direction.mul(t));
            if (ray.origin.distanceTo(intersect) < minimumDistance) {
                minimumDistance = ray.origin.distanceTo(intersect);
                closestIntersect = intersect;
            }
        }

        return closestIntersect;
    }

    @Override
    protected Vector3d localNormal(Vector3d pos) {
        if (Math.abs(pos.length() - 1) > 0.001) return null;
        else return pos.unit();
    }

    @Override
    public Color getColorAt(Vector3d intersectionPoint) {
        return color;
    }
}
