package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Objects.Sphere;

import java.io.IOException;

public class RayTracer {

    private Scene scene;

    public RayTracer() {
        this.scene = new Scene();
    }

    public static void main(String[] args) throws IOException {
        RayTracer rt = new RayTracer();
        rt.scene.addMesh(new Sphere(new Vector3d(10, -2, 1), 1));
        rt.scene.addMesh(new Sphere(new Vector3d(10, 2, 0), 1));
        rt.scene.addLight(new Light(new Vector3d(10, 0, 0)));

        rt.scene.render(1920, 1080).savePNG("sphere3.png");
    }

}
