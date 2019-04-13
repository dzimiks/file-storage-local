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

    private Path path;

    public LocalDirectory() {

    }

    //TODO handle already exist
    @Override
    public void create(String name, String path) {
        Path dirPath;

        if (path != null && !path.equals("") && !path.equals(File.separator)) {
            dirPath = Paths.get(path);
        } else {
            dirPath = Paths.get(name);
        }

        this.path = dirPath;
        if (Files.exists(dirPath) && name != "") {
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

    @Override
    public void download(String src, String dest) {

    }

    @Override
    public void upload(String src, String dest) {
        Path source = Paths.get(src);
        Path destination = Paths.get(dest);
        if (Files.exists(source) && Files.exists(destination)) {
            try {
                FileUtils.copyDirectory(new File(src), new File(dest+File.separator+src.substring(src.lastIndexOf(File.separator) + 1)));
                System.out.printf("Directory %s is successfully uploaded to %s!\n", src, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Upload directory exception");
//            System.out.println(new DirectoryUploadException());
        }
    }

    @Override
    public void uploadMultiple(ArrayList<File> directories, String dest) {
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
        }else {
//            System.out.println(new UploadMultipleDirectoriesException());
            System.out.println("Upload multiple directories exception");
        }

    }

    @Override
    public void uploadMultipleZip(ArrayList<File> directories, String dest) {
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
        }else{
//            System.out.println(new UploadMultipleDirectoriesZipException());
            System.out.println("Upload multiple directories zip exception");
        }
    }

    // TODO: Add exception if path is null or empty string
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

        if (Files.exists(source) && Files.exists(destination)) {
            try {
                FileUtils.moveDirectory(new File(src), new File(dest+File.separator+src.substring(src.lastIndexOf(File.separator) + 1)));
                System.out.printf("Directory %s is successfully moved to %s!\n", src, dest);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }else{
//            System.out.println(new MoveDirectoryException());
            System.out.println("Move directory exception");
        }
    }

    // TODO: Handle exceptions
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
        }else{
//            System.out.println(new RenameDirectoryException());
            System.out.println("Rename directory exception");
        }

    }

    @Override
    public void listFiles(String path, boolean sorted) {
        Path dirPath = Paths.get(path);

        if(Files.exists(dirPath)){
            System.out.println("List of all files in directory '" + Paths.get(path).getFileName() + "':\n");

            ArrayList<File> files = new ArrayList<>(FileUtils.listFiles(new File(path), null, true));

            if (sorted) {
                Collections.sort(files);
            }

            for (File file : files) {
                System.out.println(file.getName());
            }
        }else{
//            System.out.println(new DirectoryListFilesException());
            System.out.println("Directory list files exception");
        }

    }

    @Override
    public void listFilesWithExtensions(String path, String[] extensions, boolean sorted) {
        Path dirPath = Paths.get(path);

        if(Files.exists(dirPath) && extensions.length!=0){
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
        }else{
//            System.out.println(new DirectoryListFilesWithExtensionsException());
            System.out.println("Directory list files with extension exception");
        }

    }

    @Override
    public void listDirs(String path, boolean sorted) {
        Path dirPath = Paths.get(path);
        if(Files.exists(dirPath)){
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
        }else{
//            System.out.println(new DirectoryListDirecotiesException());
            System.out.println("Directory list directories exception");
        }

    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
