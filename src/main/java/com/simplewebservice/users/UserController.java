package com.simplewebservice.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.simplewebservice.exceptions.PostNotFoundException;
import com.simplewebservice.exceptions.UserNotFoundException;
import com.simplewebservice.posts.Post;
import com.simplewebservice.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " Not Found");
        }
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setBirthDate(user.getBirthDate());
            userRepository.save(updatedUser);
            return ResponseEntity.ok().body(updatedUser);
        } else {
            throw new UserNotFoundException("User with id " + id + " Not Found");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " Not Found");
        }
        return user.get().getPosts();
    }

    @GetMapping("/users/{id}/posts/{post_id}")
    public Post retrievePostById(@PathVariable int post_id) {
        Optional<Post> existingPost = postRepository.findById(post_id);
        if (existingPost.isEmpty()) {
            throw new PostNotFoundException("Post with id " + post_id + " Not Found");
        }
        return existingPost.get();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " Not Found");
        }
        User userToCreatePost = user.get();
        post.setUser(userToCreatePost);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).body(post);
    }

    @PutMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Post> updatePost(@PathVariable int post_id, @RequestBody Post post) {
        Optional<Post> existingPost = postRepository.findById(post_id);
        if (existingPost.isEmpty()) {
            throw new PostNotFoundException("Post with id " + post_id + " Not Found");
        }
        Post postToUpdate = existingPost.get();
        postToUpdate.setDescription(post.getDescription());
        postRepository.save(postToUpdate);
        return ResponseEntity.ok().body(postToUpdate);
    }

    @DeleteMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Object> deletePost(@PathVariable int post_id) {
        Optional<Post> existingPost = postRepository.findById(post_id);
        if (existingPost.isEmpty()) {
            throw new PostNotFoundException("Post with id " + post_id + " Not Found");
        }
        postRepository.deleteById(post_id);
        return ResponseEntity.noContent().build();
    }
}
