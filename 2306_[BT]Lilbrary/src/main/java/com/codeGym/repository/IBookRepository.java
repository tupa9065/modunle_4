package com.codeGym.repository;

import com.codeGym.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBookRepository extends PagingAndSortingRepository<Book,Long> {

    Page<Book> findAllByNameContaining(String name,Pageable pageable);
}
