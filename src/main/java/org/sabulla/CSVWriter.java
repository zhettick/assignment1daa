package org.sabulla;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void write(String fileName, String algorithm, int length, Metrics metrics) {
        File file = new File(fileName);
        boolean fileExists = file.exists();
        try (FileWriter writer = new FileWriter(fileName, true)) {
            if (!fileExists) {
                writer.write("Algorithm,Size,Time(ms),MaxDepth,Comparisons,Swaps\n");
            }
            writer.write(algorithm + "," + length + "," + metrics.getTime() / 1000000.0 + "," + metrics.getMaxDepth() + "," + metrics.getComparisons() + "," + metrics.getSwaps() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
