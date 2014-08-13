package uk.ac.cam.mr595.RayTracer.Objects;

import uk.ac.cam.mr595.RayTracer.Math.Polynomial;
import uk.ac.cam.mr595.RayTracer.Math.Transform.Rotate;
import uk.ac.cam.mr595.RayTracer.Math.Transform.Translate;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Ray;

import java.awt.*;
import java.util.Set;

public class Torus extends AbstractObject {
    private double innerRadius, outerRadius;
    private Vector3d position;
    private Color color;

    public Torus(Vector3d position, Vector3d normal, double innerRadius, double outerRadius, Color color) {
        super(0.6, 0.4);
        this.position = position;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.color = color;
        if (normal.equals(new Vector3d(1, 0, 0))) {
            this.localToWorld = new Translate(position);
        } else {
            this.localToWorld = new Rotate(new Vector3d(1, 0, 0), normal).add(new Translate(position));
        }
    }

    @Override
    protected Vector3d localIntersect(Ray ray) {
        double R = outerRadius;
        double r = innerRadius;
        Vector3d d = ray.direction;
        Vector3d p = ray.origin;

        double alpha = d.dot(d);
        double beta = 2* p.dot(d);
        double gamma = p.dot(p) - r * r - R * R;

        Polynomial polynomial = new Polynomial (
                gamma*gamma + 4*R*R*p.x*p.x - 4*R*R*r*r, // Oh..
                2*beta*gamma + 8*R*R*p.x*d.x, // Oh..
                beta*beta + 2*alpha*gamma + 4*R*R*d.x*d.x, // Oh..
                2*alpha*beta, // It's maagic,
                alpha*alpha // You knooow!
        ); // Aaaaaay, macarena!

        Set<Double> solutions = polynomial.solve();
        if (solutions.size() == 0) {
            return null;
        }

        Vector3d closestIntersection = null;
        double smallestDistance = Double.POSITIVE_INFINITY;
        for (Double t: solutions) {
            Vector3d intersectionPoint = ray.origin.add(ray.direction.mul(t));
            double distance = ray.origin.distanceTo(intersectionPoint);
            if (distance < smallestDistance) {
                smallestDistance = distance;
                closestIntersection = intersectionPoint;
            }
        }

        return closestIntersection;
    }

    @Override
    protected Vector3d localNormal(Vector3d pos) {
        double common = pos.x*pos.x + pos.y*pos.y + pos.z*pos.z - innerRadius*innerRadius - outerRadius*outerRadius;

        return new Vector3d (
                4*pos.x*common + 8*outerRadius*outerRadius*pos.x,
                4*pos.y*common,
                4*pos.z*common
        );
    }

    @Override
    public Color getColorAt(Vector3d intersectionPoint) {
        return color;
    }
}
