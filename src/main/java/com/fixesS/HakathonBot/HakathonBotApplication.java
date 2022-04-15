package com.fixesS.HakathonBot;

import com.fixesS.HakathonBot.Config.BotConfig;
import com.fixesS.HakathonBot.Controller.WebhooksController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@Import(BotConfig.class)
@ComponentScan( basePackages = "com.fixesS.HakathonBot.Controller")
public class HakathonBotApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HakathonBotApplication.class, args);


	}
}
