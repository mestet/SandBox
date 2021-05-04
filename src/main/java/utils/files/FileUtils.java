package utils.files;

import lombok.extern.java.Log;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.apache.commons.io.FileUtils.readFileToString;

@Log
public class FileUtils {

    public static final String TMP_DIR_PATH = "tmp/";

    public static boolean createFolder(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            return folder.mkdir();
        }
        return false;
    }

    public static void deleteFolder(String folderName) {
        File dir = new File(folderName);
        if (dir.exists()) {
            try (Stream<Path> pathStream = Files.walk(Paths.get(folderName))) {
                pathStream.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete folder", e);
            }
        }
    }

    public static String readFileAsString(File file) {
        try {
            return readFileToString(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    public static File createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create file " + path, e);
        }
        return path.toFile();
    }

    public static List<File> splitFile(File sourceFile, int partSize) {
        int partNumber = 0;
        byte[] buffer = new byte[partSize];

        List<File> fileList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(sourceFile);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                String filePartName = String.format("%s.%03d", sourceFile.getName(), partNumber++);
                File newFile = new File(sourceFile.getParent(), filePartName);
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    fos.write(buffer, 0, bytesAmount);
                }
                fileList.add(newFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileList;
    }

    public static void unZip(File file, String destinationFolder) {
        Path path = Paths.get(destinationFolder);
        createFolder(path.toString());

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                writeZipEntryToFile(destinationFolder, zipInputStream, zipEntry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeZipEntryToFile(String destinationFolder,
                                            ZipInputStream zipInputStream,
                                            ZipEntry zipEntry) {
        byte[] buff = new byte[1024];
        Path pathToFile = Paths.get(destinationFolder, zipEntry.getName());
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToFile.toFile())) {
            int len;
            while ((len = zipInputStream.read(buff)) > 0) {
                fileOutputStream.write(buff, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File loadFileFromResources(String path) {
        try {
            return new ClassPathResource(path).getFile();
        } catch (IOException e) {
            throw new RuntimeException("Error loading file from resources", e);
        }
    }
}
