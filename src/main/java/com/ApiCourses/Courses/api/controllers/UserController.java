package com.ApiCourses.Courses.api.controllers;

import com.ApiCourses.Courses.api.dto.request.used_request.UserRequest;
import com.ApiCourses.Courses.api.dto.response.used_responses.UserResponse;
import com.ApiCourses.Courses.infrastructure.abstract_services.IUserService;
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
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "Usuario", description = "Operaciones relacionadas con los usuarios")
public class UserController {

    private final IUserService iUserService;

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Crea un nuevo usuario con los datos proporcionados",
            tags = {"Gestión"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la creación del usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Usuario creado satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(this.iUserService.create(userRequest));
    }

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Obtiene un usuario específico por su ID",
            tags = {"Consulta"}
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.iUserService.get(id));
    }

    @Operation(
            summary = "Eliminar usuario por ID",
            description = "Elimina un usuario específico por su ID",
            tags = {"Eliminación"}
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.iUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar usuario",
            description = "Actualiza un usuario existente por su ID con los nuevos datos proporcionados",
            tags = {"Actualización"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos requeridos para la actualización del usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )
            )
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,  @Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(this.iUserService.update(userRequest, id));
    }
}
