package com.infosys.app.testclass;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infosys.app.Repository.RoomTypeRepository;
import com.infosys.app.entity.RoomType;
import com.infosys.app.service.RoomTypeService;

@SpringBootTest
 class RoomTypeTest {
	
Logger logger= LoggerFactory.getLogger(RoomTypeTest.class);
@Autowired
RoomTypeService roomtyperepo;
@Autowired
RoomTypeRepository repo;

@Test
  void checkRoomtype(){
	int roomtypeid=1;
	RoomType rm=roomtyperepo.getRoomDeatilsAll("Superior");
	int id=rm.getRoomTypeId();
	assertThat(roomtypeid).isEqualTo(id);
}
@Test
void fetchRoomTypeData(){
	List<RoomType>roomtype=roomtyperepo.getRoomDeatils();
	assertThat(roomtype)
	.isNotEmpty()
	.isNotNull()
	.hasSize(4)
	.containsAll(roomtype);
}
@Test
void roomtypeIdExist(){
	String id="1";
	//Optional<RoomType> rm =repo.findById(id);
assertThat(repo.findById(id)).isPresent();
}
}
