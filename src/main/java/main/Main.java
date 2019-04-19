package main;


import models.Arhive;
import models.LocalDirectory;
import models.LocalFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
//		localFile.delete("./src/file/localFile.txt");
//		localFile.move(
//				"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/localFile.txt",
//				"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/"
//		);

//		localFile.rename("renamed.txt", "/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/localFile.txt");
//		localFile.upload(
//				"/home/slime/Desktop/test.txt",
//				"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file"
//		);

//		ArrayList<File> files = new ArrayList<>();
//		files.add(new File("//home/slime/Desktop/test.txt"));
//		files.add(new File("/home/slime/Desktop/upload/upload.txt"));
//		files.add(new File("/Users/dzimiks/Desktop/upload/uploaded.txt"));
//		files.add(new File("/Users/dzimiks/Desktop/upload/dir/test1.txt"));

//		localFile.uploadMultiple(files, "/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/multiple");
//		localFile.uploadMultipleZip(files, "/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/multiple");

		// TODO: Directory test
		LocalDirectory localDirectory = new LocalDirectory();
//		localDirectory.create("dir", "./src/file");
//		localDirectory.create("dir2", "./src/file/dir");
//		localFile.create("file1.txt", "./src/file/dir");
//		localFile.create("file2.txt", "./src/file/dir/dir2");
//		localDirectory.delete("./src/file/dir/dir2");
//		localDirectory.move(
//				"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/dir/dir2",
//				"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/"
//		);
//
//		localDirectory.rename("dir-new", "/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/dir/dir2");
//		localDirectory.upload(
//				"/home/slime/Desktop/upload",
//				"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file"
//		);

//		ArrayList<File> directories = new ArrayList<>();
//		directories.add(new File("/home/slime/Desktop/upload"));
//		directories.add(new File("/home/slime/Desktop/test"));

//		localDirectory.uploadMultiple(directories, "/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/multiple");
//		localDirectory.uploadMultipleZip(directories, "/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageLocal/file-storage-local/src/file/multiple");

//		localDirectory.listFiles("./src", false);
//		System.out.println();
//		localDirectory.listFiles("./src", true);
//		System.out.println();
//		localDirectory.listFilesWithExtensions("./src", new String[]{"java"}, false);
//		System.out.println();
//		localDirectory.listDirs("./src", false);
	}
}
