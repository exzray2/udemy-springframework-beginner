package com.exzray.spring6webapp.bootstrap;

import com.exzray.spring6webapp.domain.author.Author;
import com.exzray.spring6webapp.domain.author.AuthorRepository;
import com.exzray.spring6webapp.domain.book.Book;
import com.exzray.spring6webapp.domain.book.BookRepository;
import com.exzray.spring6webapp.domain.publisher.Publisher;
import com.exzray.spring6webapp.domain.publisher.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Java O'Big Library");
        publisher.setAddress("St1 Lost Street");
        publisher.setCity("Gotham City");

        publisher = publisherRepository.save(publisher);

        Author author = new Author();
        author.setFirstname("Mohd Nazirul Aiman");
        author.setLastname("Bin Rosli");

        author = authorRepository.save(author);

        Book book1 = new Book();
        book1.setTitle("Java Hard Way 1");
        book1.setIsbn("ABC123");
        book1.setPublisher(publisher);

        book1 = bookRepository.save(book1);

        author.getBooks().add(book1);
        book1.getAuthors().add(author);

        authorRepository.save(author);
        bookRepository.save(book1);
    }
}
