package com.project.BookMyShow.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatTypeConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SeatTypeId;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private int seatCount;

    private double price;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    public SeatTypeConfiguration(SeatType seatType, int seatCount, double price, Theatre theatre) {
        this.seatType = seatType;
        this.seatCount = seatCount;
        this.price = price;
        this.theatre = theatre;
    }
}