package com.example.springhttp.common;

public interface CrudResource<ID> {

  ID getId();

  void setId(ID id);

}
