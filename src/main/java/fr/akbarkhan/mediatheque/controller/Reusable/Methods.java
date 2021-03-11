package fr.akbarkhan.mediatheque.controller.Reusable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class Methods {

    public Integer getUserIdFromToken(Principal principal) {
        return Integer.parseInt(principal.getName());
    }

    public ResponseEntity<?> getResponseEntity(boolean b) {
        return b ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
