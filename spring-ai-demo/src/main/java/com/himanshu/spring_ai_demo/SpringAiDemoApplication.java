package com.himanshu.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringAiDemoApplication implements CommandLineRunner {

    @Autowired
    VectorStore vectorStore;

    @Autowired
    ChatModel chatModel;

	public static void main(String[] args) {
		SpringApplication.run(SpringAiDemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Spring AI Demo Application Started...");
//        loadPdfInVectorStore();
//        searchInVectorStore("Kotlin");
        chatWithModel("Kotlin prerequisites");
        System.exit(0);
    }

    private void loadPdfInVectorStore() {
        PagePdfDocumentReader pdfReader =
                new PagePdfDocumentReader("classpath:/spring-framework.pdf", PdfDocumentReaderConfig.builder().build());
        TokenTextSplitter splitter = TokenTextSplitter.builder().build();
        vectorStore.add(splitter.apply(pdfReader.read()));
    }

    private void searchInVectorStore(String userQuery) {
        List<Document> results = this.vectorStore.similaritySearch(SearchRequest.builder().query(userQuery).topK(1).build());
        results.forEach(doc -> System.out.println(doc.getFormattedContent()));
    }

    private void chatWithModel(String userQuery) {
        ChatClient chatClient = ChatClient.builder(chatModel).build();
        ChatResponse chatResponse = chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(userQuery).call().chatResponse();
        if(null != chatResponse) {
            System.out.println("Chat Response: " + chatResponse.getResult().getOutput().getText());
        }

    }

}
