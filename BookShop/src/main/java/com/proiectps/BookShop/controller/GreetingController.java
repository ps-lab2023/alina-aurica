package com.proiectps.BookShop.controller;

import com.proiectps.BookShop.model.ws.Greeting;
import com.proiectps.BookShop.model.ws.HelloMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class GreetingController {
    List<String> messages = new ArrayList<String>();
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        String str = message.getName() + '\n';
        messages.add(str);
        return new Greeting("Announcement: " + "\n" +
                                    HtmlUtils.htmlEscape(message.getName()));
    }

    @GetMapping("/messageInFrontend")
    public ResponseEntity getMessagesInFrontend(){
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }
}
