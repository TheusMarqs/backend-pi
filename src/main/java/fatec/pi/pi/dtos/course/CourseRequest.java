package fatec.pi.pi.dtos.course;

import jakarta.validation.constraints.NotBlank;

public record CourseRequest( 
    @NotBlank(message = "Nome não pode ser em branco")
    String name,
    
    @NotBlank(message = "Carga horária não pode ser em branco")
    String workload,

    @NotBlank(message = "Duração não pode ser em branco")
    String duration
    )
{}
