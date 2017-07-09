// 検索処理メソッド
function search() {

	// 職種ID
	var occupationTypeId = $("#occupationTypeId").val();
	// 業種ID
	var industryTypeId = $("#industryTypeId").val();
	// フリーワード
	var freeWord = $("#freeWord").val();
	
	// 検索処理用のurlを設定
	var url = "/api/v1/job/list";
	
	// 検索実行メソッドを呼び出す
	searchRun(url, "GET", occupationTypeId, industryTypeId, freeWord, null, null);
}

// 検索実行メソッド
function searchRun(Url, Method, oTypeId, iTypeId, fWord, CallbackFunc, ErrorCallbackFunc) {

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
	
		// 取得できたjsonの要素数分繰り返す
        $.each(data, function(i, jobOfferInfo) {
        	
        	// 検索結果表示用のテーブルに、取得したjsonの要素を設定する
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>求人ID</th>");
        	$(".jobOfferTable").append("<td class=leftTdCell>" + jobOfferInfo.jobOfferId + "</td>");
        	$(".jobOfferTable").append("<th>求人名</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.jobOfferName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>企業ID</th>");
        	$(".jobOfferTable").append("<td class=leftTdCell>" + jobOfferInfo.corporation.corporationId + "</td>");
        	$(".jobOfferTable").append("<th>企業名</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.corporation.corporationName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>業種ID</th>");
        	$(".jobOfferTable").append("<td class=leftTdCell>" + jobOfferInfo.industryType.industryTypeId + "</td>");
        	$(".jobOfferTable").append("<th>業種</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.industryType.industryTypeName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>職種ID</th>");
        	$(".jobOfferTable").append("<td class=leftTdCell>" + jobOfferInfo.occupationType.occupationTypeId + "</td>");
        	$(".jobOfferTable").append("<th>職種</th>");
        	$(".jobOfferTable").append("<td>" + jobOfferInfo.occupationType.occupationTypeName + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>キャッチコピー</th>");
        	$(".jobOfferTable").append("<td colspan=3>" + jobOfferInfo.catchCopy + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	var overview = jobOfferInfo.jobOfferOverview;
        	overview = overview.replace(/\n/g, "<br>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<th>概要</th>");
        	$(".jobOfferTable").append("<td colspan=3>" + overview + "</td>");
        	$(".jobOfferTable").append("</tr>");
        	
        	$(".jobOfferTable").append("<tr>");
        	$(".jobOfferTable").append("<td colspan=4> =========================================== </td>");
        	$(".jobOfferTable").append("</tr>");
        	
        });
        
	}).fail(function(data) {
        
	});
	
	// cssの適用
	$('.jobOfferTable').addClass('jobOfferTable');
	
}

// 登録内容の入力欄ダイアログを表示する
function showRegisterDialog(category) {

	// 求人情報登録の入力欄を表示する場合
	if (category == "jobOffer") {
	
		// 企業情報を取得し、プルダウンに設定する
		var url = "/api/v1/job/useJobOfferRegister/corporation";
		getCorporation(url, "POST")
	
		// 業種情報を取得し、プルダウンに設定する
		url = "/api/v1/job/useJobOfferRegister/industry";
		getIndustry(url, "POST");
		
		// 職種情報を取得し、プルダウンに設定する
		url = "/api/v1/job/useJobOfferRegister/occupation";
		getOccupation(url, "POST");
		
		// 求人情報登録ダイアログを表示
		$("#jobOfferRegister").dialog({
			width:450, 
			height:500
		});
		
	// 企業情報登録の入力欄を表示する場合
	} else if (category == "corporation") {
		
		// 企業情報登録ダイアログを表示
		$("#corporationRegister").dialog({
			width:350, 
			height:250
		});
	}
	
}

// 求人情報登録開始メソッド
function jobOfferRegisterStart() {

	// ダイアログを閉じる
	$("#jobOfferRegister").dialog('destroy');
	
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
	
	// 求人情報登録処理用のurlを設定
	var url = "/api/v1/job";
	
	// 求人情報登録メソッドを呼び出す
	jobOfferRegister(url, "POST", jobOfferId, jobOfferName, corporationId, 
		industryTypeId, occupationTypeId, catchCopy, jobOfferOverview);
	
}

// 求人情報登録処理メソッド
function jobOfferRegister(Url, Method, jOfferId, jOfferName, cId, 
	iTypeId, oTypeId, cCopy, jOfferOverview) {
	
	var JsonData = {
		jobOfferId: jOfferId, 
		jobOfferName: jOfferName, 
		corporationId: cId, 
		industryTypeId: iTypeId, 
		occupationTypeId: oTypeId, 
		catchCopy: cCopy, 
		jobOfferOverview: jOfferOverview
	};
	
	$.ajax({
		url: Url,
		type: Method,
		dataType: 'text',
		contentType:'application/json',
		data: JSON.stringify(JsonData)
	}).done(function(data) {
        window.alert("成功\n求人ID：" + data);
        
        // 入力項目欄の記載を削除
        $("#jobOfferIdRegister").val("");
        $("#jobOfferNameRegister").val("");
		$("#jobOffer_corporationIdRegister").val("");
		$("#catchCopyRegister").val("");
		$("#jobOfferOverviewRegister").val("");
        
	}).fail(function(data) {
		window.alert("求人情報の登録に失敗しました。\n原因：\n" + data.responseText);
	});
}

// 企業情報登録開始メソッド
function corporationRegisterStart() {

	// ダイアログを閉じる
	$("#corporationRegister").dialog('destroy');
	
	// 企業ID
	var corporationId = $("#corporationIdRegister").val();
	// 企業名
	var corporationName = $("#corporationNameRegister").val();
	
	// 企業情報登録用のurlを設定
	var url = "/api/v1/job/corporation";
	
	// 企業情報登録メソッドを呼び出す
	corporationRegister(url, "POST", corporationId, corporationName);
	
}

// 企業情報登録処理メソッド
function corporationRegister(Url, Method, cTypeId, cTypeName) {

	var JsonData = {
        corporationId: cTypeId, 
        corporationName: cTypeName  
    };
    
   	$.ajax({
		url: Url,
		type: Method,
		dataType: 'text',
		contentType:'application/json',
		data: JSON.stringify(JsonData) 
	}).done(function(data) {
		window.alert("成功\n企業ID：" + data);
		
		// 入力項目欄の記載を削除
		$("#corporationIdRegister").val("");
		$("#corporationNameRegister").val("");
		
	}).fail(function(data) {
        window.alert("企業情報の登録に失敗しました。\n原因：\n" + data.responseText);
	});
}

// 企業情報取得メソッド
function getCorporation(Url, Method) {

	$.ajax({
		url: Url,
		type: Method,
		data: {
			id : Method
		}, 
		beforeSend: function () {
        	// 実行前にプルダウンの要素を削除する
        	$("#jobOffer_corporationIdRegister").empty();
    	} 
	}).done(function(data) {
	
		// 取得したjsonの要素数分繰り返す
        $.each(data, function(i, corporationInfo) {
        	// プルダウンの要素を追加
        	$("#jobOffer_corporationIdRegister").append("<option value=" + corporationInfo.corporationId + ">" + corporationInfo.corporationName + "</option>");
        });
        
	}).fail(function(data) {
        
	});
}

// 業種情報取得メソッド
function getIndustry(Url, Method) {

	$.ajax({
		url: Url,
		type: Method,
		data: {
			id : Method
		}, 
		beforeSend: function () {
        	// 実行前にプルダウンの要素を削除する
        	$("#industryTypeIdRegister").empty();
    	} 
	}).done(function(data) {
	
		// 取得したjsonの要素数分繰り返す
        $.each(data, function(i, industryInfo) {
        	// プルダウンの要素を追加
        	$("#industryTypeIdRegister").append("<option value=" + industryInfo.industryTypeId + ">" + industryInfo.industryTypeName + "</option>");
        });
        
	}).fail(function(data) {
        
	});
}

// 職種情報取得メソッド
function getOccupation(Url, Method) {

	$.ajax({
		url: Url,
		type: Method,
		data: {
			id : Method
		}, 
		beforeSend: function () {
        	// 実行前にプルダウンの要素を削除する
        	$("#occupationTypeIdRegister").empty();
    	}
	}).done(function(data) {
	
		// 取得したjsonの要素数分、繰り返す
        $.each(data, function(i, occupationInfo) {
        	// プルダウンの要素を追加
        	$("#occupationTypeIdRegister").append("<option value=" + occupationInfo.occupationTypeId + ">" + occupationInfo.occupationTypeName + "</option>");
        });
        
	}).fail(function(data) {
        
	});
}
