package com.codeGym.service.book;

import com.codeGym.model.Book;
import com.codeGym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends IGeneralService<Book> {
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAllByNameContaining(String name,Pageable pageable);

}
