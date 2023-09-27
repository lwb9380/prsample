package com.example.prsample;

import com.example.prsample.entity.Mail;
import com.example.prsample.repository.MailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class PrsampleApplicationTests {

    @Autowired
    MailRepository mailRepository;

    @Test
    void contextLoads() {
    }

    @Test   // 삽입 기능
    public void testInsertDummies() {
        IntStream.rangeClosed(1,2).forEach(i -> {
            Mail mail = Mail.builder()
                    .sName("sender..."+i)
                    .title("title...."+i)
                    .content("content..."+i)
                    .date(LocalDateTime.now())
                    .empno(39)
                    .build();
            mailRepository.save(mail);
        });
    }

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0,10);

        Page<Mail> result = mailRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("------------------------------------");

        System.out.println("Total Pages: " + result.getTotalPages());

        System.out.println("Total Count: " + result.getTotalElements());

        System.out.println("Page Number: " + result.getNumber());

        System.out.println("Page Size: " + result.getSize());

        System.out.println("has next page?: " + result.hasNext());

        System.out.println("first page?: " + result.isFirst());

        System.out.println("-------------------------------------");

        for (Mail mail : result.getContent()) {
            System.out.println(mail);
        }
    }

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(0, 10, sort1);

        Page<Mail> result = mailRepository.findAll(pageable);

        result.get().forEach(mail -> {
            System.out.println(mail);
        });
    }
}
