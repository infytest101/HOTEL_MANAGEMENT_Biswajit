package com.infosys.app.testclass;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infosys.app.Repository.RoomRepository;
import com.infosys.app.entity.Rooms;
import com.infosys.app.service.RoomsService;

@SpringBootTest
 class RoomTest {
@Autowired
RoomRepository repo;
@Autowired
RoomsService roomservice;
@Test
void checkAddingData() {
	String JsonData1="{\"roomType\":\"Superior\",\"price\":\"1234542\",\"isACAvailable\":\"NONAC\",\"isSmokingAvailable\":\"NonSmoking\",\"totalRoomAvaliable\":\"1\",\"totalRoomBooked\":\"0\"}";
	String JsonData2="{\"isSmokingAvailable\":\"NonSmoking\",\"isACAvailable\":\"NONAC\",\"amenties\":[\"WIFI\",\"TV\",\"Geyser\"]}";
	roomservice.addRoomDetails(JsonData2, JsonData1);
	//Rooms rmdata=new Rooms();
	//repo.save(rmdata);
	//assertThat(repo.findById(1345)).isPresent();
}

}
