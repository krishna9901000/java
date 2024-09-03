import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

public class Main {
    static double coff2=0;
    static double coff=0;
    static double cons=0;
    static TrigonometricFunction.TrigonometricOperation operation = null;
    static double frequency  = 0;
    static double base = 0;
    static double coff3 = 0;

    public static boolean isLinear(String expression) {
        // Remove whitespaces and split the expression
        String[] terms = expression.replaceAll("\\s", "").split("[+]");

        for (String term : terms) {
            if (term.contains("x")) {
                // If x appears with a power greater than 1, it's not linear
                if (term.contains("^") && Integer.parseInt(term.split("\\^")[1]) > 1) {
                    return false;
                }
            } else {
                try {
                    // Try to parse it as a constant
                    Double.parseDouble(term);
                } catch (NumberFormatException e) {
                    // If parsing fails, it's not linear
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isQuadratic(String expression) {
        // Remove whitespaces and split the expression
        String[] terms = expression.replaceAll("\\s", "").split("(?=[-+])");

        for (String term : terms) {
            if (term.contains("x")) {
                // If x appears with a power greater than 2, it's not quadratic
                if (term.contains("^") && Integer.parseInt(term.split("\\^")[1]) > 2) {
                    return false;
                }
            } else {
                try {
                    // Try to parse it as a constant
                    Double.parseDouble(term);
                } catch (NumberFormatException e) {
                    // If parsing fails, it's not quadratic
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isTrigonometric(String expression) {
        // Convert the expression to lowercase to make the search case-insensitive
        String lowercaseExpression = expression.toLowerCase();
        String[] terms = lowercaseExpression.split("(?=[(])");
        // Check if the expression contains trigonometric terms or inverse trigonometric terms
        if (terms[0].contains("sin") || terms[0].contains("cos") ||
            terms[0].contains("tan") || terms[0].contains("csc") ||
            terms[0].contains("sec") || terms[0].contains("cot") ) {
            return true;
        }
    
        // If none of the trigonometric terms are found, return false
        return false;
    }
    public static boolean isLogarithmic(String expression) {
        // Convert the expression to lowercase to make the search case-insensitive
        String lowercaseExpression = expression.toLowerCase();
        if (lowercaseExpression.contains("log")) {
            return true;
        }
        return false;
    }

    public static boolean isExponential(String expression) {
        // Convert the expression to lowercase to make the search case-insensitive
        String lowercaseExpression = expression.toLowerCase();
        String[] terms = lowercaseExpression.split("(?=[*(])");
        if (terms[1].contains("*exp") ) {
            return true;
        }
        return false;
    }

    private static void createUI(JFrame frame) {

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setPreferredSize(new Dimension(200, 0));

        JButton inputFunctionButton = new JButton("Input Function");
        inputFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String functionExpression = JOptionPane.showInputDialog(frame, "Enter the function expression:\n1)Liner - ax+b\n2)Quadratic ax^2+bx+c\n3)Trignomentric sin(3) instead of sin(3x)\n4)Logarithmic log x to base a - log(a)\n5)Exponential(a*b^x)- a*(b)");
                // You can perform further actions with the entered function expression here
                JOptionPane.showMessageDialog(frame, "Entered Function: " + functionExpression);

                if (isLinear(functionExpression)){
                String[] terms = functionExpression.split("(?=[-+])");
                for (String term : terms) {
                    term = term.trim(); 
                    if (term.endsWith("x")) {
                        term = term.substring(0, term.length() - 1);
                            if (term.isEmpty() || term.equals("-")) {
                                coff = term.startsWith("-") ? -1 : 1;
                            } 
                            else {
                                coff = Double.parseDouble(term);
                            }
                        } 
                        else {
                            cons = Double.parseDouble(term);    
                        }
                    }
                }

                if (isQuadratic(functionExpression)){
                    String[] terms = functionExpression.split("(?=[-+])");
		            terms[0] = terms[0].replace("x^2", "").trim();
		            coff2 = Double.parseDouble(terms[0]);
		            terms[1] = terms[1].replace("x", "").trim();
		            coff = Double.parseDouble(terms[1]);
		            cons = Double.parseDouble(terms[2]);
                }
                if (isTrigonometric(functionExpression)){
                    String[] terms = functionExpression.split("(?=[(])");
                    if (terms[0].contains("sin")) {
                        operation = TrigonometricFunction.TrigonometricOperation.SINE;
                    }
                    if (terms[0].contains("cos")) {
                        operation = TrigonometricFunction.TrigonometricOperation.COSINE;
                    }
                    if (terms[0].contains("tan")) {
                        operation = TrigonometricFunction.TrigonometricOperation.TANGENT;
                    }
                    if (terms[0].contains("cot")) {
                        operation = TrigonometricFunction.TrigonometricOperation.COTANGENT;
                    }
                    if (terms[0].contains("csc")) {
                        operation = TrigonometricFunction.TrigonometricOperation.COSECANT;
                    }
                    if (terms[0].contains("sec")) {
                        operation = TrigonometricFunction.TrigonometricOperation.SECANT;
                    }
                    String coefficientTerm = terms[1].substring(terms[1].indexOf("(") + 1, terms[1].indexOf(")"));
                    frequency = Double.parseDouble(coefficientTerm);
                }

                if (isLogarithmic(functionExpression)){
                    String[] terms = functionExpression.split("(?=[(])");
                    if (terms[0].contains("log")){
                        String coefficientTerm = terms[1].substring(terms[1].indexOf("(") + 1, terms[1].indexOf(")"));
                        base = Double.parseDouble(coefficientTerm);
                    }
                } 

                    String[] terms = functionExpression.split("(?=[*])");
                    base = Double.parseDouble(terms[0]);
                    String coefficientTerm = terms[1].substring(terms[1].indexOf("(") + 1, terms[1].indexOf(")"));
                    coff3 = Double.parseDouble(coefficientTerm);
            }

        
        });
        buttonPanel.add(inputFunctionButton);

        JButton linearButton = new JButton("Plot Linear Function");
        linearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Function linearFunction = new LinearFunction(coff,cons);
                plotFunction(linearFunction, frame);
            }
        });
        buttonPanel.add(linearButton);

        JButton quadraticButton = new JButton("Plot Quadratic Function");
        quadraticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Function quadraticFunction = new QuadraticFunction(coff2,coff,cons);
                plotFunction(quadraticFunction, frame);
            }
        });
        buttonPanel.add(quadraticButton);

        JButton trigonometricButton = new JButton("Plot Trigonometric Function");
        trigonometricButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Function trigFunction = new TrigonometricFunction(1, frequency, operation); // Example: f(x) = sin(x)
                plotFunction(trigFunction, frame);
            }
        });
        buttonPanel.add(trigonometricButton);

        JButton logarithmicButton = new JButton("Plot Logarithmic Function");
        logarithmicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Function logFunction = new LogarithmicFunction(base); // Example: f(x) = log base 10 (x)
                plotFunction(logFunction, frame);
            }
        });
        buttonPanel.add(logarithmicButton);

        JButton exponentialButton = new JButton("Plot Exponential Function");
        exponentialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Function expFunction = new ExponentialFunction(base, coff3); // Example: f(x) = 2 * 3^x
                plotFunction(expFunction, frame);
            }
        });
        buttonPanel.add(exponentialButton);

        frame.add(buttonPanel, BorderLayout.WEST);
    }

    private static void plotFunction(Function function, JFrame frame) {
        JPanel plotPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Define plot area dimensions
                int width = getWidth();
                int height = getHeight();
                int padding = 40;
                int plotWidth = width - 2 * padding;
                int plotHeight = height - 2 * padding;

                // Draw axes
                g2d.drawLine(padding, padding, padding, height - padding); // y-axis
                g2d.drawLine(padding, height - padding, width - padding, height - padding); // x-axis

                // Draw coordinates
                g2d.setColor(Color.GRAY);
                g2d.setFont(new Font("Arial", Font.PLAIN, 10));
                double xScale = plotWidth / 20.0; // Scale factor for x-coordinates
                double yScale = plotHeight / 20.0; // Scale factor for y-coordinates
                for (double x = -10; x <= 10; x += 1) {
                    int plotX = (int) (padding + x * xScale);
                    int plotY = height - padding;
                    g2d.drawString(String.format("%.1f", x), plotX - 10, plotY + 15);
                }
                for (double y = -10; y <= 10; y += 1) {
                    int plotX = padding;
                    int plotY = (int) (height - padding - y * yScale);
                    g2d.drawString(String.format("%.1f", y), plotX - 30, plotY + 5);
                }
                

                // Draw function
                g2d.setColor(Color.BLUE);
                Path2D path = new Path2D.Double();
                for (double x = -10; x <= 10; x += 0.1) {
                    double y = function.evaluate(x);
                    int plotX = (int) (padding + x * xScale);
                    int plotY = (int) (height - padding - y * yScale);
                    if (x == -10) {
                        path.moveTo(plotX, plotY);
                    } else {
                        path.lineTo(plotX, plotY);
                    }
                }
                g2d.draw(path);
            }
        };

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart(frame);
            }
        });
        plotPanel.add(restartButton, BorderLayout.NORTH);

        frame.getContentPane().removeAll(); // Clear previous content
        frame.add(plotPanel, BorderLayout.CENTER);
        frame.revalidate(); // Refresh frame
    }

    public static void restart(JFrame frame) {
        frame.dispose(); // Close the current frame
        Main main = new Main(); // Create a new instance of the main class
        main.main(new String[0]); // Start the application again
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Math Functions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        createUI(frame); // Initialize the UI

        frame.setVisible(true);
    }

}
