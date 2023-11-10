package fatec.pi.pi.dtos.schedule;


public record ScheduleResponse(
    long id,
    String day,
    Integer time,
    Integer professor,
    Integer classroom,
    Integer discipline,
    Integer team
){
    
}
