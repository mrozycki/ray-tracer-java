package uk.ac.cam.mr595.RayTracer.Objects.Test;

import org.junit.Before;
import org.junit.Test;
import uk.ac.cam.mr595.RayTracer.Math.Vector3d;
import uk.ac.cam.mr595.RayTracer.Objects.Sphere;
import uk.ac.cam.mr595.RayTracer.Ray;

import static org.junit.Assert.assertEquals;

public class SphereTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void localIntersectTest() {
        Sphere s = new Sphere(new Vector3d(0.0, 0.0, 0.0), 1);
        Ray r = new Ray(new Vector3d(-2, 0, 0), new Vector3d(1, 0, 0));

        Vector3d intersect = s.intersect(r);

        assert(intersect.x == -1);
        assert(intersect.y == 0);
        assert(intersect.z == 0);
    }

    @Test
    public void noIntersectionTest() {
        Sphere s = new Sphere(new Vector3d(0.0, 0.0, 0.0), 1);
        Ray r = new Ray(new Vector3d(-2, 0, 0), new Vector3d(0, 1, 0));

        assert(s.intersect(r) == null);
    }

    @Test
    public void translatedIntersectTest() {
        Sphere s = new Sphere(new Vector3d(2, 0, 0), 1);
        Ray r = new Ray(new Vector3d(0, 0, 0), new Vector3d(1, 0, 0));

        Vector3d intersect = s.intersect(r);

        assertEquals("x", 1, intersect.x, 0.01);
        assertEquals("y", 0, intersect.y, 0.01);
        assertEquals("z", 0, intersect.z, 0.01);
    }

    @Test
    public void localNormalTest() {
        Sphere s = new Sphere(new Vector3d(0, 0, 0), 1);
        Vector3d v = new Vector3d(-1, 0, 0);

        Vector3d normal = s.normal(v);

        assertEquals("x", -1, normal.x, 0.01);
        assertEquals("y", 0, normal.y, 0.01);
        assertEquals("z", 0, normal.z, 0.01);
    }

    @Test
    public void translatedNormalTest() {
        Sphere s = new Sphere(new Vector3d(2, 0, 0), 1);
        Vector3d v = new Vector3d(1, 0, 0);

        Vector3d normal = s.normal(v);

        assertEquals("x", -1, normal.x, 0.01);
        assertEquals("y", 0, normal.y, 0.01);
        assertEquals("z", 0, normal.z, 0.01);

    }
}