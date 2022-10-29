package com.csse.backend.RetrieveDeliveredItems.Services.Abstract;

import com.csse.backend.RetrieveDeliveredItems.DTO.MessageDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Message;

import java.util.List;

public interface MessageService {

    boolean AddMessage(MessageDTO messageDTO);

}
