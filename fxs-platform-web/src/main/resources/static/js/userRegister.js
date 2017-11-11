$(document).ready(function() {
	$("#userRegister").on("click", function(e) {
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '/user',
			data : JSON.stringify({
				"username": $("#username").val(),
				"email": $("#email").val(),
				"mobile": $("#mobile").val(),
				"password": $("#password").val()
			}),
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
