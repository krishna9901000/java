public class LogarithmicFunction extends Function {
    private double base;

    public LogarithmicFunction(double base) {
        this.base = base;
    }

    // Method to evaluate the logarithmic function at a given x
    @Override
    public double evaluate(double x) {
        
        return Math.log(x) / Math.log(base);
    }

    // Method to calculate the derivative of the logarithmic function
    @Override
    public double derivative(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Cannot compute derivative for non-positive x");
        }
        // The derivative of ln(x) with respect to x is 1/x
        return 1 / (x * Math.log(base));
    }

    // Method to calculate the integral of the logarithmic function over a range [startX, endX]
    @Override
    public double integral(double startX, double endX) {
        // The integral of ln(x) from a to b is (b * ln(b) - b) - (a * ln(a) - a)
        if (startX <= 0 || endX <= 0) {
            throw new IllegalArgumentException("Cannot compute integral for non-positive startX or endX");
        }
        double integralStart = startX * Math.log(startX) / Math.log(base) - startX;
        double integralEnd = endX * Math.log(endX) / Math.log(base) - endX;
        return integralEnd - integralStart;
    }

    // Method to calculate the average value of the logarithmic function over a range [startX, endX]
    @Override
    public double average(double startX, double endX) {
        // Compute the integral and divide by the length of the interval
        if (startX <= 0 || endX <= 0) {
            throw new IllegalArgumentException("Cannot compute average for non-positive startX or endX");
        }
        double averageValue = integral(startX, endX) / (endX - startX);
        return averageValue;
    }

    // Method to find a root of the logarithmic function using numerical methods
    @Override
    public double findRoot(double initialGuess, double tolerance, int maxIterations) {
        if (initialGuess <= 0) {
            throw new IllegalArgumentException("Initial guess must be positive");
        }
        // Root finding for logarithmic functions can be complex
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

