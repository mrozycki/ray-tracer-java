package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Objects.Sphere;

import java.awt.*;
import java.io.IOException;

public class RayTracer {

    private Scene scene;

    public RayTracer() {
        this.scene = new Scene();
    }

    public static void main(String[] args) throws IOException {
        RayTracer rt = new RayTracer();
        rt.scene.addMesh(new Sphere(new Vector3d(3, 0, 0), 1));
        rt.scene.addLight(new Light(new Vector3d(20, 5, 5)));

        rt.scene.render(1920, 1080).savePNG("sphere.png");
    }

}
