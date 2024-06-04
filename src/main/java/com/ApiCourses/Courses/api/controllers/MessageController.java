package com.ApiCourses.Courses.api.controllers;

import com.ApiCourses.Courses.api.dto.request.used_request.MessageRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.MessageResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.IMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
@Tag(name = "Mensaje", description = "Operaciones relacionadas con los mensajes")
public class MessageController {

    private final IMessageService iMessageService;

    @Operation(
            summary = "Crear un nuevo mensaje",
            description = "Crea un nuevo mensaje con los datos proporcionados",
            tags = {"Gestión"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la creación del mensaje",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MessageRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Mensaje creado satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<MessageResponse> create(@Validated @RequestBody MessageRequest messageRequest){
        return ResponseEntity.ok(this.iMessageService.create(messageRequest));
    }

    @Operation(
            summary = "Obtener mensajes por ID de curso",
            description = "Obtiene una lista de mensajes asociados a un curso específico por su ID",
            tags = {"Consulta"}
    )
    @GetMapping("/courses/{course_id}")
    public ResponseEntity<List<MessageResponse>> findByCourse(@PathVariable("course_id") Long id){
        return ResponseEntity.ok(this.iMessageService.findByCourseId(id));
    }

    @Operation(
            summary = "Obtener mensajes por ID de usuario emisor y receptor",
            description = "Obtiene una lista de mensajes asociados a un usuario emisor y receptor específicos",
            tags = {"Consulta"}
    )
    @GetMapping("/messages")
    public ResponseEntity<List<MessageResponse>> findByUsers(@RequestParam("sender_id") Long senderId, @RequestParam("receiver_id") Long receiverId){
        return ResponseEntity.ok(this.iMessageService.findBySenderIdAndReceiverId(senderId, receiverId));
    }
}
