package fatec.pi.pi.mappers;

import fatec.pi.pi.dtos.time.TimeRequest;
import fatec.pi.pi.dtos.time.TimeResponse;
import fatec.pi.pi.entities.Time;

public class TimeMapper {
    public static Time toEntity(TimeRequest request) {
        Time time = new Time();
        time.setTime(request.time());
        return time;
    }

    public static TimeResponse toDTO(Time time) {
        return new TimeResponse(
            time.getId(),
            time.getTime()
        );
    }
}
