package uk.ac.cam.mr595.RayTracer.Math.Transform;

import uk.ac.cam.mr595.RayTracer.Math.Vector3d;

public class Rotate extends Transform {

    public Rotate(Vector3d axis, double angle) {
        axis = axis.unit();
        double d = Math.sqrt(axis.y*axis.y + axis.z*axis.z);

        Transform xRotation;
        if (d != 0) {
            xRotation = new Transform(new double[][]{
                    {1, 0, 0, 0},
                    {0, axis.z / d, -axis.y / d, 0},
                    {0, axis.y / d, axis.z / d, 0},
                    {0, 0, 0, 1}
            });
        } else {
            xRotation = new Transform();
        }

        Transform yRotation = new Transform(new double[][]{
                {d, 0, -axis.x, 0},
                {0, 1, 0, 0},
                {axis.x, 0, d, 0},
                {0, 0, 0, 1}
        });

        Transform zRotation = new Transform(new double[][]{
                {Math.cos(angle), -Math.sin(angle), 0, 0},
                {Math.sin(angle), Math.cos(angle), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });

        this.matrix = xRotation
                .add(yRotation)
                .add(zRotation)
                .add(yRotation.inverse())
                .add(xRotation.inverse()).matrix;
    }

    public Rotate(Vector3d source, Vector3d destination) {
        Vector3d axis = source.unit().cross(destination.unit());
        double angle = Math.asin(axis.length());

        this.matrix = new Rotate(axis, angle).matrix;
    }

}
