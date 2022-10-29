package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.DTO.MessageDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Message;
import com.csse.backend.RetrieveDeliveredItems.Respository.MessageRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean AddMessage(MessageDTO messageDTO) {
        try {
            Message message = new Message();
            message.setMessage(messageDTO.getMessage());
            message.setSender(messageDTO.getSender());
            message.setReceiver(messageDTO.getReceiver());
            message.setOrderReference(messageDTO.getOrderReference());

            messageRepository.addMessage(message);

            return true;

        }catch (Exception e){
            return false;
        }
    }
}
