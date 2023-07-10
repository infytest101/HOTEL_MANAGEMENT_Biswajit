
package com.infosys.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

@Service
public class EmailServiceImpl {
	Logger logger = LoggerFactory.getLogger(RoomtypeServiceImpl.class);
	@Autowired
	JavaMailSender mailSender;

	/*
	 * public EmailServiceImpl(JavaMailSender javaMailSender) { this.javaMailSender
	 * = javaMailSender; }
	 */
	public String sendEmail(String amenitiesjsonData) throws Exception {
		String amenities1 = null;
		
		  JSONObject jsonObject= new JSONObject(amenitiesjsonData);
		  JSONArray jsonArray=jsonObject.getJSONArray("amenties");
		  String[] amenities = new String[jsonArray.length()];
		  int index=0;
		  for(Object obj:jsonArray) {
			  amenities[index++]=obj.toString();
		  }
		 
		logger.info("amenities1 amenities1" + amenities);
		MimeMessage message = mailSender.createMimeMessage();

		try {

			logger.info("Started creating mail");

			LocalDateTime currentDate = LocalDateTime.now();
			message.setFrom("biswajitnayak815@gmail.com");

			message.setSubject("Successfully Added Room");

			message.setRecipients(RecipientType.TO, "biswajitnayak814@gmail.com");

			//String[] amenities = amenities1.split(",");

			String htmlTemplate = "<p>Dear ${name},</p>"

					+ " <p>You have successfully added ${roomType} room.Please see your added room details as below:</p>"

					+ " <p>Room Type : ${roomType}</p>" + " <p>Added Date : ${BookingDate}</p>"

					+ " <p>Amenities Details :</p>";

			if (amenities.length == 0)

				htmlTemplate = htmlTemplate + " <p>&emsp;&emsp;No amenities added</p>";

			else {

				for (String amenity : amenities) {

					htmlTemplate = htmlTemplate + " <p>&emsp;&emsp;-" + amenity + "</p>";

				}

			}

			htmlTemplate = htmlTemplate + " <p>Thanks and Regards,</p>" + " <p>Infy-Hotel Team</p>";

			htmlTemplate = htmlTemplate.replace("${name}", "Biswajit");

			htmlTemplate = htmlTemplate.replace("${roomType}", "superior");

			htmlTemplate = htmlTemplate.replace("${BookingDate}", currentDate.toString());

			message.setContent(htmlTemplate, "text/html; charset=utf-8");

			logger.info("Created mail");

		} catch (MessagingException e) {

			logger.error(e.getMessage());

			throw new Exception(e.getMessage());

		}

		mailSender.send(message);

		logger.info("Successfully sent mail");

		return "Successfully sent mail";

	}

}
