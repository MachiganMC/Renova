package be.machigan.renova.controller;

import be.machigan.renova.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/pictures")
@RequiredArgsConstructor
public class PictureController {
    private final PictureService pictureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPicture(@RequestParam MultipartFile picture) throws IOException {
        this.pictureService.addPicture(picture);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.pictureService.delete(id);
    }
}
