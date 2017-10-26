$(document).ready(function() {
	$("#submit").on("click", function(e) {
		event.preventDefault();
		fire_ajax_submit();
	})
});

function fire_ajax_submit() {
	var formdata = {}
	formdata["username"] = $("#username").val();
	formdata["password"] = $("#password").val();
	formdata["mobile"] = $("#mobile").val();
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '/user/collectformdata',
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
}