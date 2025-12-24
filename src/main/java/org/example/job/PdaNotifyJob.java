package org.example.job;

import org.example.model.Sku;
import org.example.service.HCService;
import org.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdaNotifyJob {

    @Autowired
    private HCService hcService;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 55 23 * * ?") // 时间
    public void sendUnreturnedPdaEmail() {
        List<Sku> list = hcService.getUnreturnedToday();
        if(!list.isEmpty()){
            mailService.sendUnreturnedPdaMail(list);
        }
    }
}
