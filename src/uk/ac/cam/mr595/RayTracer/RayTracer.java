package uk.ac.cam.mr595.RayTracer;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Objects.Torus;

import java.io.IOException;

public class RayTracer {

    private Scene scene;

    public RayTracer() {
        this.scene = new Scene();
    }

    public static void main(String[] args) throws IOException {
        RayTracer rt = new RayTracer();
        rt.scene.addMesh(new Torus(new Vector3d(3, 0, 0), new Vector3d(1, 1, 1), 0.33, 0.67));
        //rt.scene.addMesh(new Sphere(new Vector3d(3, 1.1, 0), 1, Color.RED));
        rt.scene.addLight(new Light(new Vector3d(0, 0, 0)));

        rt.scene.render(640, 480).savePNG("torus2.png");
    }

}
