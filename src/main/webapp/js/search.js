$(document).ready(function() {

})

function searchByDevice() {
	var deviceNames = [];
	var countries = [];
	$('.names:checked').each(function(i, e) {
		deviceNames.push($(this).val());
	});
	
	$('.countries:checked').each(function(i, e) {
		countries.push($(this).val());
	});
	
	var data = {};
	data['deviceName'] = deviceNames;
	data['countries'] = countries;
	$.ajax({
		url:"http://localhost:8080/assignment/search",
		contentType: "application/json",
		type:"post",
		data:JSON.stringify(data),
		success: function(data) {
			console.log("Wohooo");
		}
		
	})
	console.log(data);

}

