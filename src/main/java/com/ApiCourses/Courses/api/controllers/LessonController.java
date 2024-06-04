package com.ApiCourses.Courses.api.controllers;

import com.ApiCourses.Courses.api.dto.request.used_request.LessonRequest;
import com.ApiCourses.Courses.api.dto.request.custom_request.UpdateLessonRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.CourseResponseToLessons;
import com.ApiCourses.Courses.api.dto.response.used_responses.LessonResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ILessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
@Tag(name = "Lección", description = "Operaciones relacionadas con las lecciones")
@AllArgsConstructor
public class LessonController {
    private final ILessonService iLessonService;

    @Operation(
            summary = "Crear una nueva lección",
            description = "Crea una nueva lección con los datos proporcionados",
            tags = {"Gestión"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la creación de la lección",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LessonRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Lección creada satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LessonResponse.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<LessonResponse> create(@Validated @RequestBody LessonRequest lessonRequest){
        return ResponseEntity.ok(this.iLessonService.create(lessonRequest));
    }

    @Operation(
            summary = "Obtener una lección por ID",
            description = "Obtiene una lección específica por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.iLessonService.get(id));
    }

    @Operation(
            summary = "Eliminar una lección por ID",
            description = "Elimina una lección específica por su ID",
            tags = {"Eliminación"}
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.iLessonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar una lección",
            description = "Actualiza una lección existente por su ID con los nuevos datos proporcionados",
            tags = {"Actualización"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la actualización de la lección",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateLessonRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Lección actualizada satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LessonResponse.class)
                    )
            )
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(@PathVariable Long id, @Validated @RequestBody UpdateLessonRequest lessonRequest){
        return ResponseEntity.ok(this.iLessonService.customUpdate(lessonRequest, id));
    }

    @Operation(
            summary = "Obtener temas de una lección",
            description = "Obtiene una lista de temas asociados a una lección específica por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/courses/{course_id}")
    public ResponseEntity<CourseResponseToLessons> findByCourse(@PathVariable("course_id") Long id) {
        return ResponseEntity.ok(this.iLessonService.findByCourse(id));
    }
}
