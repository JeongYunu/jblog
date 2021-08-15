<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

<script type="text/javascript">
window.onload = function(){
	
	var userId;
	var idChecked = false;
	var registerInfo = {
		id: document.getElementById("txtId"),
		checkMsg: false,
		password: document.getElementById("txtPassword"),
		name: document.getElementById("txtUserName"),
		agree: false,
	};
	console.log(registerInfo);
	
	$("#btnIdCheck").on("click", function(){
		idCheck();
	});

	$("#joinForm").on("submit", function(){
		if(registerSubmit() == false){
			return false;
		}else{
			return true;
		}
	});
	
	function idCheck(){
		idChecked = true;
		userId = registerInfo.id.value;
		if (registerInfo.id.value < 1) {
			alert("아이디를 입력해주세요");
		}else {
			$.ajax({
	
				url : "${pageContext.request.contextPath }/user/idCheck",
				type : "post",
				//contentType : "application/json",
				data : {
					id : registerInfo.id.value
				},
	
				//dataType : "json",
				success : function(idCheck) {
					console.log(typeof idCheck);
					if (idCheck === true) {
						$("#tdMsg").text("사용할 수 있는 아이디 입니다.");
						$("#tdMsg").show();
						registerInfo.checkMsg = true;
					} else {
						$("#tdMsg").text("다른 아이디로 가입해 주세요.");
						$("#tdMsg").show();
						registerInfo.checkMsg = false;
					};
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		};
	};//idCheck
	
	function registerSubmit(){
		
		if(registerInfo.id.value < 1){
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(idChecked == false){
			alert("아이디체크를 해주세요");
			return false;
		}
		
		if(registerInfo.checkMsg == false){
			$("#tdMsg").hide();
			idChecked = false;
			alert("이미 사용중인 아이디 입니다");
			return false;
		}
		
		if(registerInfo.password.value < 1){
			alert("패스워드를 입력해주세요");
			return false;
		}
		
		if(registerInfo.agree == $("#chkAgree").is(":checked")){
			alert("약관에 동의해주세요");
			return false;
		}
		
		if(registerInfo.id.value !== userId){
			$("#tdMsg").hide();
			alert("아이디체크를 해주세요");
			return false;
		}else{
			return true;
		};
	};//registerSubmit
}//window.onload
	
</script>
</head>
<body>
	<div id="center-content">

		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>
			<form id="joinForm" method="post" action="${ pageContext.request.contextPath }/user/join">
				<table>
					<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
					<tr>
						<td><label for="txtId">아이디</label></td>
						<td><input id="txtId" type="text" name="id"></td>
						<td><button id="btnIdCheck" type="button">아이디체크</button></td>
					</tr>
					<tr>
						<td></td>
						<td id="tdMsg" colspan="2">
							<!-- 아이디사용여부 -->
						</td>
					</tr>
					<tr>
						<td><label for="txtPassword">패스워드</label></td>
						<td><input id="txtPassword" type="password" name="password" value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="txtUserName">이름</label></td>
						<td><input id="txtUserName" type="text" name="userName" value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><span>약관동의</span></td>
						<td colspan="3"><input id="chkAgree" type="checkbox" name="agree" value="y"> <label for="chkAgree">서비스 약관에 동의합니다.</label></td>
					</tr>
				</table>
				<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit">회원가입</button>
				</div>

			</form>

		</div>


		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>

	</div>

</body>



</html>