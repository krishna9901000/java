public class LinearFunction extends Function {
    private double a;
    private double b;

    public LinearFunction(double a, double b) {
        this.a = a;
        this.b = b;
    }

    // Method to evaluate the linear function at a given x
    @Override
    public double evaluate(double x) {
        return a * x + b;
    }

    // Method to calculate the derivative of the linear function
    @Override
    public double derivative(double x) {
        // The derivative of a linear function is constant and equal to 'a'
        return a;
    }

    // Method to calculate the integral of the linear function over a range [startX, endX]
    @Override
    public double integral(double startX, double endX) {
        // Using the formula for the definite integral of a linear function
        double startValue = evaluate(startX);
        double endValue = evaluate(endX);
        return (endValue + startValue) * (endX - startX) / 2;
    }

    // Method to calculate the average value of the linear function over a range [startX, endX]
    @Override
    public double average(double startX, double endX) {
        // The average value of a function over a range is the integral of the function divided by the range
        return integral(startX, endX) / (endX - startX);
    }

    // Method to find a root of the linear function using Newton's method
    @Override
    public double findRoot(double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        int iterations = 0;

        while (iterations < maxIterations) {
            double fx = evaluate(x);

            // Check if the absolute value of the function value at the current point is within the tolerance
            if (Math.abs(fx) < tolerance) {
                return x; // Return the root if it is found
            }

            // Apply Newton's method to update the current point
            x = x - fx / a;
            iterations++;
        }

        // If maxIterations is reached without convergence, return NaN
        return Double.NaN;
    }
}

