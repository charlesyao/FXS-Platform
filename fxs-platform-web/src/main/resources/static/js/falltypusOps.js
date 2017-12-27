$(function() {
	$(".deleteFalltypusLink").on("click", function(e) {
		e.preventDefault()
		var currentFalltypusId = $(this).attr("id").split("_")[0];
		
		$.ajax({
			type : 'DELETE',
			url : '/admin/falltypus/delete/' + currentFalltypusId,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#modalContent").empty().append("删除成功")
				$("#notificationModal").modal("show")
			},
			error : function(error) {
				$("#modalContent").empty().append("删除失败")
				$("#notificationModal").modal("show")
			}
		});
	})
	
	$("#notificationModal").on('hide.bs.modal', function() {
		location.reload();
	})
})
