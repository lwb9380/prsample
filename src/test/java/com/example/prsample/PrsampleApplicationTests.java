package com.example.prsample;

import com.example.prsample.entity.Mail;
import com.example.prsample.repository.MailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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
}
