package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Objects.AbstractObject;
import uk.ac.cam.mr595.RayTracer.Objects.Sphere;

import java.awt.*;
import java.io.IOException;

public class RayTracer {

    private Scene scene;
    private final static Color BACKGROUND_COLOR = new Color(47, 47, 47);

    public RayTracer(Scene scene) {
        this.scene = scene;
    }

    public Render renderScene(int width, int height) {
        Render render = new Render(width, height);

        for (int i = 0; i < width; i++) {
            System.out.println(i);
            for (int j = 0; j < height; j++) {
                Vector3d origin = scene.getCamera().getPosition();
                Vector3d direction = scene.getCamera().getDirection().add(new Vector3d(0, i - width / 2, j - height / 2).mul(1.0 / height));
                Ray ray = new Ray(origin, direction);

                render.putPixel(i, j, castRay(ray));
            }
        }

        return render;
    }

    private Color castRay(Ray ray) {
        double minimumDistance = Double.POSITIVE_INFINITY;
        AbstractObject intersectionObject = null;
        Vector3d intersectionPoint = null;

        for(AbstractObject m: scene.getObjects()) {
            Vector3d intersection = m.intersect(ray);
            if (intersection == null) {
                continue;
            }

            if (ray.origin.distanceTo(intersection) > 0.001 && ray.origin.distanceTo(intersection) < minimumDistance) {
                intersectionObject = m;
                intersectionPoint = intersection;
                minimumDistance = ray.origin.distanceTo(intersection);
            }
        }

        if (intersectionObject == null) return BACKGROUND_COLOR;
        if (intersectionPoint == null) return BACKGROUND_COLOR;

        return intersectionObject.calculateIlluminationAt(intersectionPoint, ray.origin, scene.getLights());
    }

    public static void main(String[] args) throws IOException {
        Scene scene = new Scene();
        //scene.addMesh(new Torus(new Vector3d(10, 0, 0), new Vector3d(1, 1, 1), 1, 2, new Color(43, 145, 227)));
        scene.addMesh(new Sphere(new Vector3d(5, -1.1, 0), 1.0, new Color(43, 145, 227)));
        scene.addMesh(new Sphere(new Vector3d(5, 1.1, 0), 1.0, new Color(227, 8, 5)));
        scene.addLight(new Light(new Vector3d(-3, -5, -5)));

        RayTracer rt = new RayTracer(scene);
        rt.renderScene(640, 480).savePNG("spheres1.png");
    }

}
