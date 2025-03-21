package be.machigan.renova.handler;

import be.machigan.renova.controller.PictureController;
import be.machigan.renova.controller.PromoController;
import be.machigan.renova.controller.UserController;
import be.machigan.renova.exception.InvalidFileTypeException;
import be.machigan.renova.exception.NameAlreadyInUseException;
import be.machigan.renova.exception.OperationNotPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(assignableTypes = {UserController.class, PictureController.class, PromoController.class})
public class EntityControllerHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException() {
        return new ResponseEntity<>("The entity hasn't been found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameAlreadyInUseException.class)
    public ResponseEntity<String> handleNameAlreadyUsedException() {
        return new ResponseEntity<>("This name is already in use", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidFileTypeException.class)
    public ResponseEntity<String> handleInvalidFileTypeException(InvalidFileTypeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<String> handleOperationNotPermittedException(OperationNotPermittedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleBadArgumentException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                e
                        .getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList(),
                HttpStatus.BAD_REQUEST
        );
    }
}
