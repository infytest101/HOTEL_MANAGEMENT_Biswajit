<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Room</title>
<script type="text/javascript" charset="utf8"
src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf8"
src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
src="https://cdn.datatables.net/1.10.16/js/dataTables.jqueryui.min.js"></script>
<link rel="stylesheet" type="text/css"
href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
href="https://cdn.datatables.net/1.10.16/css/dataTables.jqueryui.min.css" />
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">



<style type="text/css">
.form-row{
margin-bottom:200px;
}
body{  
  font-family: Calibri, Helvetica, sans-serif;  
  background-color:#33ffff;
  padding: 50px;
} 
 #price,#fromDate,#toDate{
 width:160px
 }
 
 h1{
 text-align:left
 }
 
 #adddata{
position:absolute;
bottom:1px;
left:25%;
transform: translateX(-50%);
 }
 
 .form-container label {
 width: 200px;
 text-align: left;
 margin-right:90px;
 padding: 5px;

/* text-align: right;
margin-right: 90px;
gap:50px;
padding: 5px; */ 
}
</style>
<body>
   <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    Manage Room
                </a>
            </div>
    <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    Manage Booking
                </a>
    </div>
</head>
<form id="myForm" action="/infy/addCostumer" method="POST">
 <h1> Update Hotel Management Form</h1> 
<div class="form-container">
<label id="rmo" for="roomid">Room ID:</label>
<input type="text" id="roomid" name="roomid" value=${roomid}>
</div>

<div class="form-container">
<label>RoomType:</label>
<select class="custom-select" id="mycombo"  name="roomtype"></select><br>
</div>

<div class="form-container">
<label for="quantity">Price :</label>
<input type="number" id="price" name= "price" value =${price} min="1000" max="25000"><br>
</div>

<div class="form-container">
<label>  Please Choose Amenties1:</label>  
<input type="radio"id="isACRoom" name="isACRoom" value="AC"/>AC    
 <input type="radio"  name="isACRoom" value="NONAC"/> NON-AC  <br>
 </div>
 
 <div class="form-container">
<label>Please Choose Amenties2:</label>
<input type="radio"  name="SmokingAvailable" value="Smok"/> Smoking 
<input type="radio"  name="SmokingAvailable" value="NOSmok"/> Non-Smoking Room<br>
</div>

<div class="form-container">
<label>Please Select Booking From:</label>
<input type="date" id="fromDate" name="fromDate"value=${fromDate}><br>
</div>

<div class="form-container">
<label>Please Select Booking To:</label>
<input type="date" id="toDate" name="toDate" value=${toDate}><br>
</div>

<div class="form-container">
<label>Select Facility: </label>    
              <input type="checkbox" id="WIFI" name="checkdata" value="WIFI"/>WI-FI  
              <input type="checkbox" id="TV" name="checkdata" value="TV"/> TV    
              <input type="checkbox" id="Geyser" name="checkdata" value="Geyser"/> Geyser<br> 
</div>

<div class="form-container">
<button id="adddata" class="btn btn-dark"type="submit">Update Room</button>
</div>


</form>
</body>
<script type="text/javascript">
function showMassage(message,duration) {  
	var massageElement=document.createElement("div");
	massageElement.textContent=message;
	massageElement.style.position="fixed";
	massageElement.style.top="50px";
	massageElement.style.left="50px";
	massageElement.style.display="flex";
	//massageElement.style.tranform="translate(-50%, -50%)";
	massageElement.style.justifyContent="center";
	massageElement.style.alignItems="center";
	massageElement.style.textAlign="center";
	massageElement.style.padding="10px";
	massageElement.style.backgroundColor="green";
	massageElement.style.color="white";
	massageElement.style.borderRadius="5px";
	massageElement.style.width="60%";
	massageElement.style.textAlign="center";
	document.body.appendChild(massageElement);
	setTimeout(function(){
		massageElement.remove();
		},duration);
	 } 
<%-- var data='<%= request.getAttribute("price") %>';
console.log("datadatadatadatadata"+data); --%>
//var smokdata=${smoknonsmok};
var smoknonsmok='<%= request.getAttribute("smoknonsmok") %>';
var acnonac='<%= request.getAttribute("acnonac") %>';
var wifitvdata='<%=request.getAttribute("wifitv")%>';
var roomtypedata='<%=request.getAttribute("roomtypedata")%>';
console.log("roomtypedata......"+roomtypedata);
var wifitv=wifitvdata.split(",");
$(document).ready(function () {
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
		comboBox.append($('<option>').text("Select roomType...").val(''));
		$.each(data,function(index,value){
			comboBox.append($('<option>').text(value).val(value));
			comboBox.find("option").each(function(){
				if($(this).val()===roomtypedata){
					$(this).prop("selected",true);
				}
			});
		});
		//$('input[name="roomtype"][value="'+roomtypedata+'"]').prop('checked',true);
	}
$('input[name="SmokingAvailable"][value="'+smoknonsmok+'"]').prop('checked',true);
$('input[name="isACRoom"][value="'+acnonac+'"]').prop('checked',true);
//$('input[name="roomtype"][value="'+roomtypedata+'"]').prop('checked',true);
//$('input[name="checkdata"][value="'+wifitv+'"]').prop('checked',true);
$('#mycombo').val(roomtypedata);
$('#mycombo').prop('checked',$('#mycombo').val()===roomtypedata);
$('input[name="checkdata"]').each(function(){
	if(wifitv.includes($(this).val())){
	$(this).prop('checked',true);
}
});
var formData;
var allData={};
$('#myForm').submit(function(e){
var submitdata=confirm("Kindly check once Before submiting,Are you sure to submit." );
	console.log("submitted value"+submitdata);
	
	var fromdatedata=$('#fromDate').val();
	var todatedata=$('#toDate').val();
	var  date1 = new Date(fromdatedata);
	var date2 = new Date(todatedata);
		if((fromdatedata=="") || (todatedata=="")){
		alert("kindly provide Date input for Update Room");
		return false;
	}
		else if(date1>date2){
			alert(" toDate Should be Greater then or equals to fromDate");
			return false;
		}
		
	e.preventDefault();
 formdata=$(this).serialize();
 console.log("Edit Data for Updatting Room::::::"+formdata);
/*allData= {
		formData:formdata,
		AmenitiesjsonData};
		console.log("all the form data:::::::"+JSON.stringify(allData));*/
$.ajax({
	type :'POST',
	url:'/infy/addRoomDataforUpdate',
	contentType:'application/json;charset=utf-8',
	 data:JSON.stringify({
		formData:formdata,
		AmenitiesjsonData:AmenitiesjsonData
	 }),
	success: function(result) {
      console.log("DataUpdatePage value addded:::::"+result); 
      if(result ==='updateData') {
			showMassage("Room data Updated SucessFully",5000);
      }
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
$('#fromDate').on('change', function() {
	var fromDate = $('#fromDate').val().trim();
	$('#toDate').attr('min',fromDate);
	$('#Hotel').DataTable().draw();
	});

	$('#toDate').on('change', function() {
	var toDate = $('#toDate').val().trim();
	$('#fromDate').attr('max',toDate);
	$('#Hotel').DataTable().draw();
	});
});
</script>
</html>