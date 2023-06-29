package com.infosys.app.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.app.entity.BookingDetails;
@Repository
public interface BookingRepository extends JpaRepository<BookingDetails, String> {

}
