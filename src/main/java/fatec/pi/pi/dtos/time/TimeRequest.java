package fatec.pi.pi.dtos.time;

import jakarta.validation.constraints.NotBlank;

public record TimeRequest( 
    @NotBlank(message = "Horário não pode ser em branco")
    String time
    )
{}
