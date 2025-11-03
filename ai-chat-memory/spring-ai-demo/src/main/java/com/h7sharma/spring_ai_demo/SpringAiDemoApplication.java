package com.h7sharma.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiDemoApplication implements CommandLineRunner {

    private final ChatClient chatClient;

    public SpringAiDemoApplication(ChatClient.Builder chatClientBuilder, JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        ChatMemory chatMemory = MessageWindowChatMemory.builder().chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(4)
                .build();
        this.chatClient = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAiDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String conversationIdHimanshu = "abc_123";
        String conversationIdJohn = "xyz_789";
        chatClient.prompt().user("My name is Himanshu")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationIdHimanshu))
                .call().content();
        chatClient.prompt().user("My age is 40")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationIdHimanshu))
                .call().content();
        chatClient.prompt().user("I live in Mumbai")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationIdHimanshu))
                .call().content();


        chatClient.prompt().user("My name is John")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationIdJohn))
                .call().content();

        String response = chatClient.prompt().user("What is my name ?")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationIdHimanshu))
                .call().content();

        System.out.println(response);
        System.exit(0);
    }
}
