<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">

	var searchRequest = new XMLHttpRequest();		// 검색
	var registerRequest = new XMLHttpRequest();		// 등록
	
	function searchFunction(){
		searchRequest.open("POST", "./UserSearchServlet?userName=" + encodeURIComponent(document.getElementById("userName").value), true);
		searchRequest.onreadystatechange = searchProcess;
		searchRequest.send(null);
	}
	
	function searchProcess(){
		var table = document.getElementById("ajaxTable");
		table.innerHTML = "";
		if(searchRequest.readyState == 4 && searchRequest.status == 200){
			var object = eval('(' + searchRequest.responseText + ')');	// String -> JSon
			var result = object.result;	// 전달받은 변수 이름이 result
			
			for(var i=0; i<result.length; i++){
				var row = table.insertRow(0);			// 열 생성
				for(var j=0; j<result[i].length; j++){
					var cell = row.insertCell(j);		// 행 생성
					cell.innerHTML = result[i][j].value;
				}
			}
		}
	}
	
	function registerFunction(){
		registerRequest.open("POST", "./UserRegisterServlet?userName=" + encodeURIComponent(document.getElementById("registerName").value)
											+ "&userAge=" + encodeURIComponent(document.getElementById("registerAge").value)
											+ "&userGender=" + encodeURIComponent($('input[name=registerGender]:checked').val())
											+ "&userEmail=" + encodeURIComponent(document.getElementById("registerEmail").value), true);
		registerRequest.onreadystatechange = registerProcess;
		registerRequest.send(null);
	}
	
	function registerProcess(){
		if(registerRequest.readyState == 4 && registerRequest.status == 200){
			var result = registerRequest.responseText;
			if(result !=- -1){
				alert('등록에 실패했습니다.');
			}else{
				// 등록 성공이므로 폼 값을 초기화한다.
				var userName = document.getElementById("userName");
				var registerName = document.getElementById("registerName");
				var regitserAge = document.getElementById("registerAge");
				var registerEmail = document.getElementById("registerEmail");
				userName.value = "";
				registerName.value = "";
				registerAge.value = "";
				registerEmail.value = "";
				
				// 등록된 데이터를 포함해서 새로 결과 출력
				searchFunction();
			}
		}
	}
	
	window.onload = function(){
		searchFunction();
	}
	
</script>
<title>JSP Ajax</title>
</head>
<body>
	<br>
	<div class="container">
		<div class="form-group row pull-right">
			<div class="col-xs-8"> 
				<input class="form-control" id="userName" onkeyup="searchFunction()" type="text" size="20">
			</div>
			<div class="col-xs-2">
				<button class="btn btn-primary" onclick="searchFunction()" type="button">검색</button>
			</div>
		</div>
		<table class="table" style="text-align: center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<td style="background-color: #fafafa; text-align: center;" id="uName">이름</td>
					<td style="background-color: #fafafa; text-align: center;">나이</td>
					<td style="background-color: #fafafa; text-align: center;">성별</td>
					<td style="background-color: #fafafa; text-align: center;">이메일</td>
				</tr>
			</thead>
			<tbody id="ajaxTable">
		
			</tbody>
		</table>
		
	</div>
 	<div class="container">
 		<table class="table" style="text-align: center; border: 1px solid #dddddd">
 			<thead>
 				<tr>
 					<th colspan="2" style="background-color: #fafafa; text-align: center;'">회원 등록 양식</th>
 				</tr>
 			</thead>
 			<tbody>
 				<tr>
 					<td style="background-color: #fafafa; text-align: center;"><h5>이름</h5></td>
 					<td><input class="form-control" type="text" id="registerName" size="20"></td>
 				</tr>
 				<tr>
 					<td style="background-color: #fafafa; text-align: center;"><h5>나이</h5></td>
 					<td><input class="form-control" type="text" id="registerAge" size="20"></td>
 				</tr>
 				<tr>
 					<td style="background-color: #fafafa; text-align: center;"><h5>성별</h5></td>
 					<td>
 						<div class="form-group" style="text-align: center; margin: 0 auto;">
 							<div class="btn-group" data-toggle="buttons">
 								<label class="btn btn-primary active">
 									<input type="radio" name="registerGender" autocomplete="off" value="남자" checked>남자
 								</label>
 								<label class="btn btn-primary">
 									<input type="radio" name="registerGender" autocomplete="off" value="여자">여자
 								</label>
 							</div>
 						</div>
 					</td>
 				</tr>
 				<tr>
 					<td style="background-color: #fafafa; text-align: center;"><h5>이메일</h5></td>
 					<td><input class="form-control" type="text" id="registerEmail" size="20"></td>
 				</tr>
 				<tr>
 					<td colspan="2"><button class="btn btn-primary pull-right" onclick="registerFunction();" type="button">등록</button>
 				</tr>
 			</tbody>
 		</table>
 		
 	</div>
</body>
</html>