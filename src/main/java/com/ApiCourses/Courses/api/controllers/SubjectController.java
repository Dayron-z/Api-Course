package com.ApiCourses.Courses.api.controllers;

import com.ApiCourses.Courses.api.dto.request.custom_request.UpdateSubjectRequest;
import com.ApiCourses.Courses.api.dto.request.used_request.SubjectRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.api.dto.response.used_responses.SubjectResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ISubjectService;
import com.ApiCourses.Courses.utils.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/subject")
@Tag(name = "Temas", description = "Operaciones relacionadas con los temas")
@AllArgsConstructor
public class SubjectController {
    @Autowired
    private final ISubjectService iSubjectService;

    @Operation(
            summary = "Obtener todos los temas",
            description = "Obtiene una lista paginada de todos los temas disponibles",
            tags = {"Consulta"}
    )
    @GetMapping
    public ResponseEntity<Page<SubjectResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestHeader(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(this.iSubjectService.getAll(page - 1, size, sortType));
    }

    @Operation(
            summary = "Obtener temas por ID de lección",
            description = "Obtiene una lista de temas asociados a una lección específica por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/lessons/{lesson_id}")
    public ResponseEntity<List<SubjectResponse>> get(@PathVariable("lesson_id") Long id){
        return ResponseEntity.ok(this.iSubjectService.findBylessonId(id));
    }

    @Operation(
            summary = "Crear un nuevo tema",
            description = "Crea un nuevo tema con los datos proporcionados",
            tags = {"Gestión"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la creación del tema",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubjectRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Tema creado satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubjectResponse.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<SubjectResponse> create(@Validated @RequestBody SubjectRequest subjectRequest){
        return ResponseEntity.ok(this.iSubjectService.create(subjectRequest));
    }

    @Operation(
            summary = "Eliminar un tema por ID",
            description = "Elimina un tema específico por su ID",
            tags = {"Eliminación"}
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.iSubjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar un tema",
            description = "Actualiza un tema existente por su ID con los nuevos datos proporcionados",
            tags = {"Actualización"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la actualización del tema",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateSubjectRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Tema actualizado satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubjectResponse.class)
                    )
            )
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<SubjectResponse> update(@PathVariable Long id,  @Validated @RequestBody UpdateSubjectRequest subjectRequest){
        return ResponseEntity.ok(this.iSubjectService.customUpdate(subjectRequest, id));
    }
}
