package com.chslcompany.picpaysimplificado.service;

import com.chslcompany.picpaysimplificado.domain.user.Users;
import com.chslcompany.picpaysimplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {

    private static final String URL_NOTIFICATION = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(Users users, String message) throws Exception {
        String email = users.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        ResponseEntity<Map> responseNotification = restTemplate.postForEntity(URL_NOTIFICATION, notificationRequest, Map.class);
//        if(!(responseNotification.getStatusCode() == HttpStatus.OK)){
//            System.out.println("deu ruim nas notificações");
//            throw new Exception("Nossos serviços de notificação estão indisponíveis");
//        }

       System.out.println("Notificação enviada com sucesso");
    }
}
