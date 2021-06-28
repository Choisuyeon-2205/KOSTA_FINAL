/**
 * 공통 자바 스크립트
 */ 

var curregManager= ( function(){
	var getAll= function(obj, callback){
		console.log("getAll......."+obj);
		$.getJSON("/curreg/"+obj,callback);
	};
	
		var remove= function(obj, callback){
		console.log("remove .......");
		$.ajax({
			url:"/curreg/deleteCurreg/"+obj["curnum"]+obj["userid"],
			type:"delete",
			success: callback
		});
	};
	
	return { "getAll":getAll, 
			"remove":remove };
})();