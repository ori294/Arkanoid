package blocksreader;

/**
 * ColorsParser: converts a color (string or RGB values) into java.awt.Color.
 * @author ori29
 */
public class ColorsParser {

    /**
     * rgbToColor gets a string with the RGB values and returns a Color.
     * @param s a string.
     * @return a Color
     */
    public static java.awt.Color rgbToColor(String s) {

        if (s.contains("RGB(")) {
            s = s.replaceFirst("RGB", "");
            s = s.replaceFirst("\\(", "");
        }

        String[] colors = s.split(",");

        int red = Integer.parseInt(colors[0]);
        int green = Integer.parseInt(colors[1]);
        int blue = Integer.parseInt(colors[2]);
        java.awt.Color color = new java.awt.Color(red, green, blue);

        return color;
    }

    /**
     * stringToColor gets a string with a color name and returns a Color.
     * @param s a string.
     * @return a Color
     */
    public static java.awt.Color stringToColor(String s) {
        java.awt.Color color = null;

        switch (s.toLowerCase()) {
        case "black":
            color = java.awt.Color.BLACK;
            break;
        case "light_gray":
            color = java.awt.Color.LIGHT_GRAY;
            break;
        case "dark_gray":
            color = java.awt.Color.DARK_GRAY;
            break;
        case "gray":
            color = java.awt.Color.GRAY;
            break;
        case "white":
            color = java.awt.Color.WHITE;
            break;
        case "orange":
            color = java.awt.Color.ORANGE;
            break;
        case "red":
            color = java.awt.Color.RED;
            break;
        case "yellow":
            color = java.awt.Color.YELLOW;
            break;
        case "green":
            color = java.awt.Color.GREEN;
            break;
        case "blue":
            color = java.awt.Color.BLUE;
            break;
        case "magneta":
            color = java.awt.Color.MAGENTA;
            break;
        case "cyan":
            color = java.awt.Color.CYAN;
            break;
        case "pink":
            color = java.awt.Color.PINK;
            break;
        default:
            color = java.awt.Color.GRAY;
            break;
        }
        return color;
    }
}