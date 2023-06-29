package com.infosys.app.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.app.entity.RoomType;
import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType,String> {
RoomType findByRoomType(String roomType);
}
