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
 td,th{text-align:center}
 </style>
 </head>
 
<body>
<h1>HOTEL MANAGMENT SYSTEM</h1>

<table id="Hotel" class="table table-bordered table-striped"style="width: 100%">
<thead>
	<tr>
		<th>Room Type</th>
		<th>Price</th>
		<th>Action</th>
	</tr>
</thead>
<tbody>

</tbody>
</table>
<script>
function fun(){
	window.location.href = "/infy/UpdatePage";
}
</script>
<script>
$(document).ready(function () {
	var d1=$('#Hotel').DataTable(); 
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
       var pricedata=roomType.price;
       var roomtypedata=roomType.roomtypedata;
       d1.row.add([
    	   roomType.price,
    	   roomType.roomtypedata,
    	   '<td id="add"><button  type="submit" onclick="fun()" Style=background:blue>Edit / Delete</button></td>'
    	   ]).draw();
/*        var row=$('<tr>');
       row.append($('<td>').text(roomtypedata));
       row.append($('<td>').text(pricedata));
       row.append($('<td id="add"><button  type="submit" onclick="fun()" Style=background:blue>Edit / Delete</button></td>'));
       $('#Hotel tbody').append(row); */
       }
		},
         error:function(xhr,status,error){
			console.error(error);
           }
		
	});
	/*var data= ${PriceType};
	/var x=data.replace('RoomPriceTypeDto','');
	console.log("datattttata"+x);*/
	  
});
</script>
</body>
</html>
