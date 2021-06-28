/**
 * 공통 자바 스크립트
 */ 

var centerManager= ( function(){
	var getAll= function(obj, callback){
		console.log("getAll......."+obj);
		$.getJSON("/center/"+obj,callback);
	};
	
	var add= function(obj, callback){
		console.log("add center.......");
		$.ajax({
			url:"/center/insertCenter",
			data:JSON.stringify(obj),
			dataType:"json",
			type:"post",
			contentType:"application/json",
			success: callback
		});
	};
	
	var update = function(obj, callback){
		console.log("update center.........");
		console.log(obj);
		$.ajax({
			url:"/center/updateCenter/"+obj["cnum"],
			data: JSON.stringify(obj),
			dataType:"json",
			type:"put",
			contentType:"application/json",
			success:callback
		});
	};
	
		var remove= function(obj, callback){
		console.log("remove center.......");
		$.ajax({
			url:"/center/deleteCenter/"+obj["cnum"],
			type:"delete",
			success: callback
		});
	};
	
	return { "getAll":getAll, 
			 "add": add,
			"update":update,
			"remove":remove };
})();