package com.himanshu.mcp_server;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

@SpringBootApplication
public class McpServerApplication {

	@Bean
	public ToolCallbackProvider dateTool() {
		return MethodToolCallbackProvider.builder().toolObjects(new UtilTools()).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(McpServerApplication.class, args);
	}

}


class UtilTools {

	@Tool(description = "This method provide current date and time as per user's timezone.")
	String getCurrentDateTime() {
		return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
	}
}