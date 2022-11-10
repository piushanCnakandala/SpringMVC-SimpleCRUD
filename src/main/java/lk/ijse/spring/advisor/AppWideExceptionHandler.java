package lk.ijse.spring.advisor;

import lk.ijse.spring.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {

    @ExceptionHandler({Exception.class})


    //status code is 200- wede haraiyta una kiyala error awtah pennwa
   /* public ResponseUtil exceptionHandler(Exception e){
        return new ResponseUtil(500,e.getMessage(),null);

    }*/

    // //status code is 500
   /* public ResponseEntity exceptionHandler(Exception e){
        return new ResponseEntity(new ResponseUtil(500,e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);

    }*/

 //or use it for easy

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil exceptionHandler(Exception e){
        return new ResponseUtil(500,e.getMessage(),null);

    }
}
