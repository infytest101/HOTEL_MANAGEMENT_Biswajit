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
Select RoomType:
<br><select id="mycombo" name="roomtype"></select></br>
<label for="quantity">price :</label>
<input type="number" id="price" name= "price" min="1000" max="5000"></br>
 plese choose Room type: 
<br><label>  <input type="radio"id="isACRoom" name="isACRoom" value="AC"/>AC    </label>
<label> <input type="radio"  name="isACRoom" value="NONAC"/> NON-AC  </label>
</br>
 <br>
plese choose Room type:   
<label>
<input type="radio"  name="SmokingAvailable" value="Smok"/> Smoking 
<input type="radio"  name="SmokingAvailable" value="NOSmok"/> Non-Smoking Room
</label>
</br>
Select facility: <br>    
              <input type="checkbox" id="WIFI" name="checkdata" value="WIFI"/>    
                <label>WI-FI</label> <br>    
              <input type="checkbox" id="TV" name="checkdata" value="TV"/>    
                <label>TV</label> <br>    
              <input type="checkbox" id="Geyser" name="checkdata" value="Geyser"/>    
                <label>Geyser</label> </br> 
<button id="adddata" type="submit">Click Here</button>
</form>




<script> 

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
	var comboBox;
	function populateComboBox(data) {// this method used for showing Roomtype in dynamicDrop Down
		 comboBox=$("#mycombo");
		comboBox.empty();
		comboBox.append($('<option>').text("please select roomType:::").val(''));
		$.each(data,function(index,value){
			comboBox.append($('<option>').text(value).val(value));
		});
	}
	
	var formData;
	var allData={};
	$('#myForm').submit(function(e){
	var submitdata=confirm("Kindly check once Before submiting,Are you sure to submit." );
		console.log("submitted value"+submitdata);
		e.preventDefault();
	 formdata=$(this).serialize();
	/*allData= {
			formData:formdata,
			AmenitiesjsonData};
			console.log("all the form data:::::::"+JSON.stringify(allData));*/
	$.ajax({
		type :'POST',
		url:'/infy/addRoomData',
		contentType:'application/json;charset=utf-8',
		 data:JSON.stringify({
			formData:formdata,
			AmenitiesjsonData:AmenitiesjsonData
		 }),
		success: function(result) {
          console.log("value addded:::::"+result);
		},
         error:function(xhr,status,error){
			console.error(error);
           }
	});
	});
	
	
	var AmenitiesjsonData={};
	$('#adddata').click(function(e){
		var amentis= {};
		var CheckValue=[];
		var radioValue=$('input[name="SmokingAvailable"]:checked').val();
		var ACValue=$('input[name="isACRoom"]:checked').val();
		var rmtype=$("#mycombo").val();
		var price=$("#price").val();
	if(rmtype==""){
		alert("please select a RoomType");
		return false;
		
	}
	else if(price==""){
		alert("please enter price value");
		return false;
		
	}
	else if(ACValue=="" || typeof ACValue==="undefined"){
		alert("please  select AC ROOM / NON-AC ROOM");
		return false;
		
	}

	else if(radioValue=="" || typeof radioValue==="undefined"){
		alert("please select Smoking Room or Non-Smoking Room ");
		return false;
		
	}
		amentis.isSmokingAvailable=radioValue;
		amentis.isACAvailable=ACValue;
		
				$('input[name="checkdata"]:checked').each(function(){
					CheckValue.push($(this).val());
				});
				amentis.checkboxes=	CheckValue;
				AmenitiesjsonData=JSON.stringify(amentis);
				console.log(" Amenities jSONData::::::::::"+AmenitiesjsonData);
	});
	
});
</script>
</body>
</html>