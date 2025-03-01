package org.example;

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