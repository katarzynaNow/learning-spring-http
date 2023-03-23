package com.example.springhttp.Profile.model;

import com.example.springhttp.Profile.service.common.CrudResource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;

public class Profile implements CrudResource<Integer> {

  private Integer id;
  @Size(min = 3, max = 50)
  @Pattern(regexp = "[a-zA-Z]+")
  private String imie;
  @Size(min = 3, max = 50)
  @Pattern(regexp = "[a-zA-Z]+")
  private String nazwisko;
  @DateTimeFormat(pattern = "yyyy-MD-dd")
  @NotBlank
  private String dataUrodzenia;
  private byte[] zdjecie;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer integer) {
    this.id = integer;
  }

  public String getImie() {
    return imie;
  }

  public void setImie(String imie) {
    this.imie = imie;
  }

  public String getNazwisko() {
    return nazwisko;
  }

  public void setNazwisko(String nazwisko) {
    this.nazwisko = nazwisko;
  }

  public String getDataUrodzenia() {
    return dataUrodzenia;
  }

  public void setDataUrodzenia(String dataUrodzenia) throws ParseException {
    this.dataUrodzenia = dataUrodzenia;
  }

  public byte[] getZdjecie() {
    return zdjecie;
  }

  public void setZdjecie(byte[] zdjecie) {
    this.zdjecie = zdjecie;
  }
}
