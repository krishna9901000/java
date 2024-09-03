public class QuadraticFunction extends Function {
    private double a;
    private double b;
    private double c;

    public QuadraticFunction(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Method to evaluate the quadratic function at a given x
    @Override
    public double evaluate(double x) {
        return a * x * x + b * x + c;
    }

    // Method to calculate the derivative of the quadratic function
    @Override
    public double derivative(double x) {
        // The derivative of a quadratic function ax^2 + bx + c is given by 2ax + b
        return 2 * a * x + b;
    }

    @Override
    public double integral(double startX, double endX) {
    double a3 = a / 3.0;
    double b2 = b / 2.0;
    
    double integralStart = a3 * Math.pow(startX, 3) + b2 * Math.pow(startX, 2) + c * startX;
    double integralEnd = a3 * Math.pow(endX, 3) + b2 * Math.pow(endX, 2) + c * endX;
    
    return integralEnd - integralStart;
    }


    // Method to calculate the average value of the quadratic function over a range [startX, endX]
    @Override
    public double average(double startX, double endX) {
        // The average value of a function over a range is the integral of the function divided by the range
        return integral(startX, endX) / (endX - startX);
    }

    // Method to find a root of the quadratic function using Newton's method
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
            double derivativeValue = derivative(x);
            if (derivativeValue == 0) {
                // Avoid division by zero
                throw new ArithmeticException("Division by zero");
            }
            x = x - fx / derivativeValue;
            iterations++;
        }

        // If maxIterations is reached without convergence, return NaN
        return Double.NaN;
    }
}

