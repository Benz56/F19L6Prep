/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class F19L6Prep {

    public static void main(String[] args) {
        Arrays.asList("alice30.txt"/*, "Boscombe.txt" <- Mangler i ZIP filen?*/, "Snow White.txt").forEach(fileName -> {
                    new Thread(() -> {
                        try (Scanner scanner = new Scanner(new File("src\\wordcount\\" + fileName))) {
                            long words = 0, start = System.currentTimeMillis();
                            while (scanner.hasNext()) {
                                scanner.next();
                                words++;
                            }
                            System.out.println(fileName + " has " + words + ". It took " + (System.currentTimeMillis() - start) + "ms to count the words.");
                        } catch (FileNotFoundException e) {
                            System.out.println("Couldn't find file: " + fileName);
                        }
                    }).start();
                });
    }
}
