package com.project.BookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO{

    private Long showId;
 
    private Long movieId;

    private Long theatreId;

    private LocalDateTime showTime;

}
