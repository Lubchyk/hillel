package com.hillel.finalWork.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

@Service
public class EmailService {

    @Bean("templateMessageAboutReport")
    public SimpleMailMessage templateMessageAboutReport() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("report/Report.csv").getFile());
        String content = "";
        content = new String(Files.readAllBytes(file.toPath()));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("motruk.lubomyr88888@gmail.com");
        mailMessage.setSubject("Report Top 3");
        mailMessage.setText(content);
        mailMessage.setFrom("lubchyk1613@gmail.com");
        return mailMessage;
    }

    @Bean("templateMessageAboutError")
    public SimpleMailMessage templateMessageAboutError() throws IOException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("motruk.lubomyr88888@gmail.com");
        mailMessage.setSubject("Error Error Error");
        mailMessage.setText("Something about error");
        mailMessage.setFrom("lubchyk1613@gmail.com");
        return mailMessage;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost("smtp.gmail.com");
        javaMailSenderImpl.setPort(587);
        javaMailSenderImpl.setProtocol("smtp");
        javaMailSenderImpl.setUsername("lubchyk1613@gmail.com");
        javaMailSenderImpl.setPassword("");
        Properties props = javaMailSenderImpl.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return javaMailSenderImpl;
    }


//    private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final ProductOrder order) {
//
//        MimeMessagePreparator preparator = new MimeMessagePreparator() {
//
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//                helper.setSubject("Your order on Demoapp with attachement");
//                helper.setFrom("lubchyk1613@gmail.com");
////                helper.setTo(order.getCustomerInfo().getEmail());
////                String content = "Dear " + order.getCustomerInfo().getName()
////                    + ", thank you for placing order. Your order id is " + order.getOrderId() + ".";
//
////                helper.setText(content);
//
//                // Add a resource as an attachment
//                helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
//
//            }
//        };
//        return preparator;
//    }

}
