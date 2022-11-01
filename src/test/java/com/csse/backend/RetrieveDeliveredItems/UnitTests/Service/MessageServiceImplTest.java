package com.csse.backend.RetrieveDeliveredItems.UnitTests.Service;

import com.csse.backend.RetrieveDeliveredItems.DTO.MessageDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Message;
import com.csse.backend.RetrieveDeliveredItems.Respository.MessageRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.MessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageServiceImplTest {

    @Autowired
    MessageServiceImpl messageService;
    @MockBean
    MessageRepository messageRepository;

    @Test
    @DisplayName("")
    void TestAddMessage(){
        MessageDTO message = new MessageDTO();
        message.setMessage("Test Message");
        message.setSender(1L);
        message.setReceiver(2L);
        message.setOrderReference(123L);

        boolean result = messageService.AddMessage(message);
        Assertions.assertEquals(true,result);
    }

}
