package com.picpaychallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaychallenge.dto.NotifyDto;
import com.picpaychallenge.model.User;

@Service
public class NotifyService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception{
        NotifyDto notificationRequest = new NotifyDto(user.getEmail(), message);
       ResponseEntity<String> notifyResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify)",
        notificationRequest, String.class);

        if(!(notifyResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("ERRO NA NOTIFICAÇÃO");
            throw new Exception("Seviço fora.");
        }
    }
}
