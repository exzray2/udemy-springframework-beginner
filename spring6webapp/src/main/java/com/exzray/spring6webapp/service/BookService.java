package com.exzray.spring6webapp.service;

import com.exzray.spring6webapp.domain.book.Book;

public interface BookService {
    Iterable<Book> findAll();
}
