package org.example.service;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ImageProcessor {

    public static String processImageWithWatermark(String imagePath, String name) throws IOException {
        String currentDate = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date());
        String watermarkText = name.toUpperCase() + " - " + currentDate;

        File inputFile = new File(imagePath);
        BufferedImage originalImage = ImageIO.read(inputFile);

        String imageFormat = getImageFormat(inputFile);
        if (imageFormat == null) {
            throw new IOException("Unsupported image format");
        }

        Graphics2D graphics = (Graphics2D) originalImage.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int fontSize = originalImage.getWidth() / 40;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        graphics.setFont(font);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(watermarkText);
        int textHeight = fontMetrics.getHeight();

        int x = originalImage.getWidth() - textWidth - 10;
        int y = originalImage.getHeight() - textHeight + fontMetrics.getAscent();

        graphics.setColor(new Color(0, 0, 0, 128));
        graphics.drawString(watermarkText, x + 2, y + 2);

        graphics.setColor(new Color(255, 255, 255, 192));
        graphics.drawString(watermarkText, x, y);

        graphics.dispose();

        String outputFileName = "watermark-image." + imageFormat;
        File outputFile = new File(outputFileName);
        ImageIO.write(originalImage, imageFormat, outputFile);

        return outputFile.getAbsolutePath();
    }

    private static String getImageFormat(File file) throws IOException {
        try (ImageInputStream imageInputStream = ImageIO.createImageInputStream(file)) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(imageInputStream);
            if (readers.hasNext()) {
                return readers.next().getFormatName().toLowerCase();
            }
        }
        return null;
    }
}
