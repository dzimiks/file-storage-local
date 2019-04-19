package models;

import exceptions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dzimiks
 * Date: 12-04-2019 at 22:19
 */
public class LocalFile implements BasicFile {
	/**
	 * @var path Variable used for storing path of created file.
	 */
	private Path path;

	/**
	 * File constructor.
	 */
	public LocalFile() {

	}

	/**
	 * Creates new file instance on given path.
	 *
	 * @param name Name of the file.
	 * @param path Path where file should be created at on local storage.
	 */
	@Override
	public void create(String name, String path) throws CreateFileException{
		Path filePath;

		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			filePath = Paths.get(path);
		} else {
			filePath = Paths.get(name);
		}

		this.path = filePath;

//        System.out.println(filePath);
		if (Files.exists(filePath) && name != "" && !Files.exists(Paths.get(filePath + File.separator + name))) {
			try {
				Files.createFile(Paths.get(filePath + File.separator + name));
				System.out.printf("File %s is successfully created at path %s!\n", name, filePath.toAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//			System.out.println(new CreateFileException());
			throw new CreateFileException();
		}

	}

	/**
	 * Deletes file from given path.
	 *
	 * @param path Path of the file on the local storage.
	 */
	@Override
	public void delete(String path) throws DeleteException {
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

		if (Files.exists(filePath)) {
			try {
				Files.deleteIfExists(filePath);
				System.out.printf("File %s is successfully deleted from path %s!\n", filePath, filePath.toAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//            System.out.println(new DeleteFileException());
//			System.out.println("File " + filePath + " doesn't exists at given path!");
			throw new DeleteException();

		}
	}

	/**
	 * Downloads file from given path.
	 * (Uses move() because that operation simulates downloading file from
	 * local storage)
	 *
	 * @param src  Path of the file on the storage.
	 * @param dest Path of the directory where we want to download it.
	 */
	@Override
	public void download(String src, String dest) {
		try {
			move(src, dest);
		} catch (MoveException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uploads file to local storage on given path.
	 *
	 * @param src  Path of the file on the storage.
	 * @param dest Path of the local storage directory where we want to upload it.
	 */
	@Override
	public void upload(String src, String dest) throws UploadException {
		Path source = Paths.get(src);
		Path destination = Paths.get(dest);

		if (Files.exists(source) && Files.exists(destination) && !Files.exists(Paths.get(destination + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)))) {
			try {
				Files.copy(source, Paths.get(destination + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.printf("File %s is successfully uploaded to %s!\n", src, dest);
		} else {
//            System.out.println(new UploadFileException());
//			System.out.println("Upload file exception");
			throw new UploadException();
		}

	}

	/**
	 * Uploads multiple files on given path in local storage.
	 *
	 * @param files List of files.
	 * @param dest  Path on the local storage where we want to upload filese.
	 * @param name  Name of created zip.
	 */
	@Override
	public void uploadMultiple(List<File> files, String dest, String name) throws UploadMultipleException {
		Path path = Paths.get(dest);

		if (Files.exists(path) && files.size() != 0) {
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
			System.out.println("Files are successfully uploaded to " + dest);
		} else {
//            System.out.println(new UploadMultipleFilesException());
//			System.out.println("Upload multiple files exception");
			throw new UploadMultipleException();
		}


	}

	/**
	 * Uploads multiple zipped files on given path in local storage.
	 *
	 * @param files List of files.
	 * @param dest  Path on local storage where we want to upload zipped files.
	 * @param name  Name of created zip
	 */
	@Override
	public void uploadMultipleZip(List<File> files, String dest, String name) throws UploadMultipleZipException {
		Path path = Paths.get(dest);
		Arhive arhive = new Arhive();
		if (Files.exists(path) && files.size() != 0) {
			for (File file : files) {
				try {
					arhive.zipFile(file, file.getName().substring(0, file.getName().lastIndexOf('.')), dest);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Files are successfully zipped and uploaded to " + dest);
		} else {
//            System.out.println(new UploadMultipleFilesZipException());
//			System.out.println("Upload multiple files zip exception");
			throw new UploadMultipleZipException();
		}

	}

	/**
	 * Moves file to given path.
	 *
	 * @param src  Path of the file on local storage.
	 * @param dest Path where we want to move file.
	 */
	@Override
	public void move(String src, String dest) throws MoveException {
		Path source;
		Path destination;
		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			source = Paths.get(src);
		} else {
			source = Paths.get("invalid source path");
		}
		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			destination = Paths.get(dest);
		} else {
			destination = Paths.get("invalid destination path");
		}

		if (Files.exists(source) && Files.exists(destination) && !Files.exists(Paths.get(destination + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)))) {
			try {
				Files.move(source, Paths.get(destination + File.separator + src.substring(src.lastIndexOf(File.separator) + 1)), StandardCopyOption.REPLACE_EXISTING);
				System.out.printf("File %s is successfully moved to %s!\n", src, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//            System.out.println(new MoveFileException());
//			System.out.println("Move file exception");
			throw new MoveException();
		}
	}

	/**
	 * Renames current file.
	 *
	 * @param name New name for the file.
	 * @param path Path of the file on local storage.
	 */
	@Override
	public void rename(String name, String path) throws RenameException {
		Path source;
		if (path != null && !path.equals("") && !path.equals(File.separator)) {
			source = Paths.get(path);
		} else {
			source = Paths.get("invalid source path");
		}

		if (Files.exists(source) && name != "") {
			try {
				Files.move(source, source.resolveSibling(name));
				System.out.printf("File %s is successfully renamed to %s!\n",
						path.substring(path.lastIndexOf(File.separator) + 1), name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//            System.out.println(new RenameFileException());
//			System.out.println("Rename file exception");
			throw new RenameException();
		}
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
