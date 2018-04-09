package com.example.demo.repository;

import com.example.demo.domain.entity.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat, Long> {


    @Query(value = "SELECT * FROM reservation_seat \n" +
            "WHERE reservation_id = :id", nativeQuery = true)
    List<ReservationSeat> findByReservationId(
            @Param("id") Long id
    );

    //nadji sva zauzeta
    @Query(value = "SELECT * FROM reservation_seat \n" +
            "WHERE reservation_id IN :ids", nativeQuery = true)
    List<ReservationSeat> findReserved(
            @Param("ids") List<Long> ids
    );

    //vidi jel postoji u bazi mjesto
    @Query(value = "SELECT * FROM reservation_seat \n" +
            "WHERE reservation_id IN :ids AND row = :row AND col = :col", nativeQuery = true)
    ReservationSeat findByReservationAndSeat(
            @Param("ids") List<Long> ids,
            @Param("row") int row,
            @Param("col") int col
    );
}
