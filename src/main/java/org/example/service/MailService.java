package org.example.service;

import org.example.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendUnreturnedPdaMail(List<Sku> list) {
        if(list.isEmpty()) return;

        SimpleMailMessage message = new SimpleMailMessage();  //纯文本邮件对象
        message.setFrom("1716107159@qq.com");  // 发件人
        message.setTo(
                "3028129886@qq.com",
                "dxz520221@dingtalk.com",
                "3eh_c3dfllccl@dingtalk.com",
                "jiangpengxin0854@dingtalk.com"
        );
        message.setSubject("PDA 未归还提醒"); // 邮件主题

        StringBuilder content = new StringBuilder();
        content.append("以下 PDA 今日仍未归还：\n\n");

        int i = 1;
        for(Sku sku : list){
            content.append(i++)
                    .append(". PDA编号: ").append(sku.getPdaNo())
                    .append("，操作人: ").append(sku.getOperator())
                    .append("，取走时间: ").append(sku.getDateTime())
                    .append("\n");
        }

        message.setText(content.toString());

        mailSender.send(message);
    }
}
