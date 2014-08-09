package uk.ac.cam.mr595.RayTracer.Objects;

import uk.ac.cam.mr595.RayTracer.Math.Transform.Transform;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Ray;

import java.awt.*;

public abstract class AbstractObject {

    protected Transform localToWorld;

    public final Vector3d normal(Vector3d pos) {
        return localToWorld.inverse().noTranslation().applyTo(localNormal(worldToLocal(pos)));
    }

    public final Vector3d intersect(Ray ray) {
        Vector3d v = localIntersect(worldToLocal(ray));
        if (v == null) return null;
        else return localToWorld(v);
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

}
