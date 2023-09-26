package com.example.prsample.controller;

import com.example.prsample.dto.Dept;
import com.example.prsample.dto.MailDto;
import com.example.prsample.entity.Mail;
import com.example.prsample.repository.MailRepository;
import com.example.prsample.service.MailService;
import com.example.prsample.service.StuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
public class SampleController {

    @Autowired
    MailRepository mailRepository;

    @Autowired
    MailService mailService;

    @GetMapping("/mailList/{empno}")
    public String exView(@PathVariable int empno, Model model) {
        List<MailDto> mail = mailService.mailList(empno);
        model.addAttribute("mails", mail);
        return "mailList";
    }

    @GetMapping("/mailDetail/{id}")
    public String mailDetail(@PathVariable Long id, Model model) {
        List<MailDto> mail = mailService.selectMailDetail(id);
        model.addAttribute("mails", mail);
        return "mailDetail";
    }

    @GetMapping("/deptSendMail/{empno}")
    public String deptSendMail(@PathVariable int empno, Model model){
        model.addAttribute("empno", empno);
        return "deptSendMail";
    }

    @PostMapping("/deptSendMail")
    public String deptSendMail2(@RequestParam("content") String content,
                                @RequestParam("empno") int empno,
                                @RequestParam("title") String title,
                                Model model) {
        LocalDateTime date = LocalDateTime.now();
        String sName = "25";
        mailService.sendMail(content, date, empno, sName, title);
        return "sendSuccess";
    }


    @GetMapping("/sendMail")
    public String sendMail( Model model) {
        List<Dept> deptList = mailService.selectDeptList();
        model.addAttribute("deptList", deptList);
        return "sendMail";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("content") String content,
                           @RequestParam("title") String title,
                           Model model) {
        List<Dept> list = mailService.selectDeptList();
        LocalDateTime date = LocalDateTime.now();
        String sName = "30";
        model.addAttribute("deptList", list);
        mailService.sendMail(content, date, 30, sName, title);
        return "sendSuccess";
    }

    @GetMapping("getEmpnosByDept")
    @ResponseBody
    public List<Dept> getEmpnosByDept(@RequestParam("dept") String dept) {
        List<Dept> empnos = mailService.selectEmpnoList(dept);
        return empnos;
    }

}
