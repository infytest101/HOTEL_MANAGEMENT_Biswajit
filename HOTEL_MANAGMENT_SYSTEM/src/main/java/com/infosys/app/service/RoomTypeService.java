package com.infosys.app.service;
import java.util.List;
import com.infosys.app.entity.RoomType;
public interface RoomTypeService {
	List<RoomType> getRoomDeatils();

	RoomType getRoomDeatilsAll(String roomtype);
}
