package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        Post post = repository.getById(id);
        if (post == null) {
            throw new NotFoundException("Post not found with id: " + id);
        }
        return post;
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        if (repository.getById(id) == null) {
            throw new NotFoundException("Post not found with id: " + id);
        }
        repository.removeById(id);
    }
}