function search() {

	// 職種ID
	var occupationTypeId = $("#occupationTypeId").val();
	
	// 業種ID
	var industryTypeId = $("#industryTypeId").val();
	
	// フリーワード
	var freeWord = $("#freeWord").val();
	
	var url = "/api/list";
	
	// リクエストメソッドを呼び出す
	Request(url, "GET", occupationTypeId, industryTypeId, freeWord, null, null);
}

function Request(Url, Method, oTypeId, iTypeId, fWord, CallbackFunc, ErrorCallbackFunc) {

	window.alert("リクエストメソッド開始");
	$.ajax({
		url: Url,
		type: Method,
		data: {
			occupationTypeId: oTypeId, 
			industryTypeId: iTypeId, 
			freeWord: fWord
		}, 
		beforeSend: function () {
        	alert("実行前");
    	}
	}).done(function(data) {
        window.alert("成功");
        $("#result").append(data.occupationTypeId);
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
	
}

