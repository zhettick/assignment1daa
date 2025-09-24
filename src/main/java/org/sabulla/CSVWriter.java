package org.sabulla;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void write(String fileName, String algorithm, int length, Metrics metrics) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(algorithm + "," + length + "," + metrics.getTime() + "," +
                    metrics.getMaxDepth() + "," + metrics.getComparisons() + "," + metrics.getSwaps() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
