package com.infosys.app.serviceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infosys.app.Repository.RoomTypeRepository;
import com.infosys.app.entity.RoomType;
import com.infosys.app.service.RoomTypeService;
@Service
public class RoomtypeServiceImpl implements RoomTypeService{
	Logger logger= LoggerFactory.getLogger(RoomtypeServiceImpl.class);
@Autowired
RoomTypeRepository rt;
public List<RoomType> getRoomDeatils(){
	logger.info("RoomtypeServiceImpl::::getRoomDeatils()::RoomType::"+rt.findAll());
	return rt.findAll();	
}
@Override
public RoomType getRoomDeatilsAll(String roomtype) {
	RoomType rty = null;
	
	try {
		 rty=rt.findByRoomType(roomtype);
		 logger.info("RoomtypeServiceImpl::::getRoomDeatilsAll()::RoomTypeALL::"+rty);
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	return rty;
}
}