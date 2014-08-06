package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final Camera camera;
    private List<Mesh> meshes;
    private List<Light> lights;

    public Scene() {
        meshes = new ArrayList<Mesh>();
        lights = new ArrayList<Light>();
        camera = new Camera();
    }

    public void addMesh(Mesh m) {
        meshes.add(m);
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Render render(int width, int height) {
        Render render = new Render(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Vector3d origin = camera.getPosition();
                Vector3d direction = camera.getDirection();
                Ray ray = new Ray(origin, direction);

                double minimumDistance = Double.POSITIVE_INFINITY;
                Mesh intersectionMesh = null;
                Vector3d intersectionPoint = null;

                for(Mesh m: meshes) {
                    Vector3d intersection = m.intersect(ray);
                    if (origin.distanceTo(intersection) < minimumDistance) {
                        intersectionMesh = m;
                        intersectionPoint = intersection;
                        minimumDistance = origin.distanceTo(intersection);
                    }
                }

                if (intersectionMesh != null) {
                    render.putPixel(i, j, intersectionMesh.getColorAt(intersectionPoint));
                }
            }
        }

        return render;
    }
}
