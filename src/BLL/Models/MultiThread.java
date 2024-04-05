package BLL.Models;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThread {
    public long[] getNumberOfPixels(File imageFile, int NUM_THREADS) {
        Image image = new Image(imageFile.toURI().toString());
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        long[] counters = new long[3]; // Index 0: red, Index 1: green, Index 2: blue

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Split image processing into equal chunks for parallel processing
        int chunkHeight = height / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            int startY = i * chunkHeight;
            int endY = (i == NUM_THREADS - 1) ? height : (i + 1) * chunkHeight;

            executor.execute(() -> {
                countPixels(image, startY, endY, width, counters);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return counters;
    }

    private void countPixels(Image image, int startY, int endY, int width, long[] counters) {
        PixelReader pixelReader = image.getPixelReader();
        long[] localCounters = new long[3];

        for (int y = startY; y < endY; y++) {
            for (int x = 0; x < width; x++) {
                int color = pixelReader.getArgb(x, y);
                int red = (color >> 16) & 0xFF;
                int green = (color >> 8) & 0xFF;
                int blue = color & 0xFF;

                if (red != 0) {
                    localCounters[0]++;
                }
                if (green != 0) {
                    localCounters[1]++;
                }
                if (blue != 0) {
                    localCounters[2]++;
                }
            }
        }

        // Aggregate local counts
        synchronized (counters) {
            for (int i = 0; i < 3; i++) {
                counters[i] += localCounters[i];
            }
        }
    }

    public long[] getImageDimensions(File imageFile, int NUM_THREADS) {
        Image image = new Image(imageFile.toURI().toString());
        long[] dimensions = new long[2]; // Index 0: width, Index 1: height

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        executor.execute(() -> {
            dimensions[0] = (long) image.getWidth();
        });
        executor.execute(() -> {
            dimensions[1] = (long) image.getHeight();
        });

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return dimensions;
    }

}