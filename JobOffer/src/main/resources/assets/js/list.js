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
        	// 実行前に検索結果の表示を消去する
        	$("#searchResult").empty();
    	}
	}).done(function(data) {
        window.alert("成功");
        $.each(data, function(i, jobOfferInfo) {
        	$("#searchResult").append("<table>");
        	$("#searchResult").append("<tr>");
        	$("#searchResult").append("<th>業種ID：</th>");
        	$("#searchResult").append("<td>" + jobOfferInfo.industryTypeId + "</td>");
        	$("#searchResult").append("<th>職種ID：</th>");
        	$("#searchResult").append("<td>" + jobOfferInfo.occupationTypeId + "</td>");
        	$("#searchResult").append("</tr>");
        	$("#searchResult").append("</table>");
        });
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
	
}

