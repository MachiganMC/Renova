package be.machigan.renova.service;

import be.machigan.renova.exception.InvalidFileTypeException;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {
    private final ServletContext servletContext;

    private Path getFolderPath(String folderName) {
        return Paths.get(System.getProperty("user.dir") + "/" + folderName);
    }

    public void saveTo(String folderName, String fileName, MultipartFile file) throws IOException {
        Path path = this.getFolderPath(folderName);
        if (!Files.exists(path))
            Files.createDirectories(path);
        Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    public boolean isAnImage(MultipartFile file) {
        return Objects.requireNonNullElse(
                this.servletContext.getMimeType(file.getOriginalFilename()),
                ""
        ).startsWith("image/");
    }

    public Resource getResource(String folder, String file) throws MalformedURLException, FileNotFoundException {
        Resource resource = new UrlResource(this.getFolderPath(folder).resolve(file).toUri());
        if (!resource.exists())
            throw new FileNotFoundException();
        return resource;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void delete(String folder, String fileName) {
        File file = this.getFolderPath(folder).resolve(fileName).toFile();
        if (file.exists())
            file.delete();
    }

    public String getExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null)
            throw new InvalidFileTypeException("No file extension");
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
