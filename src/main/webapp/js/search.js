$(document).ready(function() {

})

function searchByDevice() {
	var ids = [];
	$('.names:checked').each(function(i, e) {
		ids.push($(this).val());
	});
	
	var data = {};
	data['deviceName'] = ids;
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

