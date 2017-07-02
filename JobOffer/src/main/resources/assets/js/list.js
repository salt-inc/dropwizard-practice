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
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.corporation.corporationId + "</td>");
        	$(".jobOfferTable").append("<th>企業名：</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.corporation.corporationName + "</td>");
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

function showRegisterDialog(category) {

	if (category == "jobOffer") {
	
		var url = "/api/v1/job/useJobOfferRegister/industry";
		
		getIndustry(url, "POST");
		
		url = "/api/v1/job/useJobOfferRegister/occupation";
		
		getOccupation(url, "POST");
		
		$("#jobOfferRegister").dialog({
			
		});
		
	} else if (category == "corporation") {
		$("#corporationRegister").dialog({
			
		});
	}
	
}

function jobOfferRegisterStart() {
	
	// 求人ID
	var jobOfferId = $("#jobOfferIdRegister").val();
	// 求人名
	var jobOfferName = $("#jobOfferNameRegister").val();
	// 企業ID
	var corporationId = $("#jobOffer_corporationIdRegister").val();
	// 業種ID
	var industryTypeId = $("#industryTypeIdRegister").val();
	// 職種ID
	var occupationTypeId = $("#occupationTypeIdRegister").val();
	// キャッチコピー
	var catchCopy = $("#catchCopyRegister").val();
	// 概要
	var jobOfferOverview = $("#jobOfferOverviewRegister").val();
	
	var url = "/api/v1/job";
	
	// 登録メソッドを呼び出す
	jobOfferRegister(url, "POST", jobOfferId, jobOfferName, corporationId, 
		industryTypeId, occupationTypeId, catchCopy, jobOfferOverview);
	
}

function jobOfferRegister(Url, Method, jOfferId, jOfferName, cId, 
	iTypeId, oTypeId, cCopy, jOfferOverview) {
	$.ajax({
		url: Url,
		type: Method,
		data: {
			jobOfferId: jOfferId, 
			jobOfferName: jOfferName, 
			corporationId: cId, 
			industryTypeId: iTypeId, 
			occupationTypeId: oTypeId, 
			catchCopy: cCopy, 
			jobOfferOverview: jOfferOverview
		}
	}).done(function(data) {
        window.alert("成功");
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
}

function corporationRegisterStart() {
	window.alert("登録開始！");
	
	// 企業ID
	var corporationId = $("#corporationIdRegister").val();
	
	// 企業名
	var corporationName = $("#corporationNameRegister").val();
	
	var url = "/api/v1/job/corporation";
	
	// 登録メソッドを呼び出す
	corporationRegister(url, "POST", corporationId, corporationName);
	
}

function corporationRegister(Url, Method, cTypeId, cTypeName) {
	$.ajax({
		url: Url,
		type: Method,
		data: {
			corporationId: cTypeId, 
			corporationName: cTypeName 
		} 
	}).done(function(data) {
        window.alert("成功");
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
}

function getIndustry(Url, Method) {
	$.ajax({
		url: Url,
		type: Method,
		data: {
			id : Method
		} 
	}).done(function(data) {
        $.each(data, function(i, industryInfo) {
        	console.log(JSON.stringify(industryInfo));
        	$("#industryTypeIdRegister").append("<option value=" + industryInfo.industryTypeId + ">" + industryInfo.industryTypeName + "</option>");
        });
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
}

function getOccupation(Url, Method) {
	$.ajax({
		url: Url,
		type: Method,
		data: {
			id : Method
		} 
	}).done(function(data) {
        $.each(data, function(i, occupationInfo) {
        	console.log(JSON.stringify(occupationInfo));
        	$("#occupationTypeIdRegister").append("<option value=" + occupationInfo.occupationTypeId + ">" + occupationInfo.occupationTypeName + "</option>");
        });
        
	}).fail(function(data) {
        window.alert("失敗");
        
	});
}
