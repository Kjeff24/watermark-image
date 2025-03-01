package org.example.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageProcessor {

    public static InputStream processImageWithWatermark(InputStream inputStream, String name, String imageFormat) throws IOException {
        String currentDate = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date());
        String watermarkText = name.toUpperCase() + " - " + currentDate;

        BufferedImage originalImage = ImageIO.read(inputStream);

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

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(originalImage, imageFormat, byteArrayOutputStream);

        String outputFileName = "watermark-image." + imageFormat;
        File outputFile = new File(outputFileName);
        ImageIO.write(originalImage, imageFormat, outputFile);

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
