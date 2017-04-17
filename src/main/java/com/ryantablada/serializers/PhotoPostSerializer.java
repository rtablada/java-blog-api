package com.ryantablada.serializers;

import java.util.HashMap;
import java.util.Map;

import com.ryantablada.entities.HasId;
import com.ryantablada.entities.PhotoPost;

public class PhotoPostSerializer extends JsonDataSerializer {

  public String getType() {
    return "photo-posts";
  }

  public Map<String, Object> getAttributes(HasId entity) {
    Map<String, Object> result = new HashMap<>();
    PhotoPost post = (PhotoPost) entity;

    result.put("photo-url", post.getPhotoUrl());
    result.put("caption", post.getCaption());

    return result;
  }

  public Map<String, String> getRelationshipUrls() {
    return new HashMap<String, String>();
  }
}
