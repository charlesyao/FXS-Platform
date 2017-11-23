$(document).ready(function() {
	$("#userRegister").on("click", function() {
		$.ajax({
			type : 'POST',
			url : '/user',
			data : JSON.stringify(
					{
						"name": type
					}
			),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#modalContent").empty().append("案件类型添加成功")
				$("#notificationModal").modal("show")
			},
			error : function(error) {
				$("#modalContent").empty().append("案件类型添加失败")
				$("#notificationModal").modal("show")
			}
		});
	})
	
	$('#notificationModal').on('hidden.bs.modal', function () {
		$("#parentType").val("")
	})
});