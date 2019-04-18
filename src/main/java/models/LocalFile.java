package models;

import exceptions.CreateFileException;

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

    /**
     * Creates new file instance on given path.
     *
     * @param name Name of the file.
     * @param path Path where file should be created at.
     */
    @Override
    public void create(String name, String path) {
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
            System.out.println(new CreateFileException());
        }

    }

    /**
     * Deletes file from given path.
     * @param path Path of the file on the storage.
     */
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

        if (Files.exists(filePath)) {
            try {
                Files.deleteIfExists(filePath);
                System.out.printf("File %s is successfully deleted from path %s!\n", filePath, filePath.toAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//            System.out.println(new DeleteFileException());
            System.out.println("File " + filePath + " doesn't exists at given path!");
        }
    }

    @Override
    public void download(String src, String dest) {

    }

    @Override
    public void upload(String src, String dest) {
        Path source = Paths.get(src);
        Path destination = Paths.get(dest);

        if (Files.exists(source) && Files.exists(destination) && !Files.exists(Paths.get(destination+File.separator+src.substring(src.lastIndexOf(File.separator) + 1)))) {
            try {
                Files.copy(source,Paths.get(destination+File.separator+src.substring(src.lastIndexOf(File.separator) + 1)), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("File %s is successfully uploaded to %s!\n", src, dest);
        } else {
//            System.out.println(new UploadFileException());
            System.out.println("Upload file exception");
        }

    }

    @Override
    public void uploadMultiple(ArrayList<File> files, String dest) {
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
            System.out.println("Upload multiple files exception");
        }


    }

    @Override
    public void uploadMultipleZip(ArrayList<File> files, String dest) {
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
            System.out.println("Upload multiple files zip exception");
        }

    }


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
            destination = Paths.get(dest);
        } else {
            destination = Paths.get("invalid destination path");
        }

        if (Files.exists(source) && Files.exists(destination) && !Files.exists(Paths.get(destination+File.separator+src.substring(src.lastIndexOf(File.separator) + 1)))) {
            try {
                Files.move(source, Paths.get(destination+File.separator+src.substring(src.lastIndexOf(File.separator) + 1)), StandardCopyOption.REPLACE_EXISTING);
                System.out.printf("File %s is successfully moved to %s!\n", src, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//            System.out.println(new MoveFileException());
            System.out.println("Move file exception");
        }
    }


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
                System.out.printf("File %s is successfully renamed to %s!\n",
                        path.substring(path.lastIndexOf(File.separator) + 1), name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//            System.out.println(new RenameFileException());
            System.out.println("Rename file exception");
        }

    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
