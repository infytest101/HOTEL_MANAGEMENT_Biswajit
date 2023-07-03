package com.infosys.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.app.beans.RequestBodydata;
import com.infosys.app.dto.RoomPriceTypeDto;
import com.infosys.app.exception.RoomTypeCostumException;
import com.infosys.app.service.RoomsService;

@RestController
public class RoomController {
	@Autowired
	RoomsService roomService;
	RoomsTypeController rc; 
	Logger logger = LoggerFactory.getLogger(RoomController.class);
	/*
	 * @GetMapping("/addCostumer") public void bookCostumer(HttpServletResponse
	 * response, Model model) throws IOException { List<String> roomtypedata =
	 * rc.fetchRoomType();
	 * logger.info("phone number avaliabale here:::::::::::::::::" + roomtypedata);
	 * model.addAttribute("roomTypDatas", roomtypedata);
	 * response.sendRedirect("views/bookHotel.jsp"); }
	 */



	@PostMapping("/addRoomData")
	public ResponseEntity<String> addRoomData(@RequestBody RequestBodydata requestdata) {
		logger.info("inside addRoomData() @RequestBody VALUES:::::::::::::::::" + requestdata);
		String Value=roomService.addRoomDetails(requestdata.getAmenitiesjsonData(), requestdata.getFormData());
		return ResponseEntity.status(HttpStatus.OK).body(Value);
	}
	@PostMapping("/addRoomDataforUpdate")
	public ResponseEntity<String> addRoomDataForUpdate(@RequestBody RequestBodydata requestdata) {
		logger.info("inside addRoomData() @RequestBody VALUES:::::::::::::::::" + requestdata);
		roomService.addRoomDetails(requestdata.getAmenitiesjsonData(), requestdata.getFormData());
		//return ResponseEntity.status(HttpStatus.OK).body("sucessfully added in DB");
		return ResponseEntity.status(HttpStatus.OK).body("updateData");
	}
	@GetMapping(value = "/fetchAllRoooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoomPriceTypeDto>> fetchBookingDetails() {
		List<RoomPriceTypeDto> rpto = roomService.fetchAllRoomData();
		logger.info("fetchAllRoooms ::::::::::::::::::::::::::::::::::"+rpto);
		return ResponseEntity.ok(rpto);	
	}
	@GetMapping(value = "/fetchAllFilterRoooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoomPriceTypeDto>> fetchBookingFilterDetails(
			@RequestParam("fromdatedata")String formData,
			@RequestParam("todatedata")String toDate,
			@RequestParam("roomtype")String roomtype
			) {
		logger.info("fromFilter Data::::::::::::"+formData+"toDate:::::"+toDate+"roomtype::::"+roomtype);
		List<RoomPriceTypeDto> rpto = roomService.fetchAllRoomDataFilterData(formData,toDate,roomtype);
		logger.info("fetchAllRoooms ::::::::::::::::::::::::::::::::::"+rpto);
		return ResponseEntity.ok(rpto);	
	}
	@GetMapping(value = "/RoomTypeFilterSearch", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoomPriceTypeDto>> RoomTypeFilterSearch(@RequestParam("roomtype")String roomtype) {
		logger.info("RoomTypeFilterSearch Data::::::::::::roomtype::::"+roomtype);
		List<RoomPriceTypeDto> rpto = roomService.fetchAllRoomDataFilterData(roomtype);
		logger.info("fetchAllRoooms ::::::::::::::::::::::::::::::::::"+rpto);
		return ResponseEntity.ok(rpto);	
	}
	//@GetMapping(value = "/deleteroomdetails/{roomid}")
	@GetMapping(value = "/deleteroomdetails")
	@ResponseBody
	public String deleteRoomData(@RequestParam("roomid")String roomId,@RequestParam("roomType")String roomType) {
		Integer roomid=Integer.parseInt(roomId);
		String data=roomService.deleteRoomDetails(roomid,roomType);
		logger.info("DeleteRoomDetails DeleteRoomDetails:::::::::roomType::::::"+roomId+"roomType:::"+roomType);
		//return "DeletePage";
		return data;	
	}
	@GetMapping("/*")
	public String ErrorPage() {
		return "404 Error url not found,check url";
	}

}
