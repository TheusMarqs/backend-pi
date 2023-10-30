package fatec.pi.pi.dtos.team;

public record TeamResponse(
    long id,
    Integer course,
    Integer students,
    Integer semester,
    String period
){
    
}
