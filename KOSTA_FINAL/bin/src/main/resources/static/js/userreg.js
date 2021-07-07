/**
 * 공통 자바 스크립트
 */ 

var curregManager= ( function(){
	var getAll= function(obj, callback){
		console.log("getAll......."+obj);
		$.getJSON("/curreg/"+obj,callback);
	};
	
	var getByCurnum= function(obj, callback){
		console.log("getByCurnum......."+obj);
		$.getJSON("/curreg/getByCurnum/"+obj,callback);
	};
	
	var remove= function(obj, callback){
	console.log("remove .......");
		$.ajax({
			url:"/curreg/deleteCurreg/"+obj["cnum"]+"/"+obj["curnum"]+"/"+obj["userid"],
			type:"delete",
			success: callback
		});
	};
	
	return { "getAll":getAll, 
			"remove":remove,
			"getByCurnum":getByCurnum };
})();