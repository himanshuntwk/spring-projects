package com.example.application.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIService {

  private final VectorStore vectorStore;
  private final ChatClient chatClient;

  public AIService(VectorStore vectorStore, ChatClient chatClient) {
    this.vectorStore = vectorStore;
    this.chatClient = chatClient;
  }

  public void addData(String data) {
    String[] statements = data.split("\\.");
    List<Document> documentList = new ArrayList<>();
    for (String statement : statements) {
      if (statement.isEmpty())
        continue;
      documentList.add(new Document(statement.trim()));
    }
    vectorStore.add(documentList);
  }

  public String searchData(String searchQuery) {
    StringBuilder result = new StringBuilder();
    List<Document> documents = vectorStore.similaritySearch(SearchRequest.query(searchQuery).withTopK(3));
    documents.forEach(document -> result.append(document.getContent() + "\n"));
    return result.toString();
  }

  public String summarizeContent(String content) {
    String genResponse = chatClient.call("Summarize below content \n Content:\n" + content);
    return genResponse;
  }

}
