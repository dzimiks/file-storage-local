package models;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author dzimiks
 * Date: 12-04-2019 at 21:11
 */
public class LocalDirectory implements Directory {

	private Path path;

	public LocalDirectory() {

	}

	@Override
	public void create(String name, String path) {
		Path dirPath;

		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			dirPath = Paths.get(path + File.separator + name);
		} else {
			dirPath = Paths.get(name);
		}

		this.path = dirPath;

		try {
			Files.createDirectory(dirPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("Directory %s is successfully created at path %s!\n", name, dirPath.toAbsolutePath());
	}

	@Override
	public void delete(String path) {
		Path dirPath = null;

		try {
			if (path != null && !path.equals("") && !path.equals(File.separator)) {
				dirPath = Paths.get(path);
			} else {
				throw new IOException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (path != null) {
				FileUtils.deleteDirectory(new File(path));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (dirPath != null) {
			System.out.printf("Directory %s is successfully deleted from path %s!\n", dirPath, dirPath.toAbsolutePath());
		}
	}

	@Override
	public void download(String src, String dest) {

	}

	@Override
	public void upload(String src, String dest) {
		try {
			FileUtils.copyDirectory(new File(src), new File(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("Directory %s is successfully uploaded to %s!\n", src, dest);
	}

	@Override
	public void uploadMultiple(ArrayList<File> directories, String dest) {
		for (File dir : directories) {
			try {
				FileUtils.copyDirectory(
						new File(dir.getAbsolutePath()),
						new File(dest + File.separator + dir.getName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Directories are successfully uploaded to " + dest);
	}

	@Override
	public void uploadMultipleZip(ArrayList<File> directories, String dest) {
		Arhive arhive = new Arhive();

		for (File dir : directories) {
			try {
				arhive.zipDirectory(dir, dir.getName(), dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Directories are successfully zipped and uploaded to " + dest);
	}

	// TODO: Add exception if path is null or empty string
	@Override
	public void move(String src, String dest) {
		try {
			FileUtils.moveDirectory(new File(src), new File(dest));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.printf("Directory %s is successfully moved to %s!\n", src, dest);
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

		System.out.printf("Directory %s is successfully renamed to %s!\n",
				path.substring(path.lastIndexOf(File.separator) + 1), name);
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
}
