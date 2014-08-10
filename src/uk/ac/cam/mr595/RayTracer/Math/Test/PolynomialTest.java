package uk.ac.cam.mr595.RayTracer.Math.Test;

import org.junit.Test;
import uk.ac.cam.mr595.RayTracer.Math.Polynomial;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PolynomialTest {

    @Test
    public void noSolutionQuadraticTest() {
        Polynomial p = new Polynomial(Arrays.asList(1.0, 0.0, 1.0));

        Set<Double> solutions = p.solve();
        assertEquals("x^2 + 1 = 0 has 0 solutions", 0, solutions.size());
    }

    @Test
    public void oneSolutionQuadraticTest() {
        Polynomial p = new Polynomial(Arrays.asList(1.0, 2.0, 1.0));

        Set<Double> solutions = p.solve();
        assertEquals("x^2 + 2x + 1 = 0 has 1 solution", 1, solutions.size());
        assertTrue("that solution is -1", solutions.contains(-1.0));
    }

    @Test
    public void twoSolutionsQuadraticTest() {
        Polynomial p = new Polynomial(Arrays.asList(1.0, 0.0, -1.0));

        Set<Double> solutions = p.solve();
        assertEquals("x^2 - 1 = 0 has 2 solutions", 2, solutions.size());
        assertTrue("one of the solutions is -1", solutions.contains(-1.0));
        assertTrue("one of the solutions is 1", solutions.contains(1.0));
    }

    @Test
    public void quarticTest() {
        Polynomial p = new Polynomial(0, -2, -1, 2, 1); // x^4 + 2x^3 - x^2 - 2x = (x+2)(x+1)x(x-1)

        Double[] solutions = new TreeSet<Double>(p.solve()).toArray(new Double[]{});

        assertEquals("x^4 + 2x^3 - x^2 - 2x has 4 solutions", 4, solutions.length);
        assertEquals("1st solution is -2", -2, solutions[0], 0.0001);
        assertEquals("2nd solution is -1", -1, solutions[1], 0.0001);
        assertEquals("3th solution is 0", 0, solutions[2], 0.0001);
        assertEquals("4th solution is 1", 1, solutions[3], 0.0001);
    }

}