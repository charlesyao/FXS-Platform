$(document).ready(function() {
	$("#userRegister").on("click", function(e) {
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '/user',
			data : JSON.stringify(formdata),
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {
				console.log(data)
			},
			error : function(error) {
				console.log(error)
			}
		});
	})
});
