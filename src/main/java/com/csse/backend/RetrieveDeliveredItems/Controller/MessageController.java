package com.csse.backend.RetrieveDeliveredItems.Controller;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import com.csse.backend.RetrieveDeliveredItems.DTO.MessageDTO;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstants.MESSAGE_BASE_URL)
public class MessageController {
    final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * API for save a message
     */
    @PostMapping(CommonConstants.SAVE_MESSAGE)
    public ResponseEntity<?> addMessage(@RequestBody MessageDTO messageDTO) {
        boolean status = messageService.AddMessage(messageDTO);
        if (status) {
            return new ResponseEntity<>(CommonConstants.SAVED, HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

}
