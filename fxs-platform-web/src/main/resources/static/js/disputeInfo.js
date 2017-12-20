$(function() {
	var selectedCity = ''
	//加载问题-答案
	$("#disputeInfo").on("click", 'ul', function(e) {
		e.preventDefault()
		var _this = this

		$(_this).children().css("background-color", "green")
		$.ajax({
			type : 'GET',
			url : '/public/question/answer/' + $(_this).attr("id"),
			success : function(res) {
				$("#question_" + res.message).remove();
				if ($(_this).attr("isroot") === 'Y') {
					$("#nextQuestionContainer").empty()
				} else {
					$(_this).parent().parent().nextAll().remove()
				}
				$("#nextQuestionContainer").append(res.data)
			},
			error : function(error) {
			}
		});
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
		selectedCity = $(this).children()[0].innerText
		$(this).parent().children().removeClass("cur")
		$(this).addClass("cur")
		$.ajax({
			type : 'GET',
			url : '/city/' + $(this).attr("id"),
			success : function(res) {
				$("#level2City").empty().append(res.data)
				$("#selectedCity").empty().append(selectedCity)
			},
			error : function(error) {
			}
		});
	})
	//选择二级城市并保存到当前的session中以便在保存案件的时候一起保存
	$("#level2City").on("click", 'li', function(e) {
		e.preventDefault()
		selectedCity += $(this).children()[0].innerText
		$(this).parent().children().removeClass("cur")
		$(this).addClass("cur")
		$.ajax({
			type : 'GET',
			url : '/city/autosave/' + $(this).attr("id"),
			success : function(res) {
				$("#selectedCity").empty().append(selectedCity)
			},
			error : function(error) {
			}
		});
	})
	//清空城市列表
	$('#selectCity').on('hidden.bs.modal', function() {
		$("#level1City").empty();
		$("#level2City").empty();
	})
})
