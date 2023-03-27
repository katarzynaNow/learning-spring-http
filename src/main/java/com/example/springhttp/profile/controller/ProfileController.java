package com.example.springhttp.profile.controller;

import com.example.springhttp.profile.model.Profile;
import com.example.springhttp.profile.service.ProfileService;
import com.example.springhttp.profile.service.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/profile")
public class ProfileController {

  @Autowired
  private ProfileService profileService;

  @GetMapping
  public String defaultView() {
    return "redirect:/profile/list";
  }

  @GetMapping("/get")
  public String getProfileView(@RequestParam Integer id, Model model) {
    model.addAttribute("profile", profileService.get(id));
    return "profile/get-profile";
  }

  @GetMapping("/list")
  public String listProfilesView(Model model) throws ParseException {
    model.addAttribute("profiles", profileService.list());
    return "profile/list-profiles";
  }

  @GetMapping("/create")
  public String createProfileView(Model model) {
    model.addAttribute("newProfile", new Profile());
    return "profile/create-profile";
  }

  @GetMapping("/update")
  public String updateProfileView(@RequestParam Integer id, Model model) {
    model.addAttribute("updateProfile", profileService.get(id));
    return "profile/update-profile";
  }

  @GetMapping("/delete")
  public String deleteProfileView(@RequestParam Integer id, Model model) {
    model.addAttribute("profile", profileService.get(id));
    return "profile/delete-profile";
  }

  @PostMapping("/create")
  public String createProfileAction(@Valid Profile newProfile, BindingResult bindingResult, @RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {
    if(multipartFile.getBytes().length == 0) {
      bindingResult.addError(new FieldError("newProfile", "zdjecie", "Zdjecie jest wymagane"));
    }

    if(bindingResult.hasErrors()) {
      model.addAttribute(newProfile);
      model.addAttribute("org.springframework.validation.BindingResult.newProfile", bindingResult);
      return "profile/create-profile";
    }

    newProfile.setZdjecie(multipartFile.getBytes());
    newProfile = profileService.create(newProfile);
    return "redirect:/profile/get?id=" + newProfile.getId();
  }

  @PostMapping("/update")
  public String updateProfileAction(@RequestParam Integer id, @RequestParam("file") MultipartFile multipartFile, @Valid Profile updateProfile, BindingResult bindingResult, Model model) throws IOException {
    if(bindingResult.hasErrors()) {
      model.addAttribute(updateProfile);
      model.addAttribute("org.springframework.validation.BindingResult.updateProfile", bindingResult);
      return "profile/update-profile";
    }

    if(multipartFile.getBytes().length != 0) {
      updateProfile.setZdjecie(multipartFile.getBytes());
    } else {
      updateProfile.setZdjecie(profileService.get(id).getZdjecie());
    }
    updateProfile = profileService.update(updateProfile);
    return "redirect:/profile/get?id=" + updateProfile.getId();
  }


  @PostMapping("/delete")
  public String deleteProfileAction(@RequestParam Integer id, Model model) {
    profileService.delete(id);
    return "redirect:/profile/list";
  }

  @ResponseBody
  @GetMapping(value = "/zdjecie", produces = "image/png")
  public byte[] zdjecie(@RequestParam Integer id) {
    return profileService.get(id).getZdjecie();
  }

  @ExceptionHandler(NotFoundException.class)
  public String notFoundView() {
    return "404-profile";
  }
}