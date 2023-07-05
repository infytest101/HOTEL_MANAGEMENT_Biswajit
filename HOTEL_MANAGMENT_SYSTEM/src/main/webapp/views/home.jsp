<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOTEL MANAGMENT SYSTEM</title>
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
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0px;
  overflow: hidden;
  background-color: lightgray;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
 font-size:20px;
  text-align: center;
  padding: 10px 20px;
  text-decoration: none;
}
.active{
 background-color:#33ffff;
color: white;
}
li a:hover {
  background-color: green;
  color: white;
}
/*  .rm,#roomid,.tra,.trb,#totalRoomAvaliable,#totalRoomBooked{
	 display:none
	 } */
</style>
</head>
<body>
<h2>HOTEL MANAGMENT SYSTEM</h2>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" href="http://localhost:8084/infy/ManageRoom">Manage Room</a>
        </li>
        <li class="nav-item">
          <a id="bookmanage"class="nav-link active" href="http://localhost:8084/infy/ManageBooking">Manage Booking</a>
        </li>
        </li>
      </ul>
    </div>
  </div>
</nav>
<table id="Hotel" class="table table-bordered table-striped"style="width: 100%">
<thead>
	<tr>
		<th id="roomid">Room ID</th>
		<th id="roomtypedata">Room Type</th>
		<th id="roomName">Room Name</th>
		<th id="price">Price</th>
		<th id="totalRoomAvaliable">Room availability</th>
		<th id="totalRoomBooked">Room Booked</th>
		<th id="acnonac">AC / NON-AC</th>
		<th id="smoknosmok">Smoking / Non-Smoking</th>
		<th id="amentis">WIFI/ TV / Geyser</th>
		<th id="editdel">Action</th>
		
	</tr>
</thead>
<tbody>
</tbody>
</table>

<button  type="submit"  onclick="addRoom()" class="btn btn-primary">Add Room</button>
<script>


function showMassage(message,duration,colordata) {  
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
	massageElement.style.backgroundColor=colordata;
	massageElement.style.color="white";
	massageElement.style.borderRadius="5px";
	massageElement.style.width="60%";
	massageElement.style.textAlign="center";
	document.body.appendChild(massageElement);
	setTimeout(function(){
		massageElement.remove();
		},duration);
	 } 
	 
function editdata(value){
	var editformData='';
	 var editEncodingvalue='';
	 var rowdata1={};
		if(value=="delete"){
			 $('#Hotel').on('change','.deleteclasss',function(){
			 var rowdata=$(this).closest('tr').find('td').map(function(){
				  var fieldname=$(this).data('field-name');
				  var value=$(this).text();
				  return fieldname +'='+value;
			  }).get();
			  var fieldname1=$(this).closest('tr').find('.rm').data('field-name');
			  var rowdata111=$(this).closest('tr').find('.rm').text();
			  var rmtydt=$(this).closest('tr').find('.rmtydt').text();
			  var rowdataid="&"+rowdata111;
			  console.log("rowdata111 rowdata111 rowdata111"+rowdata111);
			  console.log("rmtydt rmtydt rmtydt"+rmtydt);
			  var Encodingvalue1=rowdata.join('&');
			  var Encodingvalue=encodeURIComponent(rowdata.join('&'));
			  console.log("Encodingvalue"+Encodingvalue1);
			  var rmval= confirm("Are you sure to delete this item?");
			  if(rmval==true) {
				  //window.location.href = "/infy/deleteroomdetails/"+rowdata111;
		 	  $.ajax({
					type :'GET',
					url:"/infy/deleteroomdetails?roomid="+rowdata111+"&roomType="+rmtydt,
					success: function(result) {
						if(result ==='deletedata') {
							//window.location.href = "/infy/deleteroomdetails/"+rowdata111;
							showMassage("Room Record Deleted SucessFully",5000,"red");
							//refreshTable();
							 
							}
						else{
							 showMassage("Error occured while deleting room record,Please contact admin",5000,"red");
						}
					},
			         error:function(xhr,status,error){
			        	 showMassage("Error occured while deleting room record,Please contact admin",5000,"red");
						console.error(error);
			           }
				}); 
			  }
			  
		  });
		}
		else if(value=="edit"){
			// var rmval= confirm("Are you sure to delete this item?");
			 // if(rmval==true) {
					 $('#Hotel').on('change','.deleteclasss',function(){
						 var rowdata=$(this).closest('tr').find('td').map(function(){
							  var fieldname=$(this).data('field-name');
							  var value=$(this).text();
							//  return fieldname +': "'+value+'"';
							  return fieldname +'='+value;
						  }).get();
						  var fieldname1=$(this).closest('tr').find('.rm').data('field-name');
						  var rowdata111=$(this).closest('tr').find('.rm').text();
						  var rowdataid="&"+rowdata111;
						   editEncodingvalue=rowdata.join('&');
						  var Encodingvalue=encodeURIComponent(rowdata.join('&'));
						  rowdata1=rowdata;
						  console.log("Encodingvalue"+editEncodingvalue);
			  $.ajax({
				 	url:'/infy/editroomdetails1?',
					type :'get',
					data:editEncodingvalue,
					success: function(result) {
						console.log("edit  sucess:::::"+result);
						if(result !=null) {
							window.location.href = "/infy/editroomdetails1?"+editEncodingvalue;
							//window.location.href = "EditHotel.jsp";
						}
					
					},
			         error:function(xhr,status,error){
			        	 //showMassage("Error occured while updating room record,Please contact admin",5000,"red");
			        	 showMassage("Error occured While Rendering EditPage,Please Contact Admin",5000,"red");
						console.error(error);
			           }
				}); 
					 });
			 // }
		}
}
function addRoom(){
	window.location.href = "/infy/addCostumer";
	/* var rmval= confirm("do you want to add new Room Data");
	if(rmval==true) {
		window.location.href = "/infy/addCostumer";
	} */
	
}
/* function refreshTable(){
	tabledata.ajax.reload();
} */
/* function showMessage(message,duration){

} */
</script>
<script>
$(document).ready(function () {
	var tabledata=$('#Hotel').DataTable({
		"lengthMenu":[5, 10, 15, 20],
		//"searching": false,
		"columnDefs": [ {
			//"targets":0,
		//	"visible":false
		},
		   {
				"targets":3,"width":"15%"
		   },
		   {
				"targets":4,"width":"10%"
		   },
		   {
				"targets":1,"width":"15%"
		   },
		   {
				"targets":2,"width":"15%"
		   },
		   {
				"targets":5,"width":"15%"
			},
			{
				"targets":6,"width":"15%"
			},
			{
				"targets":7,"width":"15%"
			},
			{
				"targets":8,"width":"15%"
			}
		   ]
	
		});
	
	var dataList=[];	
/* 	$('#fromDateFilter').on('change', function() {
		var fromDate = $('#fromDateFilter').val().trim();
		$('#toDateFilter').attr('min',fromDate);
		$('#Hotel').DataTable().draw();
		});

		$('#toDateFilter').on('change', function() {
		var toDate = $('#toDateFilter').val().trim();
		$('#fromDateFilter').attr('max',toDate);
		$('#Hotel').DataTable().draw();
		}); */
	
	
	
	$.ajax({
		type :'GET',
		url:'/infy/fetchAllRoooms',
		success: function(result) {
          console.log("value addded:::::"+result);
          dataList=result;
          console.log("value addded:::::"+dataList)
       for (var i=0;i<dataList.length; i++) { 
       var roomType = dataList[i]; 
    	  var wifidata='';
    	  var ACAvailable='not avaliable';
    	  var SmokingAvailable='not avaliable';
	 if(roomType.wifiAvailable==true){
		 wifidata+="WIFI,";
	 }
	 if(roomType.tvavailable==true){
		 wifidata+="TV,";
	 }
	 if(roomType.geyserAvailable==true){
		 wifidata+="Geyser,";
	 }
	 if(roomType.isACAvailable){
		 ACAvailable="avaliable";
	 }
	 if(roomType.isSmokingAvailable){
		 SmokingAvailable="avaliable";
	 }
	var checkwifi = wifidata.includes(",", wifidata.length-1);
	var dataresult='';
	if(checkwifi){
	 dataresult = wifidata.substring(0, wifidata.length-1);
	}
      var newrow= tabledata.row.add([
    	   roomType.id,
    	   roomType.roomtypedata,
    	   roomType.roomName,
    	   roomType.price,
    	   roomType.totalRoomAvaliable,
    	   roomType.totalRoomBooked,
    	   ACAvailable,
    	   SmokingAvailable,
    	   dataresult,
    	   '<td><select class="deleteclasss" onchange="editdata(this.value)"><option value="">Select::</option><option value="edit" Style=background:lightblue>Edit</option><option value="delete" Style=background:red>Delete</option></select></td>'
    	   ]).draw(false).node();
      $(newrow).find('td:eq(0)').attr('data-field-name','roomid').addClass('rm');
      $(newrow).find('td:eq(1)').attr('data-field-name','roomtypedata').addClass('rmtydt');
      $(newrow).find('td:eq(2)').attr('data-field-name','price');
      $(newrow).find('td:eq(3)').attr('data-field-name','totalRoomAvaliable').addClass('tra');
      $(newrow).find('td:eq(4)').attr('data-field-name','totalRoomBooked').addClass('trb');
      $(newrow).find('td:eq(5)').attr('data-field-name','acnonac');
      $(newrow).find('td:eq(6)').attr('data-field-name','smoknonsmok');
      $(newrow).find('td:eq(7)').attr('data-field-name','wifitv');
      $(newrow).find('td:eq(8)').attr('data-field-name','action');
       }
		},
         error:function(xhr,status,error){
			console.error(error);
			showMassage("Error occured while fetching room record,Please contact admin",5000,"red");
			
           }
		
	});
	
	
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
		comboBox.append($('<option>').text("Select RoomType...").val(''));
		$.each(data,function(index,value){
			comboBox.append($('<option>').text(value).val(value));
		});
	}
});
</script>
</body>
</html>
