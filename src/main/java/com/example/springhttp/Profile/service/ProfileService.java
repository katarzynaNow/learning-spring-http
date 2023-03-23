package com.example.springhttp.Profile.service;

import com.example.springhttp.Profile.model.Profile;
import com.example.springhttp.Profile.service.common.CrudService;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends CrudService<Profile, Integer> {
  public ProfileService() {
    super(Integer.class);
  }
}
