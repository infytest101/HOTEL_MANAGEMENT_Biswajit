package com.infosys.app.controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.infosys.app.dto.RoomPriceTypeDto;
import com.infosys.app.entity.RoomType;
import com.infosys.app.service.RoomTypeService;
import com.infosys.app.service.RoomsService;
@Controller
public class HomePageController {
	@Autowired
	RoomTypeService rs;
	@Autowired
	RoomsService repo;
	
	Logger logger= LoggerFactory.getLogger(HomePageController.class);
	
@GetMapping("/homepage")
public String homeCall(Model model) {
	List<RoomType>rtype=rs.getRoomDeatils();
	List<String>roomtypedata=rtype.stream().map(RoomType::getRoomType).collect(Collectors.toList());
	model.addAttribute("roomTypData",roomtypedata);
	List<RoomPriceTypeDto>priceType =repo.fetchAllRoomData();
	model.addAttribute("PriceType",priceType);
	logger.info("fetching roomType Data from Dynamic DB:::"+roomtypedata);
	return "home";
}
//@GetMapping("/UpdatePage")
@GetMapping("/ManageRoom")
public String ManageRoomUrl() {
	logger.info("HomePageController:: editDeleteRenderUrl() :: EditDeleteRoom :: Rendering page");
	return "ManageRoom";
}
@GetMapping("/DataUpdatePage")
@ResponseBody
public String editUpdateRenderUrl() {
	logger.info("HomePageController:: editUpdateRenderUrl() :: DataUpdatePage :: Rendering page");
	return "DataUpdatePage";
}

@GetMapping("/ManageBooking")
public String ManageBookingUrl() {
	logger.info("HomePageController:: addpageeRenderUrl() :: SuccessAddPage :: Rendering page");
	return "ManageBooking";
}


@PostMapping("/editroomdetails")
@ResponseBody
public String editRenderUrl(@RequestParam("editEncodingvalue") String formData, Model model){
	logger.info("for edit value"+formData);
	model.addAttribute("price", formData);
	logger.info("HomePageController:: editroomdetails() :: EditRoom :: Rendering page");
	//return new ModelAndView("EditHotel");
	return "EditHotel";
}
@GetMapping("/addCostumer")
public ModelAndView bookRoomUrl(Model model) {
	/*
	 * List<String> roomtypedata; try { roomtypedata = rc.fetchRoomType();
	 * logger.info("roomtypedata roomtypedata here:::::::::::::::::" +
	 * roomtypedata); model.addAttribute("roomTypDatas", roomtypedata); } catch
	 * (RoomTypeCostumException e) { e.printStackTrace(); }
	 */
	return new ModelAndView("bookHotel");
	//return "bookHotel";
}
@GetMapping("/editroomdetails1")
@ResponseBody
public ModelAndView editRenderUrl1(@RequestParam(value="price",required = false)String price,
		@RequestParam(value="roomtypedata")String roomtypedata,
		@RequestParam(value="roomid")String roomid,
		@RequestParam(value="totalRoomAvaliable")String totalRoomAvaliable,
		@RequestParam(value="totalRoomBooked")String totalRoomBooked,
		@RequestParam(value="acnonac")String acnonac,
		@RequestParam(value="smoknonsmok")String smoknonsmok,
		@RequestParam(value="wifitv")String wifitv,Model model){
	logger.info("for edit value   :::price="+price+"roomtypedata="+roomtypedata+"roomid="+roomid+"totalRoomAvaliable="+totalRoomAvaliable+
			"totalRoomBooked="+totalRoomBooked+"acnonac="+acnonac+"smoknonsmok="+smoknonsmok+"wifitv="+wifitv);
	//Date date2=new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
	System.out.println("totalRoomAvaliable1111="+totalRoomAvaliable+"totalRoomBooked11111="+totalRoomBooked);
	//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	model.addAttribute("roomtypedata", roomtypedata);
	model.addAttribute("price", price);
	model.addAttribute("roomid", roomid);
	//model.addAttribute("totalRoomAvaliable11", totalRoomAvaliable);
	//model.addAttribute("totalRoomBooked11", totalRoomAvaliable);
	model.addAttribute("totalRoomAvaliabledata",totalRoomAvaliable);
	model.addAttribute("totalRoomBookeddata", totalRoomBooked);
	model.addAttribute("acnonac", acnonac);
	model.addAttribute("smoknonsmok", smoknonsmok);
	model.addAttribute("wifitv", wifitv);

	logger.info("HomePageController:: editroomdetails() :: EditRoom :: Rendering page");
	return new ModelAndView("EditHotel");
//return "EditHotel";
}
}
