package com.videomeeting.backend.registry;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoomRegistry {

    // ROOM -> USERS
    private final Map<String, Set<String>>
            rooms = new ConcurrentHashMap<>();

    // ADD USER
    public void addUser(

            String roomId,
            String userId
    ) {

        rooms.computeIfAbsent(

                roomId,

                k -> ConcurrentHashMap.newKeySet()
        );

        rooms.get(roomId)
                .add(userId);
    }

    // REMOVE USER
    public void removeUser(

            String roomId,
            String userId
    ) {

        if (rooms.containsKey(roomId)) {

            rooms.get(roomId)
                    .remove(userId);

            // REMOVE EMPTY ROOM
            if (rooms.get(roomId).isEmpty()) {

                rooms.remove(roomId);
            }
        }
    }

    // GET USERS
    public Set<String> getUsers(
            String roomId
    ) {

        return rooms.getOrDefault(

                roomId,

                new HashSet<>()
        );
    }
}