package artifixal.easypharmacy.controllers.advices.handlers;

import artifixal.easypharmacy.dtos.errors.ErrorMessageDTO;
import artifixal.easypharmacy.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author ArtiFixal
 */
@RestControllerAdvice
public class EntityNotFoundExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value={EntityNotFoundException.class})
    protected Mono<ResponseEntity<Object>> handleException(EntityNotFoundException ex,ServerWebExchange request){
        return handleExceptionInternal(ex,new ErrorMessageDTO("error.entity.notFound"),new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }
}
