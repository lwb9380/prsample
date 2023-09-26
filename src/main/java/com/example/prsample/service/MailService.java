package com.example.prsample.service;

import com.example.prsample.dao.MailDao;
import com.example.prsample.dto.Dept;
import com.example.prsample.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailService {

    private final MailDao mailDao;

    @Autowired
    public MailService(MailDao mailDao) {
        this.mailDao = mailDao;
    }

    public List<MailDto> mailList(int empno) {
        List<MailDto> list = mailDao.mailList(empno);
        return list;
    }

    public List<MailDto> selectMailDetail(Long id) {
        List<MailDto> list = mailDao.selectMailDetail(id);
        return list;
    }

    public void sendMail(String content, LocalDateTime date, int empno, String sName, String title) {
        mailDao.sendMail(content, date, empno, sName, title);
    }

    public List<Dept> selectDeptList() {
        return mailDao.selectDeptList();
    }

    public List<Dept> selectEmpnoList(String deptname) {
        return mailDao.selectEmpnoList(deptname);
    }
}