/**
 * 
 */

var getManager= ( function(){
	var getArea2= function(obj, callback){
		console.log("getAll......."+obj);
		$.getJSON("/center/getArea2/"+obj,callback);
	};
	var getByArea1= function(obj, callback){
		$.getJSON("/center/getByArea1/"+obj,callback);
	};
	var getByArea2= function(obj, callback){
		$.getJSON("/center/getByArea2/"+obj,callback);
	};
	
	return { "getArea2":getArea2,
		"getByArea1":getByArea1,
		"getByArea2":getByArea2 };
})();