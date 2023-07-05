package com.infosys.app.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.app.Repository.BookingRepository;
import com.infosys.app.Repository.RoomRepository;
import com.infosys.app.Repository.RoomTypeRepository;
import com.infosys.app.dto.RoomDataSaveDTO;
import com.infosys.app.dto.RoomPriceTypeDto;
import com.infosys.app.entity.Amenities;
import com.infosys.app.entity.BookingDetails;
import com.infosys.app.entity.RoomType;
import com.infosys.app.entity.Rooms;
import com.infosys.app.exception.RoomTypeCostumException;
import com.infosys.app.service.RoomsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class RoomsServiceImpl implements RoomsService {
	Logger logger = LoggerFactory.getLogger(RoomsServiceImpl.class);

	@Autowired
	RoomRepository repo;
	@Autowired
	RoomTypeRepository roomtyperepo;
	@Autowired
	EntityManager entitymanager;
	@Autowired
	BookingRepository bookrepo;
	boolean wifi = false;
	boolean ac = false;
	boolean smok = false;
	boolean TV = false;
	boolean gyser = false;
	@Override
	public List<RoomPriceTypeDto> fetchAllRoomData() {
		try {

			List<Rooms> roomData = repo.findAll();
			System.out.println(",,,,,,,,,,,,,,,,,,,," + roomData);
			List<RoomPriceTypeDto> dataValue = roomData.stream().map(data -> {
				RoomPriceTypeDto rpto = new RoomPriceTypeDto();
				String roomid = data.getRoomId().toString();
				int price1=data.getPrice();
				String price=String.valueOf(price1); 
				rpto.setId(roomid);
				rpto.setPrice(price);
				//BookingDetails bk=data.getBookingDetails();
				//logger.info("bkbkbkbkbkbkbkbkbkbkbkbkbkbkbkbk"+bk.getBookingDetailsId());
				rpto.setIsACAvailable(data.getAmenities().isACAvailable());
				rpto.setIsSmokingAvailable(data.getAmenities().isSmokingAvailable());
				rpto.setRoomtypedata(data.getRoomType().getRoomType());
				 rpto.setTotalRoomAvaliable(data.getTotalRoomsAvailable());
				rpto.setTotalRoomBooked(data.getBookingDetails().getTotalRoomsBooked());
				rpto.setRoomName(data.getRoomName());
				rpto.setGeyserAvailable(data.getAmenities().isGeyserAvailable());
				rpto.setWifiAvailable(data.getAmenities().isWifiAvailable());
				rpto.setTVAvailable(data.getAmenities().isTVAvailable());
				//rpto.setTotalRoomBooked(data.getBookingDetails().getTotalRoomsBooked());
				// rpto.setFromDate(formDate);
				// rpto.setToDate(toDate);
				return rpto;
			}).collect(Collectors.toList());
			logger.info(" RoomsServiceImpl :fetchAllRoomData:  Rooms Table PRICE  ID  RoomType Data::"
					+ dataValue.toString());
			return dataValue;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public String addRoomDetails(String amenitiesjsonData, String formData) {
		try {

			Rooms room = new Rooms();
			ObjectMapper mapper = new ObjectMapper();
			RoomDataSaveDTO rm = mapper.readValue(formData, RoomDataSaveDTO.class);
			logger.info("rm.getRoomid()::::::" + rm.getRoomid());
			RoomType roomType = roomtyperepo.findByRoomType(rm.getRoomType());
			if (rm.getRoomid() > 0) {
				repo.findById(rm.getRoomid()).orElseThrow(
						() -> new RoomTypeCostumException("Rooms Data not Found with id:" + rm.getRoomid()));
				room.setRoomId(rm.getRoomid());
			}

			// Date fromDate = new
			// SimpleDateFormat("yyyy-MM-dd").parse(mapvalue.get("fromDate"));
			// Date toDate = new
			// SimpleDateFormat("yyyy-MM-dd").parse(mapvalue.get("toDate"));
			// bookDetails.setFromBookingDate(fromDate);
			// bookDetails.setToBookingDate(toDate);

			Amenities amenities = new Amenities();
			logger.info("amenitiesjsonData::::" + amenitiesjsonData);

			JSONObject josnobj = new JSONObject(amenitiesjsonData);
			String ac1 = rm.getIsACAvailable();
			String smok1 = rm.getIsSmokingAvailable();

			if (josnobj.has("WIFI")) {
				wifi = true;
			}
			if (josnobj.has("TV")) {
				TV = true;
			}
			if (josnobj.has("Geyser")) {
				gyser = true;
			}

			if (ac1.equalsIgnoreCase("AC")) {
				ac = true;
			}
			if (smok1.equalsIgnoreCase("Smoking")) {
				smok = true;
			}

			logger.info("wifi: " + wifi + "TV: " + TV + "GYSER: " + gyser + "smok:" + smok + "ac: " + ac);
			amenities.setACAvailable(ac);
			amenities.setSmokingAvailable(wifi);
			amenities.setGeyserAvailable(gyser);
			amenities.setWifiAvailable(wifi);
			amenities.setTVAvailable(TV);

			BookingDetails bookingDetails = new BookingDetails();
			bookingDetails.setTotalRoomsBooked(rm.getTotalRoomBooked());
			bookingDetails.setCreateBy("biswajit Nayak");
			bookingDetails.setCreateDate(new Date());
			bookingDetails.setRooms(room);

			room.setRoomName("SuperiorAC");
			room.setBookingDetails(bookingDetails);
			room.setAmenities(amenities);
			room.setRoomType(roomType);
			room.setTotalRoomsAvailable(rm.getTotalRoomAvaliable());
			room.setCreateBy("Biswajit Nayak");
			room.setPrice(rm.getPrice());
			room.setCreateDate(new Date());
			room.setUpdatedBy("");
			room.setUpdateDate(null);

			// return "ADDROOM1";
			// room.setBookDetails(bookDetails);
			repo.save(room);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error";
		}
		return "ADDROOM";
	}

	@Override
	public String deleteRoomDetails(Integer roomid, String roomType1) {
		try {

			// RoomType roomType=roomtyperepo.findByRoomType(roomType1);
			// int bookedData1=roomType.getTotalRoomBooked();
			// int bookedData=bookedData1-1;
			// int roomTypeId=roomType.getRoomTypeId();
			// RoomType roomTyp=new RoomType();
			// roomTyp.setTotalRoomBooked(bookedData);
			// roomTyp.setRoomTypeId(roomTypeId);
			// roomtyperepo.save(roomType);
			repo.deleteById(roomid);
			return "deletedata";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error";
		}

	}

	@Override
	public List<RoomPriceTypeDto> fetchAllRoomDataFilterData(String fromDatefilter, String toDateFilter,
			String roomtype) {
		try {

			// Date fromDate = null;
			// Date toDate = null;

			Date fromdatefilter = new SimpleDateFormat("yyyy-MM-dd").parse(fromDatefilter);
			Date todatefilter = new SimpleDateFormat("yyyy-MM-dd").parse(toDateFilter);
			String Query = "SELECT r FROM Rooms r INNER JOIN r.bookDetails b "
					+ "ON r.bookDetails.bookingDetailsId=b.bookingDetailsId " + "where b.fromBookingDate  BETWEEN '"
					+ fromDatefilter + "'AND'" + toDateFilter + "'";
			if (!roomtype.equalsIgnoreCase("")) {
				logger.info("roomtype roomtype roomtype" + roomtype);
				RoomType roomType = roomtyperepo.findByRoomType(roomtype);
				int roomTypeID = roomType.getRoomTypeId();
				logger.info("roomTypeID roomTypeID roomTypeID" + roomTypeID);
				Query = "SELECT r FROM Rooms r INNER JOIN r.bookDetails b "
						+ "ON r.bookDetails.bookingDetailsId=b.bookingDetailsId " + "where b.fromBookingDate  BETWEEN '"
						+ fromDatefilter + "'AND'" + toDateFilter + "' AND roomType.roomTypeId=" + roomTypeID + "";
			}

			// String Query="SELECT r FROM Rooms r INNER JOIN booking_details b ON
			// r.booking_details_id = b.booking_details_id where from_booking_date BETWEEN
			// '2023-06-13' and '2023-06-15'";
			// String Query="SELECT r FROM Rooms r INNER JOIN r.bookDetails b ON
			// b.fromBookingDate like'%"+fromDatefilter+"%'";

			Query query = entitymanager.createQuery(Query);
			List<Rooms> roomData = query.getResultList();

			logger.info("fromdatefilter:::" + fromdatefilter + "todatefilter::" + todatefilter);
			// List<Rooms> roomData =
			// repo.findByFromDateFilter(fromdatefilter,todatefilter);
			logger.info("findByFromDateFilter :::" + roomData);
			List<RoomPriceTypeDto> dataValue = roomData.stream().map(data -> {
				RoomPriceTypeDto rpto = new RoomPriceTypeDto();
				Date d1 = data.getBookDetails().getFromBookingDate();
				Date d2 = data.getBookDetails().getToBookingDate();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String formDate = dateFormat.format(d1);
				String toDate = dateFormat.format(d2);
				String roomid = data.getRoomId().toString();
				String price = data.getPrice().toString();
				rpto.setId(roomid);
				rpto.setPrice(price);
				rpto.setAmenties(data.getAmenties());
				rpto.setIsACAvailable(data.getIsACAvailable());
				rpto.setIsSmokingAvailable(data.getIsSmokingAvailable());
				rpto.setRoomtypedata(data.getRoomType().getRoomType());
				rpto.setFromDate(formDate);
				rpto.setToDate(toDate);
				return rpto;
			}).collect(Collectors.toList());
			logger.info(" RoomsServiceImpl :fetchAllRoomData:  Rooms Table PRICE  ID  RoomType Data::"
					+ dataValue.toString());
			return dataValue;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public List<RoomPriceTypeDto> fetchAllRoomDataFilterData(String roomtype) {
		try {
			if (!roomtype.equalsIgnoreCase("")) {

				logger.info("roomtype roomtype roomtype" + roomtype);
				RoomType roomType = roomtyperepo.findByRoomType(roomtype);
				int roomTypeID = roomType.getRoomTypeId();
				String Query = "SELECT r FROM Rooms r where roomType.roomTypeId=" + roomTypeID + "";
				Query query = entitymanager.createQuery(Query);
				List<Rooms> roomData = query.getResultList();
				List<RoomPriceTypeDto> dataValue = roomData.stream().map(data -> {
					RoomPriceTypeDto rpto = new RoomPriceTypeDto();
					String roomid = data.getRoomId().toString();
					String price = data.getPrice().toString();
					rpto.setId(roomid);
					rpto.setPrice(price);
					rpto.setAmenties(data.getAmenties());
					rpto.setIsACAvailable(data.getIsACAvailable());
					rpto.setIsSmokingAvailable(data.getIsSmokingAvailable());
					rpto.setRoomtypedata(data.getRoomType().getRoomType());
					rpto.setTotalRoomAvaliable(data.getTotalRoomAvaliable());
					rpto.setTotalRoomBooked(data.getTotalRoomBooked());
					return rpto;
				}).collect(Collectors.toList());
				logger.info(" RoomsServiceImpl :fetchAllRoomDataFilterData:  Rooms Table PRICE  ID  RoomType Data::"
						+ dataValue.toString());
				return dataValue;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;

	}
}
