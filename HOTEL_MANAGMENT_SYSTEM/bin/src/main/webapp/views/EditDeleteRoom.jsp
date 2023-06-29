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
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/> 
 <style>
 td,th{
	 text-align:center
	 }
	 td:nth-child(1){
		 display:none
	 }
 </style>
 </head>
<body>
<h1>HOTEL MANAGMENT SYSTEM</h1>
<table id="Hotel" class="table table-bordered table-striped"style="width: 100%">
<thead>
	<tr>
		<th>Room ID</th>
		<th>Room Type</th>
		<th>AC / NON-AC</th>
		<th>Smoking / Non-Smoking</th>
		<th>WIFI/ TV / Geyser</th>
		<th>Action</th>
		
	</tr>
</thead>
<tbody>

</tbody>
</table>

<button  type="submit"  onclick="addRoom()" Style=background:blue>Add Room</button>
<script>
function editdata(){
	var rmval= confirm("Are YOU SURE ,EDIT THE ROOM DATA");
	//window.location.href = "/infy/fetchAllRoooms";
}
function addRoom(){
	var rmval= confirm("do you want to add new Room Data");
	if(rmval==true) {
	window.location.href = "/infy/addCostumer";
	}
}
</script>
<script>
$(document).ready(function () {
	$('#Hotel').DataTable({
		"columnDefs": [ {
			"targets":0,
			"visible":false,
			searchable: false
		},
		   {
			"targets":3,"width":"20%"
		   },
		   {
			"targets":4,"width":"20%"
		   },
		   {
			"targets":1,"width":"20%"
		   },
		   {
			"targets":2,"width":"20%"
		   }
		   ]
		});
	var dataList=[];
	$.ajax({
		type :'GET',
		url:'/infy/fetchAllRoooms',
		success: function(result) {
          console.log("value addded:::::"+result);
          dataList=result;
          console.log("value addded:::::"+dataList)
       for (var i=0;i<dataList.length; i++) { 
       var roomType = dataList[i]; 
       console.log(roomType.price);
       var AC=roomType.isACAvailable;
       var SMOK=roomType.isSmokingAvailable;
       var am=JSON.parse(roomType.amenties);
       var wifi=am.checkboxes;
       console.log("wifiiii"+wifi);
       var roomtypedata=roomType.roomtypedata;
       var iddata=roomType.id;
       var row=$('<tr>');
       row.append($('<td data-field-name="roomid" class="rm">').text(iddata));
       row.append($('<td data-field-name="roomtypedata">').text(roomtypedata));
       row.append($('<td data-field-name="acnonac">').text(AC));
       row.append($('<td data-field-name="smoknonsmok">').text(SMOK));
       row.append($('<td data-field-name="wifitv">').text(wifi));
       row.append($('<td data-field-name="action"><button  type="submit"  Style=background:lightblue>Edit</button><button  type="submit" class="deleteclass"  Style=background:red>Delete</button></td>'));
       $('#Hotel tbody').append(row);
       }
		},
         error:function(xhr,status,error){
			console.error(error);
           }
	});
	
	  $('#Hotel').on('click','.deleteclass',function(){
		  var rowdata=$(this).closest('tr').find('td').map(function(){
			  var fieldname=$(this).data('field-name');
			  var value=$(this).text();
			  return fieldname +'='+value;
		  }).get();
		  var fieldname1=$(this).closest('tr').find('.rm').data('field-name');
		  var rowdata111=$(this).closest('tr').find('.rm').text();
		  var rowdataid="&"+rowdata111;
		  var Encodingvalue1=rowdata.join('&');
		  var Encodingvalue=encodeURIComponent(rowdata.join('&'));
		  console.log("Encodingvalue"+Encodingvalue1);
		  var rmval= confirm("Are YOU SURE ,Delete THE ROOM DATA  ");
		  if(rmval==true) {
		  $.ajax({
				type :'GET',
				url:'/infy/deleteroomdetails',
				success: function(result) {
					if(result !=null) {
						window.location.href = "/infy/deleteroomdetails/"+rowdata111;
					}
				},
		         error:function(xhr,status,error){
					console.error(error);
		           }
			});
		  }
		  
	  });
});
</script>
</body>
</html>
