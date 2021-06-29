package com.codegym.Service;

import com.codegym.Repository.ISmartPhoneRepository;
import com.codegym.model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartPhoneService implements ISmartPhoneService{
    @Autowired
    private ISmartPhoneRepository smartPhoneRepository;
    @Override
    public Iterable<SmartPhone> findAll() {
        return smartPhoneRepository.findAll();
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return smartPhoneRepository.save(smartPhone);
    }

    @Override
    public Optional<SmartPhone> findById(Long id) {
        return smartPhoneRepository.findById(id);
    }

    @Override
    public void delete(SmartPhone smartPhone) {
        smartPhoneRepository.delete(smartPhone);
    }
}
