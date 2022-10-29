package com.csse.backend.RetrieveDeliveredItems.Controller;

import com.csse.backend.RetrieveDeliveredItems.DTO.MessageDTO;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/addMessage")
    public ResponseEntity<?> addMessage(@RequestBody MessageDTO messageDTO){
        Boolean status = messageService.AddMessage(messageDTO);
        if(status){
            return new ResponseEntity<>("Saved Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }

}
