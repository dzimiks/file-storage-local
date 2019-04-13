package main;


import models.Arhive;
import models.LocalDirectory;
import models.LocalFile;
import models.Storage;

import java.io.File;
import java.io.IOException;

/**
 * @author dzimiks
 * Date: 12-04-2019 at 17:45
 */
public class Main {

	public static void main(String[] args) throws IOException {
//        File file = new File("/Users/dzimiks/Desktop/projects/file-storage-local/src/file/file.txt");
//        File folder = new File("/Users/dzimiks/Desktop/projects/file-storage-local/src/file");
//        String path = "/Users/dzimiks/Desktop/projects/file-storage-local/src";
//
//        Arhive arhive = new Arhive();
//        arhive.zipFile(file, path);
//        arhive.zipFolder(folder, path);

		// TODO: File test
		LocalFile localFile = new LocalFile();
//		localFile.create("localFile.txt", "./src/file");
//		localFile.delete("localFile.txt");
//		localFile.downloadFile(
//				"/Users/dzimiks/Desktop/projects/file-storage-local/src/file",
//				"/Users/dzimiks/Desktop/projects/file-storage-local/src"
//		);
//		localFile.move(
//				"/Users/dzimiks/Desktop/projects/file-storage-local/src/file/localFile.txt",
//				"/Users/dzimiks/Desktop/projects/file-storage-local/src/moved.txt"
//		);
//
//		localFile.rename("renamed.txt", "/Users/dzimiks/Desktop/projects/file-storage-local/src/rename-test.txt");

		// TODO: Directory test
		LocalDirectory localDirectory = new LocalDirectory();
//		localDirectory.create("dir", "./src/file");
//		localDirectory.create("dir2", "./src/file/dir");
//		localFile.create("file1.txt", "./src/file/dir");
//		localFile.create("file2.txt", "./src/file/dir/dir2");
//		localDirectory.delete("./src/file/dir/dir2");
//		localDirectory.move(
//				"/Users/dzimiks/Desktop/projects/file-storage-local/src/file/dir/dir2",
//				"/Users/dzimiks/Desktop/projects/file-storage-local/src/file/dir2"
//		);
//
//		localDirectory.rename("dir-new", "/Users/dzimiks/Desktop/projects/file-storage-local/src/file/dir/dir2");
	}
}
