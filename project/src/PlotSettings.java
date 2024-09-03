public class PlotSettings {
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private double gridSpacing;
    private String lineColor;
    private String lineStyle;

    public double getXMin() {
        return xMin;
    }

    public void setXMin(double xMin) {
        if (xMin < xMax) {
            this.xMin = xMin;
        } else {
            throw new IllegalArgumentException("xMin must be less than xMax");
        }
    }

    public double getXMax() {
        return xMax;
    }

    public void setXMax(double xMax) {
        if (xMax > xMin) {
            this.xMax = xMax;
        } else {
            throw new IllegalArgumentException("xMax must be greater than xMin");
        }
    }

    public double getYMin() {
        return yMin;
    }

    public void setYMin(double yMin) {
        if (yMin < yMax) {
            this.yMin = yMin;
        } else {
            throw new IllegalArgumentException("yMin must be less than yMax");
        }
    }

    public double getYMax() {
        return yMax;
    }

    public void setYMax(double yMax) {
        if (yMax > yMin) {
            this.yMax = yMax;
        } else {
            throw new IllegalArgumentException("yMax must be greater than yMin");
        }
    }

    public double getGridSpacing() {
        return gridSpacing;
    }

    public void setGridSpacing(double gridSpacing) {
        if (gridSpacing > 0) {
            this.gridSpacing = gridSpacing;
        } else {
            throw new IllegalArgumentException("Grid spacing must be greater than 0");
        }
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        // Additional validation can be added here, e.g., checking if the color is valid
        this.lineColor = lineColor;
    }

    public String getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(String lineStyle) {
        // Additional validation can be added here, e.g., checking if the line style is valid
        this.lineStyle = lineStyle;
    }
}

