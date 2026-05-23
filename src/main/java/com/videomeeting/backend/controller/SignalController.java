package com.videomeeting.backend.controller;

import com.videomeeting.backend.registry.RoomRegistry;

import com.videomeeting.backend.signaling.SignalMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.Payload;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class SignalController {

    @Autowired
    private SimpMessagingTemplate
            messagingTemplate;

    @Autowired
    private RoomRegistry roomRegistry;

    // ROOM BASED MESSAGE
    @MessageMapping("/message")
    public void receiveMessage(

            @Payload SignalMessage signalMessage
    ) {

        System.out.println(
                "Received Message: "
                        + signalMessage
        );

        String roomId =
                signalMessage.getRoomId();

        String sender =
                signalMessage.getSender();

        // JOIN
        if ("JOIN".equals(
                signalMessage.getType()
        )) {

            roomRegistry.addUser(
                    roomId,
                    sender
            );

            // SEND PARTICIPANT LIST
            Set<String> users =
                    roomRegistry.getUsers(
                            roomId
                    );

            messagingTemplate.convertAndSend(

                    "/topic/participants/" +
                            roomId,

                    users
            );
        }

        // LEAVE
        if ("LEAVE".equals(
                signalMessage.getType()
        )) {

            roomRegistry.removeUser(
                    roomId,
                    sender
            );

            // SEND UPDATED LIST
            Set<String> users =
                    roomRegistry.getUsers(
                            roomId
                    );

            messagingTemplate.convertAndSend(

                    "/topic/participants/" +
                            roomId,

                    users
            );
        }

        // SEND NORMAL MESSAGE
        messagingTemplate.convertAndSend(

                "/topic/room/" +
                        roomId,

                signalMessage
        );
    }
}