package fatec.pi.pi.dtos.course;

public record CourseResponse(
    long id,
    String name,
    Integer workload,
    Integer duration
){
    
}
