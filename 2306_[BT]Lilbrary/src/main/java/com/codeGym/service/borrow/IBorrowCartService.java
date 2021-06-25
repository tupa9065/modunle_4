package com.codeGym.service.borrow;

import com.codeGym.model.Book;
import com.codeGym.model.BorrowCard;
import com.codeGym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBorrowCartService extends IGeneralService<BorrowCard> {
    Page<BorrowCard> findAll(Pageable pageable);
}
