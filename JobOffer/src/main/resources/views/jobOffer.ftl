<#-- @ftlvariable name="" type="views.JobOfferView" -->
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="content-language" content="ja">
		<meta name="author" content="Kazushige Yamaguchi">
		<title>求人一覧画面</title>
		<script type="text/javascript" src="http://localhost:8080/view/job/js/list.js"></script>
		<script type="text/javascript" src="http://localhost:8080/view/job/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="http://localhost:8080/view/job/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="http://localhost:8080/view/job/js/jquery-migrate-3.0.0.min.js"></script>
		<link rel="stylesheet" type="text/css" href="http://localhost:8080/view/job/css/jquery-ui.min.css">
		<link rel="stylesheet" type="text/css" href="http://localhost:8080/view/job/css/list.css">
	</head>

	<body>
		<div id="searchBox">
			<h2>検索</h2>
			<table id="searchBoxTable">
				<tr>
					<th align="right">職種ID：</th>
					<td><input type="text" name="occupationTypeId" id="occupationTypeId"></td>
					<th align="right">業種ID：</th>
					<td><input type="text" name="industryTypeId" id="industryTypeId"></td>
				</tr>
				<tr>
					<th align="right">フリーワード：</th>
					<td colspan="3"><input type="text" name="freeWord" id="freeWord"></td>
				</tr>
			</table>
			<input type="button" value="検索" onClick="search(0)">
		</div>
		<input type="button" value="求人登録" onClick="showRegisterDialog('jobOffer')">
		<div id="jobOfferRegister" title="登録項目入力" style="display:none;">
			<p>求人情報を入力してください。</p>
			<table>
				<tr>
					<th align="right">求人ID：</th>
					<td><input type="text" name="jobOfferIdRegister" id="jobOfferIdRegister"></td>
				</tr>
				<tr>
					<th align="right">求人名：</th>
					<td><input type="text" name="jobOfferNameRegister" id="jobOfferNameRegister"></td>
				</tr>
				<tr>
					<th align="right">企業ID：</th>
					<td>
						<select id="jobOffer_corporationIdRegister" name="jobOffer_corporationIdRegister">
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">業種：</th>
					<td>
						<select id="industryTypeIdRegister" name="industryTypeIdRegister">
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">職種：</th>
					<td>
						<select id="occupationTypeIdRegister" name="occupationTypeIdRegister">
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">キャッチコピー：</th>
					<td><input type="text" name="catchCopyRegister" id="catchCopyRegister"></td>
				</tr>
				<tr>
					<th align="right" valign="top">概要：</th>
					<td><textarea name="jobOfferOverviewRegister" id="jobOfferOverviewRegister" cols="25" rows="5"></textarea></td>
				</tr>
			</table>
			<input type="button" value="登録" onClick="jobOfferRegisterStart()">
		</div>
		<input type="button" value="企業登録" onClick="showRegisterDialog('corporation')">
		<div id="corporationRegister" title="登録項目入力" style="display:none;">
			<p>企業情報を入力してください。</p>
			<table>
				<tr>
					<th align="right">企業ID：</th>
					<td><input type="text" name="corporationIdRegister" id="corporationIdRegister"></td>
				</tr>
				<tr>
					<th align="right">企業名：</th>
					<td><input type="text" name="corporationNameRegister" id="corporationNameRegister"></td>
				</tr>
			</table>
			<input type="button" value="登録" onClick="corporationRegisterStart()">
		</div>
		<h2>検索結果</h2>
		<div id="searchResult">
			<table class="jobOfferTable">
			</table>
			<label id="pageCount">ページ数</label>
			/
			<label id="maxPageCount">Maxページ数</label>
		</div>
	</body>
</html>


