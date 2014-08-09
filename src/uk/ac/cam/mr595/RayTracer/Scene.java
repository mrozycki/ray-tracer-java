package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Objects.AbstractObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final Camera camera;
    private List<AbstractObject> objects;
    private List<Light> lights;

    public Scene() {
        objects = new ArrayList<AbstractObject>();
        lights = new ArrayList<Light>();
        camera = new Camera();
    }

    public void addMesh(AbstractObject m) {
        objects.add(m);
    }

    public void addLight(Light l) {
        lights.add(l);
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Render render(int width, int height) {
        Render render = new Render(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Vector3d origin = camera.getPosition();
                Vector3d direction = camera.getDirection().add(new Vector3d(0, i - width / 2, j - height / 2).mul(1.0 / height));
                Ray ray = new Ray(origin, direction);

                double minimumDistance = Double.POSITIVE_INFINITY;
                AbstractObject intersectionObject = null;
                Vector3d intersectionPoint = null;

                for(AbstractObject m: objects) {
                    Vector3d intersection = m.intersect(ray);
                    if (intersection == null) {
                        continue;
                    }

                    if (origin.distanceTo(intersection) < minimumDistance) {
                        intersectionObject = m;
                        intersectionPoint = intersection;
                        minimumDistance = origin.distanceTo(intersection);
                    }
                }

                if (intersectionObject == null) continue;
                if (intersectionPoint == null || intersectionPoint.x < 1) continue;

                double totalDiffuseIllumination = 0.0;
                double totalSpecularIllumination = 0.0;
                Vector3d normal = intersectionObject.normal(intersectionPoint);
                for (Light l: lights) {
                    double diffuse = l.diffuse(normal, intersectionPoint);
                    if (diffuse > 0) {
                        totalDiffuseIllumination += diffuse;
                    }

                    Vector3d reflection = l.reflect(normal, intersectionPoint);
                    double specular = intersectionPoint.uminus().unit().dot(reflection);
                    if (specular > 0) {
                        totalSpecularIllumination += Math.pow(specular, 5);
                    }
                }

                int g = (int)(255*(totalDiffuseIllumination*0.3 + totalSpecularIllumination*0.7));

                render.putPixel(i, j, new Color(g,0,0));
            }
        }

        return render;
    }
}
