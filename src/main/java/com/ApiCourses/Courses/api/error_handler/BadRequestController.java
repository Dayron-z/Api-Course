package com.ApiCourses.Courses.api.error_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ApiCourses.Courses.api.dto.errors.BaseErrorResp;
import com.ApiCourses.Courses.api.dto.errors.ErrorsResp;
import com.ApiCourses.Courses.utils.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
 * Controlador de errores para manejar solicitudes incorrectas
 */
@RestControllerAdvice
/*
 * Establece el código de estado HTTP BAD_REQUEST para todas las respuestas generadas por este controlador
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {

    /*
     * Maneja excepciones de validación de argumentos del método
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResp handleBadRequest(MethodArgumentNotValidException exception){

        // Lista para almacenar los errores
        List<Map<String,String>> errors = new ArrayList<>();

        /*
         * Recorre todos los errores de campo y los agrega a una lista de errores
         */
        exception.getBindingResult().getFieldErrors().forEach(e -> {
            // Mapa para almacenar detalles de un error
            Map<String,String> error = new HashMap<>();
            // Agrega el mensaje de error al mapa con la clave "error"
            error.put("error", e.getDefaultMessage());
            // Agrega el nombre del campo en donde ocurrió el error al mapa con la clave "field"
            error.put("field", e.getField());
            // Agrega el mapa de error a la lista de errores
            errors.add(error);
        });

        // Construye y devuelve una respuesta de error con los detalles recopilados
        return ErrorsResp.builder()
                .code(HttpStatus.BAD_REQUEST.value()) // Código de estado HTTP 400
                .status(HttpStatus.BAD_REQUEST.name()) // Nombre del estado HTTP BAD_REQUEST
                .errors(errors) // Lista de errores [{ "field": "mal", "error": "mal"}]
                .build();
    }


    /*
     * Maneja excepciones personalizadas de BadRequestException
     */
    @ExceptionHandler(BadRequestException.class)
    public BaseErrorResp handleError(BadRequestException exception){
        // Lista para almacenar los errores
        List<Map<String,String>> errors = new ArrayList<>();

        // Mapa para almacenar detalles de un error
        Map<String,String> error = new HashMap<>();

        // Agrega el mensaje de error personalizado al mapa con la clave "id"
        error.put("id", exception.getMessage());

        // Agrega el mapa de error a la lista de errores
        errors.add(error);

        // Construye y devuelve una respuesta de error con los detalles recopilados
        return ErrorsResp.builder()
                .code(HttpStatus.BAD_REQUEST.value()) // Código de estado HTTP 400
                .status(HttpStatus.BAD_REQUEST.name()) // Nombre del estado HTTP BAD_REQUEST
                .errors(errors) // Lista de errores [{ "field": "mal", "error": "mal"}]
                .build();

    }
}
