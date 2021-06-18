/**
 * 공통 자바 스크립트
 */ 

var reviewManager= ( function(){
	var getAll= function(obj, callback){
		console.log("getAll......."+obj);
		$.getJSON("/reviews/center/"+obj,callback);
	};
	var add= function(obj, callback){
		console.log("add review.......");
		$.ajax({
			url:"/reviews/"+obj["cno"]+"/"+obj["curnum"],
			data:JSON.stringify(obj),
			dataType:"json",
			type:"post",
			contentType:"application/json",
			success: callback
		});
	};
	var update = function(obj, callback){
		console.log("update review.........");
		$.ajax({
			url:"/reviews/"+obj["cno"]+"/"+obj["crNum"]+"/"+obj["curnum"],
			data: JSON.stringify(obj),
			dataType:"json",
			type:"put",
			contentType:"application/json",
			success:callback
			
		});
	};
	var remove= function(obj, callback){
		console.log("remove review.......");
		$.ajax({
			url:"/reviews/"+obj["cno"]+"/"+obj["crNum"],
			type:"delete",
			success: callback
		});
	};
	
	return { "getAll":getAll, 
			 "add": add,
			"update":update,
			"remove":remove
	};
})();