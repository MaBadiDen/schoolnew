package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    private final AvatarService avatarService;

    @GetMapping(value = "/{id}/fromDB")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findStudentAvatar(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);;

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/fromPC")
    public void getAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findStudentAvatar(id);

        Path path = Path.of(avatar.getFilePath());
        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();) {
            response.setContentLength((int) avatar.getFileSize());
            response.setContentType(avatar.getMediaType());
            is.transferTo(os);
        }
    }
    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if(avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<Collection<Avatar>> getAll(@PathVariable("pageNumber") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        return avatarService.getAll(pageNumber, pageSize);
    }



}
