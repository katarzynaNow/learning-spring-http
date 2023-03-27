package com.example.springhttp.profile.service;

import com.example.springhttp.profile.model.Profile;
import com.example.springhttp.common.CrudService;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends CrudService<Profile, Integer> {
  public ProfileService() {
    super(Integer.class);
  }
}
