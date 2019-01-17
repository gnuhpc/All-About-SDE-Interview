package org.gnuhpc.bigdata.designpattern.immutable;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/*
make a class immutable make a class immutable:
classes should be immutable unless there’s a very good reason to make them mutable.
1. Don’t provide any public methods that modify the object’s state
2. Prevent the class from being extended:
    The compiler makes sure that a class without public or protected constructors
    cannot be  subclassed.  So in this case the final keyword on the class declaration is not necessary
    but it might be a good idea to include it anyway
    just to make your intention clear to anyone who sees your code.
3. Make all fields final.
4. Never provide access to any mutable attribute.
   we don't assign the reference directly. Instead, we use the Guava to make a copy of the list
5.

 */
public final class Book {
    private final String isbn;
    private final int publicationYear;
    private final List reviews;
    private Book(BookBuilder builder) {
        this.isbn = builder.isbn;
        this.publicationYear = builder.publicationYear;
        this.reviews = Lists.newArrayList(builder.reviews);
    }
    public String getIsbn() {
        return isbn;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public List getReviews() {
        return Lists.newArrayList(reviews);
    }
    public static class BookBuilder {
        private String isbn;
        private int publicationYear;
        private List reviews;
        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }
        public BookBuilder publicationYear(int year) {
            this.publicationYear = year;
            return this;
        }
        public BookBuilder reviews(List reviews) {
            this.reviews = reviews == null ? new ArrayList() : reviews;
            return this;
        }
        public Book build() {
            return new Book(this);
        }
    }

    //what if you already have a Book instance and
    // you want to change the value of one of its attributes?


    private Book(String isbn, int publicationYear, List reviews) {
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.reviews = Lists.newArrayList(reviews);
    }
    public Book withIsbn(String isbn) {
        return new Book(isbn,this.publicationYear, this.reviews);
    }
}
