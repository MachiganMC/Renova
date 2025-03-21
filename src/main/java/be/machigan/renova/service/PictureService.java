package be.machigan.renova.service;

import be.machigan.renova.entity.Picture;
import be.machigan.renova.exception.InvalidFileTypeException;
import be.machigan.renova.repository.PictureRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;
    private final FileService fileService;
    @Value("${application.pictures-folder}")
    private String picturesFolder;

    public List<Picture> findAll() {
        return this.pictureRepository.findAll();
    }

    public Picture findById(UUID uuid) {
        return this.pictureRepository
                .findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("The picture with id " + uuid + " hasn't been found"));
    }

    public void addPicture(MultipartFile file) throws IOException {
        if (!this.fileService.isAnImage(file))
            throw new InvalidFileTypeException("The file isn't an image");
        Picture picture = new Picture();
        picture.setExtension(this.fileService.getExtension(file));
        picture = this.pictureRepository.save(picture);
        this.fileService.saveTo(this.picturesFolder, picture.getFullName(), file);
    }

    public Resource getPictureResources(UUID uuid) throws IOException {
        Picture picture = this.findById(uuid);
        return this.fileService.getResource(this.picturesFolder, picture.getFullName());
    }

    public void delete(UUID uuid) {
        Picture picture = this.findById(uuid);
        this.pictureRepository.delete(picture);
        this.fileService.delete(this.picturesFolder, picture.getFullName());
    }

    public void addPicturesToModel(Model model) {
        List<Picture> pictures = this.findAll();
        model.addAttribute("pictures", pictures);
        model.addAttribute("picturesUuid", pictures.stream().map(Picture::getUuid).toArray());
    }
}
