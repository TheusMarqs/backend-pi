package fatec.pi.pi.dtos.classroom;

import jakarta.validation.constraints.NotBlank;

public record ClassroomRequest( 
    @NotBlank(message = "Número não pode ser em branco")
    String number,
    
    @NotBlank(message = "Tipo não pode ser em branco")
    String type,

    @NotBlank(message = "Capacidade não pode ser em branco")
    String capacity
    )
{}
