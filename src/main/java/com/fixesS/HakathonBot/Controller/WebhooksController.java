package com.fixesS.HakathonBot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("f")
public class WebhooksController {

    @GetMapping
    public String WebhookReceived(){
        System.out.println("Webhook received !");

        return "hey";
    }
}
