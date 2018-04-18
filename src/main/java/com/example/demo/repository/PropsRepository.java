package com.example.demo.repository;

import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PropsRepository extends JpaRepository<Props,Long>{
    @Query("select p from Props p where approved = true and p.cinemaId = :cinemaId and p.expirationDate > :currentDate and p.sold=false")
    public List<Props> serchApproved(@Param("cinemaId") Long cinemaId,@Param("currentDate") Date currentDate);

    @Query("select p from Props p where approved = false and p.cinemaId = :cinemaId")
    public List<Props> serchUnApproved(@Param("cinemaId") Long cinemaId);

}
