<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOTEL MANAGMENT SYSTEM</title>
<script type="text/javascript" charset="utf8"
src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>  
body{  
  font-family: Calibri, Helvetica, sans-serif;  
  background-color: lightblue; 
  padding: 50px;
}   
</style>  
</head>
<body style="text-align:Center;">
<form id="myForm" action="/infy/addCostumer" method="POST">
<center>  <h1> Book Hotel Managment Form</h1> </center>
<label> Enter Customer Name </label>   
<input type="text" id="customerName" name="customerName" placeholder= "customerName" size="20" required /> <br>  
<label for="email">Customer Email Id: </label>  
<input type="email" id="email" name="email"/> <br/>  
Phone No :  
</label>  
<input type="text" name="phone" placeholder="phone no." size="10"/ required><br>  
Booking a Date: <input type = "date" name="bookingDates"><br>
<label for="quantity">price :</label>
<input type="number" name= "price"min="1000" max="5000"></br>


Select RoomType:
<select id="mycombo">
</select>
 <br>
<label>  
plese choose Room type:      
</label>
<input type="radio" id="AC" name="AC" value="AC"/> AC    
<input type="radio" id=" NONAC" name=" NONAC" value="NONAC"/> NON-AC 
<br>
<label>  
plese choose Room type:   
</label>
<input type="radio" id="Smok" name="Smok" value="Smok"/> Smoking    
<input type="radio" id=" NOSmok" name=" NOSmok" value="NOSmok"/> Non-Smoking Room
</br>
Select facility: <br>    
              <input type="checkbox" id="WIFI" name="WIFI" value="WIFI"/>    
                 <label>WI-FI</label> <br>    
              <input type="checkbox" id="TV" name="TV" value="TV"/>    
                 <label>TV</label> <br>    
              <input type="checkbox" id="Geyser" name="Geyser" value="Geyser"/>    
                 <label>Geyser</label> <br> 
Description:
<textarea cols="80" rows="5" placeholder="Description" name="Description" required>  
</textarea>
<br>
<button type="submit">Click Here</button>
</form>




<script> 
function hello(){  
confirm("hello javatpoint user");
}




$(document).ready(function(){ // collect Room Type Data From Dynamic DB(RoomsController.java)
	var dataList=[];
	$.ajax({
		type :'GET',
		url:'/infy/getRoomType',
		success: function(result) {
          console.log("value addded:::::"+result);
          dataList=result;
          populateComboBox(dataList);
		},
         error:function(xhr,status,error){
			console.error(error);
           }
	});
	
	
	
	
	function populateComboBox(data) {
		var comboBox=$("#mycombo");
		comboBox.empty();
		comboBox.append($('<option>').text("please select roomType:::").val(''));
		$.each(data,function(index,value){
			comboBox.append($('<option>').text(value).val(value));
		});
	}
	
	
	$('#myForm').submit(function(e){
		e.preventDefault();
	var formdata=$(this).serialize();
	$.ajax({
		type :'POST',
		url:'/infy/addCostumer',
		contentType:'application/json;charset=utf8',
		 data: JSON.stringfy(formdata),	
		success: function(result) {
          console.log("value addded:::::"+result);
		},
         error:function(xhr,status,error){
			console.error(error);
           }
	});
	});
});
</script>
</body>
</html>