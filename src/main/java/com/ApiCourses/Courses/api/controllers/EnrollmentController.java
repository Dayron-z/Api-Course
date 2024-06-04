package com.ApiCourses.Courses.api.controllers;

import com.ApiCourses.Courses.api.dto.request.used_request.EnrollmentRequest;
import com.ApiCourses.Courses.api.dto.response.custom_responses.EnrollmentBasicResponse;
import com.ApiCourses.Courses.api.dto.response.custom_responses.EnrollmentBasicResponseToUser;
import com.ApiCourses.Courses.api.dto.response.used_responses.EnrollmentResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.IEnrollmentService;
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
@RequestMapping("/enrollment")
@Tag(name = "Matrícula", description = "Operaciones relacionadas con las matrículas")
@AllArgsConstructor
public class EnrollmentController {
    private final IEnrollmentService enrollmentService;

    @Operation(
            summary = "Crear una nueva matrícula",
            description = "Crea una nueva matrícula con los datos proporcionados",
            tags = {"Gestión"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la creación de la matrícula",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnrollmentRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Matrícula creada satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnrollmentResponse.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@Validated @RequestBody EnrollmentRequest enrollmentRequest){
        return ResponseEntity.ok(this.enrollmentService.create(enrollmentRequest));
    }

    @Operation(
            summary = "Obtener matrícula por ID",
            description = "Obtiene una matrícula específica por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.enrollmentService.get(id));
    }

    @Operation(
            summary = "Eliminar matrícula por ID",
            description = "Elimina una matrícula específica por su ID",
            tags = {"Eliminación"}
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Obtener cursos por ID de usuario",
            description = "Obtiene una lista de cursos asociados a un usuario específico por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/users/{user_id}/courses")
    public ResponseEntity<List<EnrollmentBasicResponseToUser>> findCoursesByUser(@PathVariable("user_id") Long id){
        return ResponseEntity.ok(this.enrollmentService.findByUserId(id));
    }

    @Operation(
            summary = "Obtener usuarios por ID de curso",
            description = "Obtiene una lista de usuarios asociados a un curso específico por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/courses/{course_id}/users")
    public ResponseEntity<List<EnrollmentBasicResponse>> findByCourseId(@PathVariable("course_id") Long id){
        return ResponseEntity.ok(this.enrollmentService.findByCourseId(id));
    }
}
