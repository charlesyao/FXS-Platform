$(function() {
	$('.ui-choose').ui_choose();
	var selectedCity = ''
	var selectedProvince = ''
	//加载问题-答案
	$("#disputeInfo").on("click", 'li', function(e) {
		e.preventDefault()
		var _this = this

		$.ajax({
			type : 'GET',
			url : '/public/question/answer/' + $(_this).attr("id"),
			success : function(res) {
				$("#question_" + res.message).remove();
				if ($(_this).attr("isroot") === 'Y') {
					$("#nextQuestionContainer").empty()
				} else {
					$(_this).parent().parent().parent().nextAll().remove()
				}
				
				console.log(res.data)
				$("#nextQuestionContainer").append(res.data)
			},
			error : function(error) {
			}
		});
	})
	
	$("#nextQuestionContainer").on("click", "li", function() {
		var _this = this
		$(this).parent().find("li").removeClass("selected")
		$(this).addClass("selected")
	})
	//加载城市列表
	$("#getCity").on("click", function(e) {
		$.ajax({
			type : 'GET',
			url : '/city',
			success : function(res) {
				$("#level1City").empty().append(res.data)
			},
			error : function(error) {
			}
		});
	})
	//选择一级城市时加载对应的二级城市列表
	$("#level1City").on("click", 'li', function(e) {
		e.preventDefault()
		selectedCity = $(this)[0].innerText
		$.ajax({
			type : 'GET',
			url : '/city/' + $(this).attr("id"),
			success : function(res) {
				$("#level2City").empty().append(res.data)
			},
			error : function(error) {
			}
		});
	})
	//选择二级城市并保存到当前的session中以便在保存案件的时候一起保存
	$("#level2City").on("click", 'li', function(e) {
		e.preventDefault()
		selectedProvince = $(this)[0].innerText
		$.ajax({
			type : 'GET',
			url : '/city/autosave/' + $(this).attr("id"),
			success : function(res) {
			},
			error : function(error) {
			}
		});
	})
	$("#confirmSelectedCity").on("click", function() {
		$("#selectedCity").empty().text(selectedCity + "-" +selectedProvince)
		$('#selectCity').modal("hide")
	})
	//清空城市列表
	$('#selectCity').on('hidden.bs.modal', function() {
		$("#level1City").empty();
		$("#level2City").empty();
	})

    $(".deleteQuestionLink").on("click", function(e) {
        e.preventDefault()
        var questionInfoId = $(this).attr("id");
        $.ajax({
            type : 'DELETE',
            url : '/disputeInfo/question/delete/' + questionInfoId,
            contentType : "application/json; charset=utf-8",
            success : function(data) {
                location.reload();
            },
            error : function(error) {
                $("#modalContent").empty().append("删除失败")
                $("#notificationModal").modal("show")
            }
        });
    })
})
