public abstract class Function {
    public abstract double evaluate(double x);

    public double derivative(double x) {
        final double h = 1e-6; // Step size
        return (evaluate(x + h) - evaluate(x - h)) / (2 * h); // Approximate derivative using central difference
    }

    public double integral(double startX, double endX) {
        final int numSteps = 1000; // Number of steps for numerical integration
        final double stepSize = (endX - startX) / numSteps;
        double integral = 0.0;

        for (int i = 0; i < numSteps; i++) {
            double x0 = startX + i * stepSize;
            double x1 = x0 + stepSize;
            integral += (evaluate(x0) + evaluate(x1)) * 0.5 * stepSize; // Trapezoidal rule
        }

        return integral;
    }

    public double average(double startX, double endX) {
        double integralValue = integral(startX, endX);
        double range = endX - startX;
        return integralValue / range;
    }

    public double findRoot(double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        int iterations = 0;

        while (iterations < maxIterations) {
            double fx = evaluate(x);
            double fPrimeX = derivative(x);
            double newX = x - fx / fPrimeX;

            if (Math.abs(newX - x) < tolerance) {
                return newX;
            }

            x = newX;
            iterations++;
        }

        // If maxIterations is reached without convergence, return NaN
        return Double.NaN;
    }
}

