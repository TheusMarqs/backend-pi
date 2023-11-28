package fatec.pi.pi.dtos.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequest (
    @NotBlank(message = "Nome não pode ser em branco")
    String name,
    
    @Email(message = "Email não pode ser em branco")
    String email,

    @NotBlank(message = "Senha não pode ser em branco")
    String password,

    @NotBlank(message = "Formação não pode ser em branco")
    String education,

    Boolean status
) {
}
