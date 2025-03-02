# Image Processing Package

## Overview
This Java-based image processing provides functionality to add a watermark to an image. The watermark consists of a custom name along with the current timestamp, ensuring clear identification and authenticity.

## Features
- Reads an image from the specified file path.
- Adds a watermark containing the given name and the current date and time.
- Supports multiple image formats (JPEG, PNG, BMP, etc.).
- Automatically adjusts font size based on image dimensions.
- Outputs a new image file with the watermark applied.

## Prerequisites
- Java Development Kit (JDK) 8 or higher.
- `javax.imageio` package (included in standard Java libraries).

## Installation
Clone the repository and compile the project using your preferred Java build tool.
```sh
 git clone <https://github.com/Kjeff24/watermark-image.git>
 cd watermark-image
```

## Usage
### Import the package
Ensure the `ImageProcessor` class is included in your project.

### Example Code
```java
import org.example.service.ImageProcessor;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your full name: ");
        String name = sc.nextLine();

        System.out.print("\nEnter the file Path: ");
        String filePath = sc.nextLine();

        System.out.println(ImageProcessor.processImageWithWatermark(filePath, name));

    }
}
```

## Output
A new image file named `watermark-image.<format>` will be generated in the working directory with the specified watermark applied.

## Supported Image Formats
- All image formats

