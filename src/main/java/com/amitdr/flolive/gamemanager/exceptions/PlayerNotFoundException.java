package com.amitdr.flolive.gamemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException() {
        super("Player not found...");
    }
}
