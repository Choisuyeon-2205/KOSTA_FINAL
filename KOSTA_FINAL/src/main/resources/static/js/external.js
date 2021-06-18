/**
 * 공통 자바 스크립트
 */ 

var replyManager= ( function(){
	var register = function(obj, callback){
		console.log("register reply.........");
		console.log(obj);
		$.ajax({
			url:"/center/registerCurriculum/"+obj["cnum"],
			// data: JSON.stringify(obj),
			// dataType:"json",
			type:"post",
			// contentType:"application/json",
			success:callback
		});
	};
	
	return { "register":register };
})();