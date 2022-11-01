package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.DTO.MessageDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Message;
import com.csse.backend.RetrieveDeliveredItems.Respository.MessageRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.MessageService;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Save messages
     */
    @Override
    public boolean AddMessage(MessageDTO messageDTO) {
        try {
            Message message = new Message();
            message.setMessage(messageDTO.getMessage());
            message.setSender(messageDTO.getSender());
            message.setReceiver(messageDTO.getReceiver());
            message.setOrderReference(messageDTO.getOrderReference());

            Message message1 = messageRepository.addMessage(message);
            if (message1.getId() == null) {
                throw new NullPointerException();
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
