package com.codeGym.controller;

import com.codeGym.exception.NotFoundException;
import com.codeGym.model.Book;
import com.codeGym.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;

    @GetMapping
    public ModelAndView showList(@PageableDefault(size = 5) Pageable pageable, @RequestParam("search") Optional<String> search) {

        ModelAndView modelAndView = new ModelAndView("/book/list");
        if (search.isPresent()) {
            modelAndView.addObject("books", bookService.findAllByNameContaining(search.get(), pageable));
        } else {
            modelAndView.addObject("books", bookService.findAll(pageable));
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("/book/create", "book", new Book());
    }

    @PostMapping("/create")
    public ModelAndView createNew(@Validated@ModelAttribute Book book, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/book/create", "book", book);
            modelAndView.addObject("message","Tao moi khong thanh cong");
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/book/create", "book", new Book());
            modelAndView.addObject("message","Tao moi  thanh cong");
            bookService.save(book);
            return modelAndView;
        }
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewInfo(@PathVariable Long id) throws NotFoundException {
        Optional<Book> bookOptional = bookService.findById(id);
        if(!bookOptional.isPresent()){
            throw new NotFoundException();
        }
        ModelAndView modelAndView = new ModelAndView("/book/view");
        modelAndView.addObject("book", bookOptional.get());
        return modelAndView;
    }

}