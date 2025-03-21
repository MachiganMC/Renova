package be.machigan.renova.controller;

import be.machigan.renova.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/pictures")
@RequiredArgsConstructor
public class PictureViewerController {
    private final PictureService pictureService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Resource getPicture(@PathVariable UUID id) throws IOException {
        return this.pictureService.getPictureResources(id);
    }
}
