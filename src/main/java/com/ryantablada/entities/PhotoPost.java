package com.ryantablada.entities;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "photo_posts")
public class PhotoPost implements HasId {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  String id;

  @Column
  @JsonProperty("photo-url")
  String photoUrl;

  @Column
  String caption;

  public void setId(String val) {
    this.id = val;
  }

  public void setCaption(String val) {
    this.caption = val;
  }

  public void setPhotoUrl(String val) {
    this.photoUrl = val;
  }

  public String getId() {
    return this.id;
  }

  public String getCaption() {
    return this.caption;
  }

  public String getPhotoUrl() {
    return this.photoUrl;
  }
}
