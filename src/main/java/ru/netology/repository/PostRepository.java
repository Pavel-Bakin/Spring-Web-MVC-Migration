package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();
    private long postIdCounter = 1;

    public List<Post> all() {
        return posts;
    }

    public Post getById(long id) {
        return posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(postIdCounter++);
            posts.add(post);
        } else {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getId() == post.getId()) {
                    posts.set(i, post);
                    break;
                }
            }
        }
        return post;
    }

    public void removeById(long id) {
        posts.removeIf(post -> post.getId() == id);
    }
}