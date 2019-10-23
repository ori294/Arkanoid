package blocksreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * BlocksDefinitionReader - gets a block definitions file and converts is
 * to a block factory.
 * @author ori29
 */
public class BlocksDefinitionReader {

    /**
     * setDefaults: gets a string and sets the default values for the blocks.
     * @param line a String.
     * @return Map<String ,String>;
     */
    public static Map<String, String> getDefaults(String line) {

        Map<String, String> defaults = new HashMap<String, String>();

        if (line.contains("width:")) {
            String trimmed = line.substring(line.indexOf("width:") + 6, line.indexOf("width:") + 10);
            String width = trimmed.replaceAll(" .*", "");
            defaults.put("width", width);
        }
        if (line.contains("hit_points")) {
            String hitPoints = line.substring(line.indexOf("hit") + 11, line.indexOf("hit") + 12);
            defaults.put("hp", hitPoints);
        }
        if (line.contains("height:")) {
            String trimmed = line.substring(line.indexOf("height:") + 7, line.indexOf("height:") + 11);
            String height = trimmed.replaceAll(" .*", "");
            defaults.put("height", height);
        }
        if (line.contains("stroke:")) {
            String trimmed1 = line.substring(line.indexOf("stroke:") + 12);
            String trimmed2 = trimmed1.replaceFirst(" .*", "");
            String stroke = trimmed2.substring(trimmed2.indexOf("(") + 1, trimmed2.indexOf(")"));
            defaults.put("stroke", stroke);
        }
        if (line.contains("fill:")) {
            String trimmed1 = line.substring(line.indexOf("fill:") + 10);
            String trimmed2 = trimmed1.replaceFirst(" .*", "");
            String fill0 = trimmed2.substring(trimmed2.indexOf("(") + 1, trimmed2.indexOf(")"));
            defaults.put("fill", fill0);
        }
        return defaults;
    }
    /**
     * BlocksFromSymbolsFactory: reads the block definitions and creates a factory.
     * @param reader a buffererd reader.
     * @return returns BlocksFromSymbolsFactory.
     * @throws NumberFormatException NumberFormat exception.
     * @throws IOException file reader exception.
     */
     public static BlocksFromSymbolsFactory fromReader(BufferedReader reader) throws NumberFormatException,
     IOException {

        BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();
        BufferedReader br = reader;
        Map<String, String> defaults = null;
        String line;

        while ((line = br.readLine()) != null) {

            //assign the default definitions for all of the blocks.
            if (line.contains("default")) {
                defaults = BlocksDefinitionReader.getDefaults(line);
            }
            //assign each block definition to it's symbol.
            if (line.contains("bdef")) {
                String symbol = line.substring(line.indexOf("symbol:") + 7, line.indexOf("symbol:") + 8);
                BlocksFromIO block = new BlocksFromIO(symbol);
                int hp = 1;

                try {
                    //assign the default properties.
                    block.setHeight(defaults.get("height"));
                    block.setWidth(defaults.get("width"));
                    block.setHP(defaults.get("hp"));
                    hp = Integer.parseInt(defaults.get("hp"));
                    String defStroke = defaults.get("stroke");
                    if (defStroke.contains(",")) {
                        block.setStroke(ColorsParser.rgbToColor(defStroke));
                    } else {
                        block.setStroke(ColorsParser.stringToColor(defStroke));
                    }

                    String deffill = defaults.get("fill");
                    if (deffill.contains(",")) {
                        block.addFill(ColorsParser.rgbToColor(deffill));
                    } else {
                        block.addFill(ColorsParser.stringToColor(deffill));
                    }
                } catch (Exception e) {
                    ;//do nothing.
                }

                if (line.contains("hit_points")) {
                    String hitPoints = line.substring(line.indexOf("hit") + 11, line.indexOf("hit") + 12);
                    block.setHP(hitPoints);
                    hp = Integer.parseInt(hitPoints);
                }
                if (line.contains("width:")) {
                    String trimmed = line.substring(line.indexOf("width:") + 6, line.indexOf("width:") + 10);
                    String width = trimmed.replaceAll(" .*", "");
                    block.setWidth(width);
                }
                if (line.contains("height:")) {
                    String trimmed = line.substring(line.indexOf("height:") + 7, line.indexOf("height:") + 11);
                    String height = trimmed.replaceAll(" .*", "");
                    block.setHeight(height);
                }
                if (line.contains("stroke:")) {
                    String trimmed1 = line.substring(line.indexOf("stroke:") + 12);
                    String trimmed2 = trimmed1.replaceFirst(" .*", "");
                    String stroke = trimmed2.substring(trimmed2.indexOf("(") + 1, trimmed2.indexOf(")"));

                    if (stroke.contains(",")) {
                        block.setStroke(ColorsParser.rgbToColor(stroke));
                    } else {
                        block.setStroke(ColorsParser.stringToColor(stroke));
                    }
                }
                for (int i = 1; i <= hp; i++) {
                    if (line.contains("fill-" + i + ":")) {
                        String trimmed1 = line.substring(line.indexOf("fill-" + i + ":") + 13);
                        String trimmed2 = trimmed1.replaceFirst(" .*", "");
                        String fill1 = trimmed2.substring(trimmed2.indexOf("(") + 1, trimmed2.indexOf(")"));

                        if (fill1.contains("block_images")) {
                            block.addImage(fill1);
                        } else if (fill1.contains(",")) {
                            block.addFill(ColorsParser.rgbToColor(fill1));
                        } else {
                            block.addFill(ColorsParser.stringToColor(fill1));
                        }
                    } else {
                        String trimmed1 = line.substring(line.indexOf("fill:") + 10);
                        String trimmed2 = trimmed1.replaceFirst(" .*", "");
                        String fill = trimmed2.substring(trimmed2.indexOf("(") + 1, trimmed2.indexOf(")"));

                        if (fill.contains("block_images")) {
                            block.addImage(fill);
                        } else if (fill.contains(",")) {
                            block.addFill(ColorsParser.rgbToColor(fill));
                        } else {
                            block.addFill(ColorsParser.stringToColor(fill));
                        }
                    }
                }
                factory.addBlockCreator(symbol, block);
            }
            //assign spacers to their symbols.
            if (line.contains("sdef")) {
                String symbol = line.substring(line.indexOf("symbol:") + 7, line.indexOf("symbol:") + 8);

                if (line.contains("width:")) {
                    String trimmed = line.substring(line.indexOf("width:") + 6);
                    String spaceWitdh = trimmed.replaceAll(" ", "");
                    factory.addSpacer(symbol, Integer.parseInt(spaceWitdh));
                }
            }
        }
        return factory;
     }
}

