console.log("Reply Module............");

var replyService = (function(){
	//댓글 추가
	function add(reply, callback){
	
		console.log("add reply............");
		
		$.ajax({
		type : 'post',
		url : '/replies/new',
		data : JSON.stringify(reply),
		contentType : "application/json; charset=utf-8",
		success : function(result,status,xhr){
		if(callback){
			callback(result);
		}	
	},
	error : function(xhr, status, er){
	if(error){
		error(er);
			}
		}
	 })
	}
	
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/"+ bno + "/" + page + ".json",
		function(data){
			if(calback){
				callback(data);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error(err);
			}
		});
	}
	return{add:add,
		   getList:getList};
})();