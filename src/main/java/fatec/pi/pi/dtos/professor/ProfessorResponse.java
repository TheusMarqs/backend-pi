package fatec.pi.pi.dtos.professor;

public record ProfessorResponse(
    long id,
    String name,
    String email,
    String password,
    String education,
    Boolean condition
)  {
    
}