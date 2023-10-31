package fatec.pi.pi.dtos.discipline;

public record DisciplineResponse(
    long id,
    Integer course,
    String name,
    Integer workload
) {
    
}
