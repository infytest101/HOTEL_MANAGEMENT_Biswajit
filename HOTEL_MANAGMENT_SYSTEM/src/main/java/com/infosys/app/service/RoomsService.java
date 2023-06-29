package com.infosys.app.service;
import java.util.List;
import com.infosys.app.dto.RoomPriceTypeDto;
import com.infosys.app.dto.TypeDto;
public interface RoomsService {
public List<RoomPriceTypeDto> fetchAllRoomData();
public String addRoomDetails(String amenitiesjsonData, String formData);
public String deleteRoomDetails(Integer roomid, String roomType);
public List<RoomPriceTypeDto> fetchAllRoomDataFilterData(String formData, String toDate, String roomtype);
public List<RoomPriceTypeDto> fetchAllRoomDataFilterData(String roomtype);
}
