package com.example.prsample.dao;

import com.example.prsample.dto.Dept;
import com.example.prsample.dto.MailDto;
import com.example.prsample.entity.Mail;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MailDao {

    public List<MailDto> mailList(int empno);

    public List<MailDto> selectMailDetail(Long id);

    public void sendMail(String content, LocalDateTime date, int empno, String sName, String title);

    public List<Dept> selectDeptList();

    public List<Dept> selectEmpnoList(String deptname);

}
