package org.lifenet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.lifenet.domain.Message;
import org.lifenet.domain.View;
import org.lifenet.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepository messageRepo;
    private static int counter = 4;

    @Autowired
    public MessageController(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public List<Message> getList() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(View.FullMessage.class)
    public Message get(@PathVariable Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageRepo.save(message);
    }

    @PutMapping(value = "{id}")
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
    }

    @DeleteMapping("delete/{id}")
    public void deleteAll(@PathVariable("id") Message message) {
        messageRepo.deleteAll();
    }
}