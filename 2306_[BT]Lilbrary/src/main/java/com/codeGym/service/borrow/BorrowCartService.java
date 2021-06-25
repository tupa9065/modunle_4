package com.codeGym.service.borrow;

import com.codeGym.model.BorrowCard;
import com.codeGym.repository.IBorrowCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BorrowCartService implements IBorrowCartService {
    @Autowired
    IBorrowCardRepository repository;



    @Override
    public Page<BorrowCard> findAll() {
        return null;
    }

    @Override
    public Optional<BorrowCard> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(BorrowCard borrowCard) {
        repository.save(borrowCard);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<BorrowCard> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
