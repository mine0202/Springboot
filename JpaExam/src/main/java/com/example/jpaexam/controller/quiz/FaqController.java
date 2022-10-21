package com.example.jpaexam.controller.quiz;

import com.example.jpaexam.model.Faq;
import com.example.jpaexam.service.quiz.FaqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/quiz")
public class FaqController {

    @Autowired
    FaqService faqService;

    @GetMapping("/faq")
    public ResponseEntity<Object> getFaqAll(){
        try{
            List<Faq> list = faqService.findAll();
            if(list.isEmpty()==false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/faq/{no}")
    public ResponseEntity<Object> getById(@PathVariable int no){
        try{
            Optional<Faq> optionalFaq = faqService.findByid(no);
            if ( optionalFaq.isPresent()){
                return new ResponseEntity<>(optionalFaq.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/faq")
    public ResponseEntity<Object> createFaq(@RequestBody Faq faq){
        try{
            Faq faq1 = faqService.save(faq);
            return new ResponseEntity<>(faq1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/faq/edit/{no}")
    public ResponseEntity<Object> updateFaq(@PathVariable int no, @RequestBody Faq faq){
        try{
            Faq faq1 = faqService.save(faq);
            return new ResponseEntity<>(faq1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/faq/delete/{no}")
    public ResponseEntity<Object> deleteFaq(@PathVariable int no){
        try{
            boolean fSuccess = faqService.removeByid(no);
            if(fSuccess){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
