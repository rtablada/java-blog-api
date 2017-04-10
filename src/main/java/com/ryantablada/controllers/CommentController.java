package com.ryantablada.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ryantablada.entities.Comment;
import com.ryantablada.parsers.RootParser;
import com.ryantablada.repositories.CommentRepository;
import com.ryantablada.serializers.CommentSerializer;
import com.ryantablada.serializers.RootSerializer;

@RestController
public class CommentController {
  
  @Autowired
  CommentRepository comments;

  RootSerializer rootSerializer;
  CommentSerializer commentSerializer;

  public CommentController() {
    this.rootSerializer = new RootSerializer();
    this.commentSerializer = new CommentSerializer();
  }

  @RequestMapping(path = "/comments", method = RequestMethod.GET)
  public HashMap<String, Object> findAllComment() {
    Iterable<Comment> results = comments.findAll();

    return rootSerializer.serializeMany("/comments", results, commentSerializer);
  }

  @RequestMapping(path = "/comments/{id}", method = RequestMethod.GET)
  public HashMap<String, Object> findOneComment(@PathVariable("id") String id) {
    Comment comment = comments.findOne(id);

    return rootSerializer.serializeOne("/comments/" + comment.getId(), comment, commentSerializer);
  }


  @RequestMapping(path  = "/comments", method = RequestMethod.POST)
  public HashMap<String, Object> storeComment(@RequestBody RootParser<Comment> parser) {
    Comment comment = parser.getData().getEntity();
    comment.setPost(parser.getData().getRelationshipId("post"));

    comments.save(comment);

    return this.rootSerializer.serializeOne("/comments/" + comment.getId(), comment, this.commentSerializer);
  }

  @RequestMapping(path = "/comments/{id}", method = RequestMethod.PATCH)
  public HashMap<String, Object> updateComment(@PathVariable("id") String id, @RequestBody RootParser<Comment> parser) {
    Comment existingComment = comments.findOne(id);
    Comment input = parser.getData().getEntity();

    existingComment.setContent(input.getContent());
    existingComment.setPost(parser.getData().getRelationshipId("post"));
    
    comments.save(existingComment);

    return rootSerializer.serializeOne("/comments/" + existingComment.getId(), existingComment, commentSerializer);
  }

  @RequestMapping(path = "/comments/{id}", method = RequestMethod.DELETE)
  public void deleteComment(@PathVariable("id") String id, HttpServletResponse response) {
    comments.delete(id);

    response.setStatus(204);
  }
}
