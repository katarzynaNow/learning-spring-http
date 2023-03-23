package com.example.springhttp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    public static class Picture {
        public byte[] content;
        public String name;
        public String mimeType;
    }

    private List<Picture> pictures = new ArrayList<>();

    @GetMapping
    public String gallery(Model model) {
        model.addAttribute("pictures", pictures);
        return "gallery";
    }

    @PostMapping
    public String submit(@RequestParam("picture")MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "redirect:/gallery";
        }
        Picture newPicture = new Picture();
        newPicture.name = file.getOriginalFilename();
        newPicture.content = file.getBytes();
        newPicture.mimeType = file.getContentType();
        pictures.add(newPicture);
        return "redirect:/gallery";
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> content(@PathVariable String name){
        for (Picture p: pictures){
            if(p.name.equals(name)){
                return ResponseEntity.status((HttpStatus.OK))
                        .contentType(MediaType.parseMediaType(p.mimeType))
                        .body(p.content);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new byte[0]);
    }
}
