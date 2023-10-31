package fatec.pi.pi.dtos.discipline;

import jakarta.validation.constraints.NotBlank;

public record DisciplineRequest( 
    @NotBlank(message = "Curso não pode ser em branco")
    String course,
    
    @NotBlank(message = "Nome não pode ser em branco")
    String name,

    @NotBlank(message = "Carga horária não pode ser em branco")
    String workload

    )
{}
