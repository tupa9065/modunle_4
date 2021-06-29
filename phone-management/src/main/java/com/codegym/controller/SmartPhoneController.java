package com.codegym.controller;

import com.codegym.Service.ISmartPhoneService;
import com.codegym.model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/smartphones")
public class SmartPhoneController {
    @Autowired
    private ISmartPhoneService smartPhoneService;

    @GetMapping("/list")
    public ModelAndView getAllSmartPhonePage() {
        ModelAndView modelAndView = new ModelAndView("/smartphones/list");
        modelAndView.addObject("smartPhones",smartPhoneService.findAll());
        return modelAndView;
    }
    @GetMapping
    public ResponseEntity<Iterable<SmartPhone>> findAll(){
        return new ResponseEntity<>(smartPhoneService.findAll(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SmartPhone>  createSmartPhone(@RequestBody SmartPhone smartPhone){
        return new ResponseEntity<>(smartPhoneService.save(smartPhone), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SmartPhone> deleteSmartphone(@PathVariable Long id) {
        Optional<SmartPhone> smartphoneOptional = smartPhoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.delete(smartphoneOptional.get());
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartPhone> getInfoSmartPhone(@PathVariable Long id){
        Optional<SmartPhone> smartphoneOptional = smartPhoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.save(smartphoneOptional.get());
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SmartPhone> editSmartPhone(@PathVariable Long id,@RequestBody SmartPhone smartPhoneEdit){
        Optional<SmartPhone> smartphoneOptional = smartPhoneService.findById(smartPhoneEdit.getId());
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.save(smartPhoneEdit);
        return new ResponseEntity<>(smartPhoneEdit, HttpStatus.OK);
    }
}
