function search() {

	// 職種ID
	var occupationTypeId = $("#occupationTypeId").val();
	
	// 業種ID
	var industryTypeId = $("#industryTypeId").val();
	
	// フリーワード
	var freeWord = $("#freeWord").val();
	
	var url = "/api/v1/job/list";
	
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
        	$(".jobOfferTable").empty();
    	}
	}).done(function(data) {
        window.alert("成功");
        $.each(data, function(i, jobOfferInfo) {
        	console.log(JSON.stringify(jobOfferInfo));
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>求人ID：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.jobOfferId + "</td>");
        	$(".jobOfferTable").append("<th>求人名：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.jobOfferName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>企業ID：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.company.companyId + "</td>");
        	$(".jobOfferTable").append("<th>企業名：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.company.companyName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>業種ID：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.industryType.industryTypeId + "</td>");
        	$(".jobOfferTable").append("<th>業種：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.industryType.industryTypeName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>職種ID：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.occupationType.occupationTypeId + "</td>");
        	$(".jobOfferTable").append("<th>職種：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.occupationType.occupationTypeName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>キャッチコピー：</th>");
        	$(".jobOfferTable").append("<td colspan=3>" + jobOfferInfo.catchCopy + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>概要：</th>");
        	$(".jobOfferTable").append("<td colspan=3>" + jobOfferInfo.jobOfferOverview + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        });
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
	
	$('.jobOfferTable').addClass('jobOfferTable');
	
}

function showRegisterDialog() {
	$("#industryInfoRegister").dialog({
		
	});
}

function registerStart() {
	window.alert("登録開始！");
	
	// 業種ID
	var industryTypeId = $("#industryTypeIdRegister").val();
	
	// 職種名
	var industryTypeName = $("#industryTypeNameRegister").val();
	
	var url = "/api/v1/job";
	
	// 登録メソッドを呼び出す
	register(url, "POST", industryTypeId, industryTypeName);
	
}

function register(Url, Method, iTypeId, iTypeName) {
	$.ajax({
		url: Url,
		type: Method,
		data: {
			industryTypeId: iTypeId, 
			industryTypeName: iTypeName 
		} 
	}).done(function(data) {
        window.alert("成功");
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
}

