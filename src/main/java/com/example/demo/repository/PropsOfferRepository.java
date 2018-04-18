package com.example.demo.repository;

import com.example.demo.domain.entity.Props;
import com.example.demo.domain.entity.PropsOffer;
import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropsOfferRepository extends JpaRepository<PropsOffer,Long> {
    @Query("select p from PropsOffer p where p.user = :user and p.props = :props")
    public List<PropsOffer> serchIfOfferMade(@Param("user") User user, @Param("props") Props props);

    @Query("select p from PropsOffer p where p.props = :props")
    public List<PropsOffer> getOffersByProps(@Param("props") Props props);

    @Query("select p from PropsOffer p where p.props = :props and p.user <> :user")
    public List<PropsOffer> getNotAcceptedProps(@Param("props") Props props,@Param("user") User user);

    @Query("select p from PropsOffer p where p.user = :user")
    public List<PropsOffer> serchOffersByUser(@Param("user") User user);

}
