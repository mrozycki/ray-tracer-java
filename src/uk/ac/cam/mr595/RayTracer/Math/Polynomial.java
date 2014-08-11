package uk.ac.cam.mr595.RayTracer.Math;

import java.util.*;

public class Polynomial {

    private final List<Double> coefficients;

    public Polynomial(List<Double> coefficients) {
        int firstNonZero = coefficients.size();
        for (int i = coefficients.size()-1; i >= 0; i--) {
            if (coefficients.get(i) != 0) {
                firstNonZero = i;
                break;
            }
        }
        this.coefficients = coefficients.subList(0, firstNonZero+1);
    }

    public Polynomial(double a0, double a1, double a2, double a3, double a4) {
        this(Arrays.asList(a0, a1, a2, a3, a4));
    }

    public Set<Double> solve() {
        if (coefficients.size() == 3) return solveQuadratic();
        Set<Double> result = new TreeSet<Double>();

        Set<Double> criticalPoints = derivative().solve();
        Iterator<Double> iter = criticalPoints.iterator();
        double prev = Double.NEGATIVE_INFINITY;
        double curr;

        while (iter.hasNext()) {
            curr = iter.next();
            if (valueAt(curr) == 0) {
                result.add(curr);
            } else if (valueAt(prev) * valueAt(curr) < 0) {
                result.add(findZeroBetween(prev, curr));
            }
            prev = curr;
        }

        if (valueAt(prev) * valueAt(Double.POSITIVE_INFINITY) < 0) {
            result.add(findZeroBetween(prev, Double.POSITIVE_INFINITY));
        }

        return result;
    }

    private Set<Double> solveQuadratic() {
        double a = coefficients.get(2);
        double b = coefficients.get(1);
        double c = coefficients.get(0);
        double delta = b*b - 4*a*c;

        TreeSet<Double> result = new TreeSet<Double>();
        if (delta < 0) {
        } else if (delta == 0) {
            result.add(-b/(2*a));
        } else {
            result.add((-b-Math.sqrt(delta))/(2*a));
            result.add((-b+Math.sqrt(delta))/(2*a));
        }
        return result;
    }

    private Double findZeroBetween(double start, double end) {
        Polynomial derivative = this.derivative();

        double approx;
        if (Double.isInfinite(start) && Double.isInfinite(end)) {
            approx = 1;
        } else if (Double.isInfinite(start)) {
            approx = end - 1;
        } else if (Double.isInfinite(end)) {
            approx = start+1;
        } else {
            approx = (start+end)/2;
        }

        while (Math.abs(valueAt(approx)) > 1e-10) {
            approx = approx - this.valueAt(approx)/derivative.valueAt(approx);
            if (approx > end) {
                approx = 2*end - approx;
            } else if (approx < start) {
                approx = 2*start - approx;
            }
        }

        return approx;
    }

    public Double valueAt(double x) {
        if (Double.isInfinite(x)) {
            double powerSign = Math.pow(Math.signum(x), coefficients.size()-1);
            return Double.POSITIVE_INFINITY*powerSign*coefficients.get(coefficients.size()-1);
        }

        double power = 1;
        double result = 0;

        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i)*power;
            power *= x;
        }

        return result;
    }

    public Polynomial derivative() {
        List<Double> coeffs = new ArrayList<Double>(coefficients.subList(1, coefficients.size()));
        for (int i = 1; i < coeffs.size(); i++) {
            coeffs.set(i, coeffs.get(i)*(i+1));
        }
        return new Polynomial(coeffs);
    }
}
