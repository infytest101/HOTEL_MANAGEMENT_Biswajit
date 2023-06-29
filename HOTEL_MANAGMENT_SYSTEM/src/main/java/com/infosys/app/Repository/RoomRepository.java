package com.infosys.app.Repository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.infosys.app.entity.Rooms;

@Repository
@EnableJpaRepositories
public interface RoomRepository extends JpaRepository<Rooms, Integer> {

	@Query("SELECT r FROM Rooms r INNER JOIN BookingDetails b ON  r.bookDetails.bookingDetailsId=b.bookingDetailsId where fromBookingDate BETWEEN :fromBookingDate AND :toBookingDate")
	//@Query("SELECT r FROM Rooms r")
	List<Rooms> findByFromDateFilter(Date fromBookingDate,Date toBookingDate);
	//@Query("SELECT r FROM Rooms where r.roomType.roomTypeId=:roomTypeID")
	//List<Rooms> findByRoomTypeFilterId(int roomTypeID);


}
