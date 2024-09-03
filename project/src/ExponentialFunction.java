public class ExponentialFunction extends Function {
    private double base;
    private double coefficient;

    public ExponentialFunction(double base, double coefficient) {
        this.base = base;
        this.coefficient = coefficient;
    }

    // Method to evaluate the exponential function at a given x
    @Override
    public double evaluate(double x) {
        return coefficient * Math.pow(base, x);
    }

    // Method to calculate the derivative of the exponential function
    @Override
    public double derivative(double x) {
        // The derivative of an exponential function y = a * b^x is given by y' = a * b^x * ln(b)
        return coefficient * Math.log(base) * Math.pow(base, x);
    }

    // Method to calculate the integral of the exponential function over a range [startX, endX]
    @Override
    public double integral(double startX, double endX) {
        // Numerically integrate the function using the trapezoidal rule
        double integralValue = 0;
        double deltaX = 0.0001; // Step size for numerical integration
        for (double i = startX; i <= endX; i += deltaX) {
            double currentHeight = evaluate(i);
            double nextHeight = evaluate(i + deltaX);
            integralValue += (currentHeight + nextHeight) * deltaX / 2; // Trapezoidal rule
        }
        return integralValue;
    }

    // Method to calculate the average value of the exponential function over a range [startX, endX]
    @Override
    public double average(double startX, double endX) {
        // Compute the integral and divide by the length of the interval
        return integral(startX, endX) / (endX - startX);
    }

    // Method to find a root of the exponential function using numerical methods
    @Override
    public double findRoot(double initialGuess, double tolerance, int maxIterations) {
        // Root finding for exponential functions can be complex
        // We'll use numerical methods such as bisection or Newton's method with caution
        // For simplicity, let's assume the function has a single root in the given range
        double x = initialGuess;
        double xLeft = initialGuess - tolerance;
        double xRight = initialGuess + tolerance;
        double fx = evaluate(x);

        // Check if initial guess is already a root
        if (Math.abs(fx) < tolerance) {
            return x;
        }

        double fxLeft = evaluate(xLeft);
        double fxRight = evaluate(xRight);

        int iterations = 0;
        while (iterations < maxIterations) {
            if (Math.signum(fxLeft) != Math.signum(fxRight)) {
                // Use bisection method if the signs of f(xLeft) and f(xRight) are different
                x = (xLeft + xRight) / 2;
                fx = evaluate(x);

                if (Math.abs(fx) < tolerance) {
                    return x; // Found root within tolerance
                }

                if (Math.signum(fx) == Math.signum(fxLeft)) {
                    xLeft = x; // Update left boundary
                    fxLeft = fx;
                } else {
                    xRight = x; // Update right boundary
                    fxRight = fx;
                }
            } else {
                // Use Newton's method if the signs of f(xLeft) and f(xRight) are the same
                double derivativeValue = derivative(x);
                if (derivativeValue == 0) {
                    // Avoid division by zero
                    throw new ArithmeticException("Division by zero");
                }
                x = x - fx / derivativeValue;
                fx = evaluate(x);

                if (Math.abs(fx) < tolerance) {
                    return x; // Found root within tolerance
                }
            }

            iterations++;
        }

        // If maxIterations is reached without convergence, return NaN
        return Double.NaN;
    }
}

