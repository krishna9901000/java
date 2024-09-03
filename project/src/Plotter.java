import java.util.Arrays;

public class Plotter {
    public void renderPlot(Function[] functions, PlotSettings settings) {
        // Code to render the plot using the provided functions and settings
        System.out.println("Rendering plot...");
        System.out.println("Functions: " + Arrays.toString(functions));
        System.out.println("Plot settings: " + settings.toString());
        // Additional code can be added here to handle rendering of the plot
        // For example, invoking a graphics library to draw the plot on a canvas
    }

    public void zoomIn(double factor) {
        // Code to zoom in the plot by the given factor
        System.out.println("Zooming in by factor: " + factor);
        // Additional code can be added here to adjust the scale of the plot accordingly
    }

    public void zoomOut(double factor) {
        // Code to zoom out the plot by the given factor
        System.out.println("Zooming out by factor: " + factor);
        // Additional code can be added here to adjust the scale of the plot accordingly
    }

    public void panHorizontal(double offset) {
        // Code to pan the plot horizontally by the given offset
        System.out.println("Panning horizontally by offset: " + offset);
        // Additional code can be added here to adjust the horizontal position of the plot
    }

    public void panVertical(double offset) {
        // Code to pan the plot vertically by the given offset
        System.out.println("Panning vertically by offset: " + offset);
        // Additional code can be added here to adjust the vertical position of the plot
    }
}

