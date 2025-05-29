package com.bigw.messageeater.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;


@Slf4j
@RestController
public class MessageEaterController {

    @PostMapping("/message-capture")
    public void eatMessage(@RequestBody String message, @RequestHeader Map<String, String> headers) {
        log.info("Eating message: {}", message);
        log.info("Eating header: {}", headers);

        Path filePath = Path.of("message.log");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(message);
            writer.newLine();
            log.info("Message saved to file: {}", filePath.toAbsolutePath());
        } catch (IOException e) {
            log.error("Failed to save message to file", e);
        }
    }
}
