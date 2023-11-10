package fatec.pi.pi.dtos.team;

import java.util.List;

public record TeamResponse(
    long id,
    Integer course,
    Integer students,
    Integer semester,
    String period,
    List<Integer> time
){
    
}
