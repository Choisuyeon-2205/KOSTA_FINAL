/**
 * 공통 자바 스크립트
 */ 

var trainerManager= ( function(){
	var getAll= function(obj, callback){
		console.log("getAll......."+obj);
		$.getJSON("/trainer/"+obj,callback);
	};
	
	var add= function(obj, callback){
		console.log("add trainer.......");
		$.ajax({
			url:"/trainer/insertTrainer/"+obj["cnum"]+"/"+obj["exerciseTypeNum"],
			data:JSON.stringify(obj),
			dataType:"json",
			type:"post",
			contentType:"application/json",
			success: callback
		});
	};
	
	var update = function(obj, callback){
		console.log("update trainer.........");
		console.log(obj);
		$.ajax({
			url:"/trainer/updateTrainer/"+obj["cnum"]+"/"+obj["exerciseTypeNum"]+"/"+obj["tnum"],
			data: JSON.stringify(obj),
			dataType:"json",
			type:"put",
			contentType:"application/json",
			success:callback
		});
	};
	
		var remove= function(obj, callback){
		console.log("remove trainer.......");
		$.ajax({
			url:"/trainer/deleteTrainer/"+obj["cnum"]+"/"+obj["tnum"],
			type:"delete",
			success: callback
		});
	};
	
	return { "getAll":getAll, 
			 "add": add,
			"update":update,
			"remove":remove };
})();