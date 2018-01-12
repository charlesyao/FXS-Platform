$(function() {
	$("#optionalQAForm div").first().css('display','block');
	
	$(".answer-quiz").on("click",".will-answer,.cannot-answer", function () {
       	var showQuiz = $(".quiz-wrap .quiz:visible");
       	showQuiz.find("li").removeClass("selected");
       	toNext();
      	});
	
	$(".answer-quiz .to-right").on("click",function () {
         var showQuiz = $(".quiz-wrap .quiz:visible");
         if ($(this).hasClass("disabled") || showQuiz.find("li.selected").length == 0) {
           return
         }
         toNext();
      })
   $(".answer-quiz .to-left").on("click",function () {
      var showQuiz = $(".quiz-wrap .quiz:visible");
        if ($(this).hasClass("disabled")) {
          return
        }
       if (showQuiz.prev(".quiz").length > 0) {
        showQuiz.prev(".quiz").show();
        $(".answer-quiz .submit").hide();
        $(".answer-quiz .to-right").removeClass("disabled");
        showQuiz.hide();
       }
       
       if (showQuiz.prev(".quiz").prev(".quiz").length == 0) {
        $(".answer-quiz .to-left").addClass("disabled");
       }else{
        $(".answer-quiz .to-left").removeClass("disabled");
       }
     })
	function toNext() {
	     var showQuiz = $(".quiz-wrap .quiz:visible");
         if (showQuiz.next(".quiz").length > 0) {
          showQuiz.next(".quiz").show();
          $(".answer-quiz .to-left").removeClass("disabled");
          showQuiz.hide();
         }
         if (showQuiz.next(".quiz").next(".quiz").length == 0) {
          $(".answer-quiz .to-right").addClass("disabled");
          $(".answer-quiz .submit").show();
         }else{
          $(".answer-quiz .to-right").removeClass("disabled");
         }
      }
	$("#optionalQAForm").on("click", 'li', function(e) {
		e.preventDefault()
		
		if ($(this).hasClass("cur")) {
			return;
		}
		if ($(this).attr("data-type") == 'single') {
			$(this).parent().children().removeClass("cur")
			$(this).addClass("cur")
		} else if ($(this).attr("data-type") == 'multi') {
			$(this).addClass("cur")
		}
		
		if ($(this).attr('id') === 'addAfterConfirm') {
			e.preventDefault()
			$(this).parent().prev().children().removeClass("cur")
			$(this).parent().children().removeClass("cur")
			$(this).addClass("cur")
			$.ajax({
				type : 'POST',
				url : '/public/question/optional/other/answer',
				data : JSON.stringify({
					"other": "确认后补充",
					"questionId": $(this).parent().parent().prev().attr("id")
				}),
				contentType : "application/json; charset=utf-8",
				success : function(res) {
				},
				error : function(error) {
				}
			});
		} else if ($(this).attr('id') === 'noAnswer') {
			e.preventDefault()
			$(this).parent().prev().children().removeClass("cur")
			$(this).parent().children().removeClass("cur")
			$(this).addClass("cur")
			$.ajax({
				type : 'POST',
				url : '/public/question/optional/other/answer',
				data : JSON.stringify({
					"other": "无法回答",
					"questionId": $(this).parent().parent().prev().attr("id")
				}),
				contentType : "application/json; charset=utf-8",
				success : function(res) {
				},
				error : function(error) {
				}
			});
		} else {
			$.ajax({
				type : 'GET',
				url : '/public/question/optional/' + $(this).attr("data-type") + '/answer/' + $(this).attr("id"),
				success : function(res) {
					
				},
				error : function(error) {
				}
			});
		}
	})
	
$("#submitLawsuit").on("click", (e) => {
	e.preventDefault()
	$.ajax({
		type : 'POST',
		url : '/public/case',
		data : JSON.stringify({
			"caseType": "1",
			"comments": $("#freeComments").val()
		}),
		contentType : "application/json; charset=utf-8",
		success : function(res) {
			window.location.assign(res.data);
		},
		error : function(error) {
		}
	});
})
})