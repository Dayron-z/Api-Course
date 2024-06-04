package com.ApiCourses.Courses.api.controllers;

import com.ApiCourses.Courses.api.dto.request.custom_request.UpdatedSubmissionRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubmissionRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubmissionResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ISubmissionService;
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
@RequestMapping("/submission")
@Tag(name = "Entrega", description = "Operaciones relacionadas con las entregas")
public class SubmissionController {

    private final ISubmissionService submissionService;

    @Operation(
            summary = "Crear una nueva entrega",
            description = "Crea una nueva entrega con los datos proporcionados",
            tags = {"Gestión"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la creación de la entrega",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubmissionRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Entrega creada satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubmissionResponse.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<SubmissionResponse> create(@Validated @RequestBody SubmissionRequest submissionRequest){
        return ResponseEntity.ok(this.submissionService.create(submissionRequest));
    }

    @Operation(
            summary = "Obtener entrega por ID",
            description = "Obtiene una entrega específica por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<SubmissionResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.submissionService.get(id));
    }

    @Operation(
            summary = "Eliminar entrega por ID",
            description = "Elimina una entrega específica por su ID",
            tags = {"Eliminación"}
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.submissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar entrega",
            description = "Actualiza una entrega existente por su ID con los nuevos datos proporcionados",
            tags = {"Actualización"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la actualización de la entrega",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdatedSubmissionRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Entrega actualizada satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubmissionResponse.class)
                    )
            )
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<SubmissionResponse> update(@PathVariable Long id, @Validated @RequestBody UpdatedSubmissionRequest submissionRequest){
        return ResponseEntity.ok(this.submissionService.customUpdate(submissionRequest, id));
    }

    @Operation(
            summary = "Obtener entregas por ID de tema",
            description = "Obtiene una lista de entregas asociadas a un tema específico por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/subject/{subject_id}")
    public ResponseEntity<List<SubmissionResponse>> getBySubject(@PathVariable("subject_id") Long id){
        return ResponseEntity.ok(this.submissionService.findBySubjectId(id));
    }

    @Operation(
            summary = "Obtener entregas por ID de usuario",
            description = "Obtiene una lista de entregas asociadas a un usuario específico por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/users/{user_id}")
    public ResponseEntity<List<SubmissionResponse>> getByUser(@PathVariable("user_id") Long id){
        return ResponseEntity.ok(this.submissionService.findByUserId(id));
    }
}
