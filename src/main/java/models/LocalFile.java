package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * @author dzimiks
 * Date: 12-04-2019 at 22:19
 */
public class LocalFile implements BasicFile {

	private Path path;

	public LocalFile() {

	}

	@Override
	public void create(String name, String path) {
		Path filePath;

		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			filePath = Paths.get(path + File.separator + name);
		} else {
			filePath = Paths.get(name);
		}

		this.path = filePath;

		try {
			Files.createFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("File %s is successfully created at path %s!\n", name, filePath.toAbsolutePath());
	}

	@Override
	public void delete(String path) {
		Path filePath = null;

		try {
			if (path != null && !path.equals("") && !path.equals(File.separator)) {
				filePath = Paths.get(path);
			} else {
				throw new IOException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean exists = false;

		try {
			if (filePath != null) {
				exists = Files.deleteIfExists(filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (exists) {
			System.out.printf("File %s is successfully deleted from path %s!\n", filePath, filePath.toAbsolutePath());
		} else {
			System.out.println("File " + filePath + " doesn't exists at given path!");
		}
	}

	@Override
	public void download(String src, String dest) {

	}

	@Override
	public void upload(String src, String dest) {
		try {
			Files.copy(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("File %s is successfully uploaded to %s!\n", src, dest);
	}

	public void uploadMultiple(ArrayList<File> files, String dest) {
		for (File file : files) {
			try {
				Files.copy(
						Paths.get(file.getAbsolutePath()),
						Paths.get(dest + File.separator + file.getName()),
						StandardCopyOption.REPLACE_EXISTING
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO: Add exception if path is null or empty string
	@Override
	public void move(String src, String dest) {
		try {
			Files.move(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("File %s is successfully moved to %s!\n", src, dest);
	}

	// TODO: Handle exceptions
	@Override
	public void rename(String name, String path) {
		Path source = Paths.get(path);

		try {
			Files.move(source, source.resolveSibling(name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("File %s is successfully renamed to %s!\n",
				path.substring(path.lastIndexOf(File.separator) + 1), name);
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
}
