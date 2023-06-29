package com.infosys.app.controller;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.app.entity.RoomType;
import com.infosys.app.exception.RoomTypeCostumException;
import com.infosys.app.service.RoomTypeService;
@RestController
public class RoomsTypeController {
	@Autowired
	RoomTypeService rs;
	Logger logger= LoggerFactory.getLogger(RoomsTypeController.class);
@GetMapping("/getRoomType")// collect RoomData From Dynamic DB
public List<String> fetchRoomType() throws RoomTypeCostumException {
	List<RoomType>rtype=rs.getRoomDeatils();
	boolean isAvaliable = rtype.stream().allMatch(Objects::isNull);
	if (isAvaliable)
		throw new RoomTypeCostumException("Room Type Data Not avaliable in DB table kindly insert data::::");
	List<String>roomtypedata=rtype.stream().map(RoomType::getRoomType).collect(Collectors.toList());
	logger.info("fetching roomType Data from DB:::"+roomtypedata);
	return roomtypedata;
}
 

@GetMapping("/getRoomTypeAllData")// collect RoomData From Dynamic DB
public RoomType getRoomTypeAllData(@RequestParam("roomtype")String roomtype){
	return rs.getRoomDeatilsAll(roomtype);
}
}
 