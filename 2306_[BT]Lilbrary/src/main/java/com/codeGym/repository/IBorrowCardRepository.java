package com.codeGym.repository;

import com.codeGym.model.BorrowCard;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBorrowCardRepository extends PagingAndSortingRepository<BorrowCard,Long> {
}
