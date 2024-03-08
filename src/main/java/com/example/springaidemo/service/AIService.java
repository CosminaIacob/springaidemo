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
                Please provide me a book from category {category} and year {year}.
                Provide a summary of the book as well, in 1 sentence.
                If there is no book with the requested category, return a empty string.
                Provide the details of the book in the JSON format
                containing this information: title, author, category, year, review, summary.
                """);
        template.add("category", category);
        template.add("year", year);
        ChatResponse aiResponse = aiClient.call(template.create());
        return aiResponse.getResult().getOutput().getContent();
    }
}
