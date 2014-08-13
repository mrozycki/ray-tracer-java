package uk.ac.cam.mr595.RayTracer.Objects;

import uk.ac.cam.mr595.RayTracer.Light;
import uk.ac.cam.mr595.RayTracer.Math.Transform.Transform;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Ray;

import java.awt.*;
import java.util.List;

public abstract class AbstractObject {

    protected Transform localToWorld;
    protected double diffusionCoefficient;
    protected double specularCoefficient;

    protected AbstractObject() {
        diffusionCoefficient = 1.0;
        specularCoefficient = 0.0;
    }

    protected AbstractObject(double diffuse, double specular) {
        diffusionCoefficient = diffuse;
        specularCoefficient = specular;
    }

    public final Vector3d normal(Vector3d pos) {
        return localToWorld.noTranslation().applyTo(localNormal(worldToLocal(pos))).unit();
    }

    public final Vector3d intersect(Ray ray) {
        Vector3d v = localIntersect(worldToLocal(ray));
        if (v == null) return null;
        else return localToWorld(v);
    }

    public Vector3d reflectLight(Light light, Vector3d position) {
        Vector3d toSource = light.getPosition().sub(position).unit();
        return reflect(toSource, position);
    }

    public Vector3d reflect(Vector3d direction, Vector3d position) {
        return direction.uminus().sub(normal(position).mul(2 * direction.uminus().dot(normal(position))));
    }

    protected abstract Vector3d localIntersect(Ray ray);
    protected abstract Vector3d localNormal(Vector3d pos);

    public abstract Color getColorAt(Vector3d intersectionPoint);

    protected final Vector3d localToWorld(Vector3d v) {
        return this.localToWorld.applyTo(v);
    }

    protected final Ray localToWorld(Ray r) {
        Vector3d origin = localToWorld.applyTo(r.origin);
        Vector3d direction = localToWorld.inverse().noTranslation().inverse().applyTo(r.direction);
        return new Ray(origin, direction);
    }

    protected Vector3d worldToLocal(Vector3d v) {
        return this.localToWorld.inverse().applyTo(v);
    }

    protected final Ray worldToLocal(Ray r) {
        Vector3d origin = localToWorld.inverse().applyTo(r.origin);
        Vector3d direction = localToWorld.inverse().noTranslation().applyTo(r.direction);
        return new Ray(origin, direction);
    }

    public final Color calculateIlluminationAt(Vector3d at, Vector3d to, List<Light> lights) {
        Vector3d position = at.sub(to);
        double totalDiffuseIllumination = calculateDiffuseIlluminationAt(at, lights);
        double totalSpecularIllumination = calculateSpecularIlluminationAt(at, to, lights);

        double g = totalDiffuseIllumination*diffusionCoefficient + totalSpecularIllumination*specularCoefficient;
        Color c = getColorAt(position);

        return new Color((int)(g*c.getRed()), (int)(g*c.getGreen()), (int)(g*c.getBlue()));
    }

    private double calculateDiffuseIlluminationAt(Vector3d at, List<Light> lights) {
        double totalDiffuseIllumination = 0.0;
        for (Light l: lights) {
            totalDiffuseIllumination += diffuseLight(at, l);
        }

        return Math.min(1.0, totalDiffuseIllumination);
    }

    private double diffuseLight(Vector3d at, Light light) {
        Vector3d toLight = light.getPosition().sub(at).unit();
        return Math.max(0.0, normal(at).dot(toLight));
    }

    private double calculateSpecularIlluminationAt(Vector3d at, Vector3d to, List<Light> lights) {
        double totalSpecularIllumination = 0.0;
        for (Light l: lights) {
            totalSpecularIllumination += specularReflection(at, to, l);
        }

        return Math.min(1.0, Math.pow(totalSpecularIllumination, 5));
    }

    private double specularReflection(Vector3d at, Vector3d to, Light light) {
        Vector3d toEye = to.sub(at).unit();
        Vector3d reflection = reflectLight(light, at);
        return Math.max(0.0, (reflection.dot(toEye)));
    }

    public final void setDiffusionCoefficient(double coeff) {
        diffusionCoefficient = coeff;
    }

    public final void setSpecularCoefficient(double coeff) {
        specularCoefficient = coeff;
    }

}
