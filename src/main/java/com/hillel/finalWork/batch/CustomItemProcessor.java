package com.hillel.finalWork.batch;

import com.hillel.finalWork.model.Transaction;
import com.hillel.finalWork.service.EmailService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomItemProcessor implements ItemProcessor<Transaction, Transaction> {

    @Autowired
    public EmailService emailService;

    public Transaction process(Transaction item) {
        System.out.println("Processing..." + item);
        return item;
    }
}