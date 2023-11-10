package fatec.pi.pi.dtos.schedule;


import jakarta.validation.constraints.NotBlank;

public record ScheduleRequest( 
    @NotBlank(message = "Dia não pode ser em branco")
    String day,
    
    @NotBlank(message = "Horário não pode ser em branco")
    String time,

    @NotBlank(message = "Professor não pode ser em branco")
    String professor,

    @NotBlank(message = "Sala não pode ser em branco")
    String classroom,

    @NotBlank(message = "Disciplina não pode ser em branco")
    String discipline,

    @NotBlank(message = "Turma não pode ser em branco")
    String team
    )
{}
