package com.example.jpaexam.service.quiz;

import com.example.jpaexam.model.Faq;
import com.example.jpaexam.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {

    @Autowired
    FaqRepository faqRepository;

    public List<Faq> findAll(){
        List<Faq> list = faqRepository.findAll();
        return list;
    }

    public Optional<Faq> findByid(int no){
        Optional<Faq> optionalFaq = faqRepository.findById(no);
        return optionalFaq;
    }

    public Faq save(Faq faq){
        Faq faq1 = faqRepository.save(faq);
        return faq1;
    }

    public boolean removeByid(int no){
        if( faqRepository.existsById(no)){
            faqRepository.deleteById(no);
            return true;
        }
        else{
            return false;
        }
    }
}
