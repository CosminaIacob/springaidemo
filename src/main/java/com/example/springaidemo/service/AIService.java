package com.example.springaidemo.service;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final OpenAiChatClient aiClient;

    public AIService(OpenAiChatClient aiClient) {
        this.aiClient = aiClient;
    }

    public String getJoke(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Please act as a funny person and create a joke on the given {topic}.
                Please be mindful and sensitive about the content though.
                """);
        promptTemplate.add("topic", topic);
        return aiClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

    public String getBooks(String category, String year) {
        PromptTemplate template = new PromptTemplate("""
                Please provide me the best book for the given {category} and the {year}.
                Please do provide a summary of the book as well, the information should be
                limited and not much in depth. If you don't find any book for the provided {category} don't change it and 
                return empty response.
                 Please provide the details in the JSOn format
                containing this information: title, author, category, year, review, summary.
                """);
        template.add("category", category);
        template.add("year", year);
        ChatResponse aiResponse = aiClient.call(template.create());
        return aiResponse.getResult().getOutput().getContent();
    }
}
