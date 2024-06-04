package com.ApiCourses.Courses.api.controllers;

import com.ApiCourses.Courses.api.dto.request.used_request.CourseRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.CourseResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.ICourseService;
import com.ApiCourses.Courses.utils.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/course")
@AllArgsConstructor
@Tag(name = "Curso", description = "Operaciones relacionadas con los cursos")
public class CourseController {
    private final ICourseService courseService;

    @Operation(
            summary = "Obtener todos los cursos",
            description = "Obtiene todos los cursos disponibles paginados",
            tags = {"Consulta"}
    )
    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestHeader(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(this.courseService.getAll(page - 1, size, sortType));
    }

    @Operation(
            summary = "Obtener curso por ID",
            description = "Obtiene un curso específico por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.courseService.get(id));
    }

    @Operation(
            summary = "Eliminar curso por ID",
            description = "Elimina un curso específico por su ID",
            tags = {"Eliminación"}
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar curso por ID",
            description = "Actualiza un curso específico por su ID con los nuevos datos proporcionados",
            tags = {"Actualización"}
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable Long id,  @RequestBody CourseRequest courseRequest){
        return ResponseEntity.ok(this.courseService.update(courseRequest, id));
    }

    @Operation(
            summary = "Crear un nuevo curso",
            description = "Crea un nuevo curso con los datos proporcionados",
            tags = {"Gestión"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la creación del curso",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CourseRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Curso creado satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CourseResponse.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<CourseResponse> create(@Validated @RequestBody CourseRequest courseRequest){
        return ResponseEntity.ok(this.courseService.create(courseRequest));
    }
}
