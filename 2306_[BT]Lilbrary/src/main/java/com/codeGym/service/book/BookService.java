package com.codeGym.service.book;

import com.codeGym.model.Book;
import com.codeGym.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository repository;
    @Override
    public Page findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByNameContaining(String name, Pageable pageable) {
        return repository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Page<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
