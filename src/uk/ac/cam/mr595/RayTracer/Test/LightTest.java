package uk.ac.cam.mr595.RayTracer.Test;

public class LightTest {

    /* Reflection method was moved to AbstractObject class
     * need to move the test to appropriate class
     *
    public void reflectTest() {
        Light l = new Light(new Vector3d(0.5, 1, 0));
        Vector3d normal = new Vector3d(1, 0, 0);
        Vector3d position = new Vector3d(0, 0, 0);

        Vector3d reflection = l.reflectLight(normal, position);
        assertEquals("length", 1, reflection.length(), 0.01);
        assertEquals("angle to normal", l.getPosition().unit().dot(normal), reflection.dot(normal), 0.01);
        assertEquals("angle to original",
                Math.acos(l.getPosition().unit().dot(normal))*2/Math.PI*180,
                Math.acos(l.getPosition().unit().dot(reflection))/Math.PI*180, 0.01);
    }
     */


}