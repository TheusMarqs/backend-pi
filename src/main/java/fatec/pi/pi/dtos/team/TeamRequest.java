package fatec.pi.pi.dtos.team;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record TeamRequest( 
    @NotBlank(message = "Curso não pode ser em branco")
    String course,
    
    @NotBlank(message = "Quantia de alunos não pode ser em branco")
    String students,

    @NotBlank(message = "Semestre não pode ser em branco")
    String semester,

    @NotBlank(message = "Período não pode ser em branco")
    String period,

    List<Integer> time
    )
{}
