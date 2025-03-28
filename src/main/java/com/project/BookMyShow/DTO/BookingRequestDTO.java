package com.project.BookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {

    private Long showId;

    private List<Long> seatIds;

}
