package com.example.demo.repository;

import com.example.demo.domain.entity.Props;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropsRepository extends JpaRepository<Props,Long>{
    @Query("select p from Props p where approved = true")
    public List<Props> serchApproved();

    @Query("select p from Props p where approved = false")
    public List<Props> serchUnApproved();
}
