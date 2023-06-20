package com.exzray.spring6webapp.service;

import com.exzray.spring6webapp.domain.author.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
