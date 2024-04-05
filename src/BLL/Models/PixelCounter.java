package BLL.Models;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class PixelCounter extends Application {

    private static final int BINS = 256; // Assuming 8-bit color depth

    @Override
    public void start(Stage primaryStage) {
        // Example usage
        File imageFile = new File("resources/Images/red.jpg");
        int NUM_THREADS = 4; // Set your desired number of threads
        long result = getNumberOfRedPixels(imageFile, NUM_THREADS);
        System.out.println("Red: " + result);
    }

    public long getNumberOfRedPixels(File imageFile, int NUM_THREADS) {
        Image image = new Image(imageFile.toURI().toString());
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        AtomicLong redCount = new AtomicLong(); // Count for red pixels only

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Split image processing into equal chunks for parallel processing
        int chunkHeight = height / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            int startY = i * chunkHeight;
            int endY = (i == NUM_THREADS - 1) ? height : (i + 1) * chunkHeight;

            executor.execute(() -> {
                redCount.addAndGet(countRedPixels(image, startY, endY, width));
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return redCount.get();
    }

    private long countRedPixels(Image image, int startY, int endY, int width) {
        PixelReader pixelReader = image.getPixelReader();
        long localRedCount = 0; // Local count for red pixels only

        for (int y = startY; y < endY; y++) {
            for (int x = 0; x < width; x++) {
                int color = pixelReader.getArgb(x, y);
                int red = (color >> 18) & 0xFF;

                if (red == 255) { // If it's full red, increment the count
                    localRedCount++;
                }
            }
        }

        return localRedCount;
    }

    public static void main(String[] args) {
        launch(args);
    }
}