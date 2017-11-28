$(function() {
	//标记解决纠纷
	$("#resolveDispute").on("click", function() {
		$.ajax({
			type : 'PUT',
			url : '/user/case/update/' + [[${caseDetailInfo.id}]],
			data : JSON.stringify({
				"status": "5"
			}),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#modalContent").empty().append("更新成功")
				$("#notificationModal").modal("show")
			},
			error : function(error) {
				$("#modalContent").empty().append("更新失败")
				$("#notificationModal").modal("show")
			}
		});
	})
	//对案件进行追问
	$("#saveDetailedInquiry").on("click", function() {
		$.ajax({
			type : 'PUT',
			url : '/user/case/addDetailedInquiry/' + [[${caseDetailInfo.id}]],
			data : JSON.stringify({"detailedInquirys": $("#detailedInquiryContent").val()}),
			contentType : "application/json; charset=utf-8",
			success : function(res) {
				$("#addDetailedInquiry").modal("hide")
				$("#detailedInquiryContent").val("")
				if (res.data == 0) {
					$("#modalContent").empty().append("追问信息添加成功")
				} else if (res.data == -1) {
					$("#modalContent").empty().append("追问次数已经达到最大，不能再添加追问信息")
				}
				
				$("#notificationModal").modal("show")
			},
			error : function(error) {
				$("#addDetailedInquiry").modal("hide")
				$("#detailedInquiryContent").val("")
				$("#modalContent").empty().append("追问信息添加失败")
				$("#notificationModal").modal("show")
			}
		});
	})
	
	$('#notificationModal').on('hidden.bs.modal', function () {
		window.location.assign(location.href);
	})
})