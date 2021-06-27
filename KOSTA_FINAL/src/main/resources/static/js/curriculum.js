/**
 * 공통 자바 스크립트
 */ 

var curriculumManager= ( function(){
	var getAll= function(obj, callback){
		console.log("getAll......."+obj);
		$.getJSON("/curriculum/"+obj,callback);
	};
	
	var add= function(obj, callback){
		console.log("add curriculum.......");
		$.ajax({
			url:"/curriculum/insertCurriculum/"+obj["cnum"]+"/"+obj["exerciseTypeNum"]+"/"+obj["trainerNum"],
			data:JSON.stringify(obj),
			dataType:"json",
			type:"post",
			contentType:"application/json",
			success: callback
		});
	};
	
	var update = function(obj, callback){
		console.log("update curriculum.........");
		console.log(obj);
		$.ajax({
			url:"/curriculum/updateCurriculum"+"/"+obj["curnum"]+"/"+obj["cnum"]+"/"+obj["exerciseTypeNum"]+"/"+obj["trainerNum"],
			data: JSON.stringify(obj),
			dataType:"json",
			type:"put",
			contentType:"application/json",
			success:callback
		});
	};
	
		var remove= function(obj, callback){
		console.log("remove curriculum.......");
		$.ajax({
			url:"/curriculum/deleteCurriculum/"+obj["cnum"]+"/"+obj["curnum"],
			type:"delete",
			success: callback
		});
	};
	
	return { "getAll":getAll, 
			 "add": add,
			"update":update,
			"remove":remove };
})();