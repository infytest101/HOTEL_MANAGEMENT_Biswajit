package com.infosys.app.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.app.Repository.RoomRepository;
import com.infosys.app.Repository.RoomTypeRepository;
import com.infosys.app.dto.RoomDataSaveDTO;
import com.infosys.app.dto.RoomPriceTypeDto;
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

	
	@Override
	public List<RoomPriceTypeDto> fetchAllRoomData() {
		try {

			List<Rooms> roomData = repo.findAll();
			System.out.println(",,,,,,,,,,,,,,,,,,,," + roomData);
			// boolean isActive = roomData.stream().allMatch(data -> data == null);
			// if (isActive)
			// throw new NullPointerException("null value is found in fetchAllRoomData
			// mehod() kindly check once");
			List<RoomPriceTypeDto> dataValue = roomData.stream().map(data -> {
				RoomPriceTypeDto rpto = new RoomPriceTypeDto();
				// Date d1 = data.getBookDetails().getFromBookingDate();
				// Date d2 = data.getBookDetails().getToBookingDate();
				// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				// String formDate = dateFormat.format(d1);
				// String toDate = dateFormat.format(d2);
				String roomid = data.getRoomId().toString();
				String price = data.getPrice().toString();
				rpto.setId(roomid);
				rpto.setPrice(price);
				rpto.setAmenties(data.getAmenties());
				rpto.setIsACAvailable(data.getIsACAvailable());
				rpto.setIsSmokingAvailable(data.getIsSmokingAvailable());
				rpto.setRoomtypedata(data.getRoomType().getRoomType());
				// rpto.setTotalRoomAvaliable(data.getRoomType().getTotalRoomAvaliable());
				// rpto.setTotalRoomBooked(data.getRoomType().getTotalRoomBooked());
				rpto.setTotalRoomAvaliable(data.getTotalRoomAvaliable());
				rpto.setTotalRoomBooked(data.getTotalRoomBooked());
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

			room.setAmenties(amenitiesjsonData);
			room.setIsACAvailable(rm.getIsACAvailable());
			room.setPrice(rm.getPrice());
			room.setIsSmokingAvailable(rm.getIsSmokingAvailable());
			room.setTotalRoomAvaliable(rm.getTotalRoomAvaliable());
			room.setTotalRoomBooked(rm.getTotalRoomBooked());
			room.setRoomType(roomType);
			//return "ADDROOM1";
			// room.setBookDetails(bookDetails);
			repo.save(room);
		} catch (Exception ex) {
			ex.printStackTrace();
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
		}
		return null;

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
		}
		return null;

	}
}
