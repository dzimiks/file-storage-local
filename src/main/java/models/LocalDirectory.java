package models;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author dzimiks
 * Date: 12-04-2019 at 21:11
 */
public class LocalDirectory implements Directory {
	/**
	 * @var path Variable used for storing path of created directory.
	 */
	private Path path;

	/**
	 * Directory constructor.
	 */
	public LocalDirectory() {

	}

	/**
	 * Creates local directory instance on given path.
	 *
	 * @param name Name of the directory.
	 * @param path Path of the directory on the local storage.
	 */
	@Override
	public void create(String name, String path) {
		Path dirPath;

		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			dirPath = Paths.get(path);
		} else {
			dirPath = Paths.get(name);
		}

		this.path = dirPath;
		if (Files.exists(dirPath) && name != "" && !Files.exists(Paths.get(dirPath + File.separator + name))) {
			try {
				Files.createDirectory(Paths.get(dirPath + File.separator + name));
				System.out.printf("Directory %s is successfully created at path %s!\n", name, dirPath.toAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//            System.out.printf(new CreateDirectoryException());
			System.out.println("Directory create exception");
		}
	}

	/**
	 * Deletes local directory from given path.
	 *
	 * @param path Path of the directory on the local storage.
	 */
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

		if (Files.exists(dirPath)) {
			try {
				FileUtils.deleteDirectory(new File(path));
				System.out.printf("Directory %s is successfully deleted from path %s!\n", dirPath, dirPath.toAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//            System.out.println(new DeleteFileException());
			System.out.println("Directory " + dirPath + " doesn't exists at given path!");
		}

	}

	/**
	 * Downloads local directory from given path.
	 * (Uses move() because that operation simulates downloading directory from
	 * local storage)
	 *
	 * @param src  Path of the directory on the storage.
	 * @param dest Path of the directory where we want to download it.
	 */
	@Override
	public void download(String src, String dest) {
		move(src, dest);
	}


	/**
	 * Uploads directory to local storage on given path.
	 *
	 * @param src  Path of the directory on the storage.
	 * @param dest Path of the local storage directory where we want to upload it.
	 */
	@Override
	public void upload(String src, String dest) {
		Path source = Paths.get(src);
		Path destination = Paths.get(dest);
		if (Files.exists(source) && Files.exists(destination) && !Files.exists(Paths.get(dest + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)))) {
			try {
				FileUtils.copyDirectory(new File(src), new File(dest + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)));
				System.out.printf("Directory %s is successfully uploaded to %s!\n", src, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Upload directory exception");
//            System.out.println(new DirectoryUploadException());
		}
	}

	/**
	 * Uploads multiple directories on given path in local storage.
	 *
	 * @param directories List of directories.
	 * @param dest        Path on local storage where we want to upload directories.
	 * @param name        Name of created zip.
	 */
	@Override
	public void uploadMultiple(ArrayList<File> directories, String dest, String name) {
		Path path = Paths.get(dest);
		if (Files.exists(path) && directories.size() != 0) {
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
		} else {
//            System.out.println(new UploadMultipleDirectoriesException());
			System.out.println("Upload multiple directories exception");
		}

	}

	/**
	 * Uploads multiple zipped directories on given path in local storage.
	 *
	 * @param directories List of directories.
	 * @param dest        Path on local storage where we want to upload zipped directories.
	 * @param name        Name of created zip
	 */
	@Override
	public void uploadMultipleZip(ArrayList<File> directories, String dest, String name) {
		Path path = Paths.get(dest);
		Arhive arhive = new Arhive();
		if (Files.exists(path) && directories.size() != 0) {
			for (File dir : directories) {
				try {
					arhive.zipDirectory(dir, dir.getName(), dest);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Directories are successfully zipped and uploaded to " + dest);
		} else {
//            System.out.println(new UploadMultipleDirectoriesZipException());
			System.out.println("Upload multiple directories zip exception");
		}
	}

	/**
	 * Moves directory to given path.
	 *
	 * @param src  Path of the directory on local storage.
	 * @param dest Path where we want to move directory.
	 */
	@Override
	public void move(String src, String dest) {
		Path source;
		Path destination;
		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			source = Paths.get(src);
		} else {
			source = Paths.get("invalid source path");
		}
		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			destination = Paths.get(src);
		} else {
			destination = Paths.get("invalid destination path");
		}

		if (Files.exists(source) && Files.exists(destination) && !Files.exists(Paths.get(dest + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)))) {
			try {
				FileUtils.moveDirectory(new File(src), new File(dest + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)));
				System.out.printf("Directory %s is successfully moved to %s!\n", src, dest);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} else {
//            System.out.println(new MoveDirectoryException());
			System.out.println("Move directory exception");
		}
	}

	/**
	 * Renames current directory.
	 *
	 * @param name New name for the directory.
	 * @param path Path of the directory on local storage.
	 */
	@Override
	public void rename(String name, String path) {
		Path source;
		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			source = Paths.get(path);
		} else {
			source = Paths.get("invalid source path");
		}

		if (Files.exists(source) && name != "") {
			try {
				Files.move(source, source.resolveSibling(name));
				System.out.printf("Directory %s is successfully renamed to %s!\n",
						path.substring(path.lastIndexOf(File.separator) + 1), name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//            System.out.println(new RenameDirectoryException());
			System.out.println("Rename directory exception");
		}

	}

	/**
	 * Lists all files in directory from given path.
	 *
	 * @param path   Path of the directory on local storage.
	 * @param sorted True if we want to list files in sorted order.
	 */
	@Override
	public void listFiles(String path, boolean sorted) {
		Path dirPath = Paths.get(path);

		if (Files.exists(dirPath)) {
			System.out.println("List of all files in directory '" + Paths.get(path).getFileName() + "':\n");

			ArrayList<File> files = new ArrayList<>(FileUtils.listFiles(new File(path), null, true));

			if (sorted) {
				Collections.sort(files);
			}

			for (File file : files) {
				System.out.println(file.getName());
			}
		} else {
//            System.out.println(new DirectoryListFilesException());
			System.out.println("Directory list files exception");
		}

	}

	/**
	 * Lists all files with given extensions in directory from given path.
	 *
	 * @param path       Path of the directory on the local storage.
	 * @param extensions Array of file extensions.
	 * @param sorted     True if we want to list files in sorted order.
	 */
	@Override
	public void listFilesWithExtensions(String path, String[] extensions, boolean sorted) {
		Path dirPath = Paths.get(path);

		if (Files.exists(dirPath) && extensions.length != 0) {
			System.out.println("List of files with extensions " +
					Arrays.toString(extensions) + " in directory '" +
					Paths.get(path).getFileName() + "':\n");

			ArrayList<File> files = new ArrayList<>(FileUtils.listFiles(new File(path), extensions, true));

			if (sorted) {
				Collections.sort(files);
			}

			for (File file : files) {
				System.out.println(file.getName());
			}
		} else {
//            System.out.println(new DirectoryListFilesWithExtensionsException());
			System.out.println("Directory list files with extension exception");
		}

	}

	/**
	 * Lists all directories in directory from given path.
	 *
	 * @param path   Path of the directory on the local storage.
	 * @param sorted True if we want to list files in sorted order.
	 */
	@Override
	public void listDirs(String path, boolean sorted) {
		Path dirPath = Paths.get(path);
		if (Files.exists(dirPath)) {
			System.out.println("List of all directories in directory '" + Paths.get(path).getFileName() + "':\n");

			ArrayList<File> directories = new ArrayList<>(
					FileUtils.listFilesAndDirs(
							new File(path),
							new NotFileFilter(TrueFileFilter.INSTANCE),
							DirectoryFileFilter.DIRECTORY
					)
			);

			if (sorted) {
				Collections.sort(directories);
			}

			for (File dir : directories) {
				System.out.println(dir.getName());
			}
		} else {
//            System.out.println(new DirectoryListDirecotiesException());
			System.out.println("Directory list directories exception");
		}

	}

	public ArrayList<File> listDirectories(String path, boolean sorted) {
		System.out.println("List of all directories in directory '" + Paths.get(path).getFileName() + "':\n");
		ArrayList<File> directories = new ArrayList(
				FileUtils.listFilesAndDirs(
						new File(path),
						new NotFileFilter(TrueFileFilter.INSTANCE),
						DirectoryFileFilter.DIRECTORY)
		);

		if (sorted) {
			Collections.sort(directories);
		}

		return directories;
	}

	/**
	 * Used for getting path value.
	 *
	 * @return path value.
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Used for setting path value.
	 */
	public void setPath(Path path) {
		this.path = path;
	}
}
