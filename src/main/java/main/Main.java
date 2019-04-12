package main;


import models.Arhive;

import java.io.File;
import java.io.IOException;

/**
 * @author dzimiks
 * Date: 12-04-2019 at 17:45
 */
public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/dzimiks/Desktop/projects/file-storage-local/src/file/file.txt");
        File folder = new File("/Users/dzimiks/Desktop/projects/file-storage-local/src/file");
        String path = "/Users/dzimiks/Desktop/projects/file-storage-local/src";

        Arhive arhive = new Arhive();
        arhive.zipFile(file, path);
        arhive.zipFolder(folder, path);
    }
}
