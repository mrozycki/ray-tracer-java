package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Objects.AbstractObject;

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

    public List<AbstractObject> getObjects() {
        return objects;
    }

    public List<Light> getLights() {
        return lights;
    }
}
