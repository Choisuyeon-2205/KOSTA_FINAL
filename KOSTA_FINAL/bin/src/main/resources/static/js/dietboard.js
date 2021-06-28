
/**
 * 공통자바스크립트
 */

var replyManager = (function(){
	var getAll = function(obj, callback){
		console.log("getAll......." + obj);
		//$.getJSON은 AJAX의 하나의 방법이다.
		$.getJSON("/replies/dboard/"+ obj, callback)
		
		
	};
	var add = function(obj, callback){
		console.log("add reply.......");
		$.ajax({
			url:"/replies/" + obj["diaryNum"],
			data: JSON.stringify(obj),
			dataType:"json",
			type:"post",
			contentType:"application/json",
			success:callback	
		});
	};
	
	var remove = function(obj, callback){
		console.log("remove reply.........");
		$.ajax({
			url:"/replies/" + obj["diaryNum"] + "/" + obj["diaryRplNum"],
			type:"delete",
			success:callback
		});
	};

	return { "getAll":getAll,
			 "add":add,
			 "remove" : remove
	  };
})();



