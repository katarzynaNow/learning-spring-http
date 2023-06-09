package com.example.springhttp.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class UploadController {

  private byte[] uploaded;

  @PostMapping("/upload")
  public String upload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
    uploaded = multipartFile.getBytes();
    return multipartFile.getOriginalFilename();
  }

  @GetMapping(produces = "image/png")
  public byte[] uploaded() {

    return uploaded;
  }

}
