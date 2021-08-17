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
$(function(){
	$("#btnArea").on("click", ".btn_l", function(){
		event.preventDefault();			
		console.log("포스트하기 클릭");
		var postVo = {
				cateNo: $("[name = 'cateNo']").val(),
				postTitle: $("[name = 'postTitle']").val(),
				postContent: $("[name = 'postContent']").val()
		};
		if( postVo.postTitle < 1){
			alert("포스트 제목은 필수 입력사항이쥐");
		}else {
			$.ajax({
				
				url : "${pageContext.request.contextPath }/${ authUser.id }/admin/write",
				type : "post",
				//contentType : "application/json",
				data : postVo,
	
				//dataType : "json",
				success : function(count) {
					if(count == 1){
						$("[name = 'cateNo']").val("");
						$("[name = 'postTitle']").val("");
						$("[name = 'postContent']").val("");
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
				
			});//ajax
		};//if
	});//포스트하
});//document
</script>
</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
	<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${ pageContext.request.contextPath }/${ authUser.id }/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${ pageContext.request.contextPath }/${ authUser.id }/admin/category">카테고리</a></li>
				<li class="tabbtn selected"><a href="${ pageContext.request.contextPath }/${ authUser.id }/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
				<form action="" method="">
			      	<table id="admin-write">
			      		<colgroup>
							<col style="width: 100px;">
							<col style="">
							<col style="">
						</colgroup>
			      		<tr>
			      			<td class="t">포스트 제목</td>
			      			<td >
			      				<input type="text" name="postTitle">
				      		</td>
				      		<td>
				      			<select name="cateNo">
				      				<!-- 카테고리 리스트 영역 -->
				      					<!-- <option value="">카테고리 선택</option> -->
				      				<c:forEach items="${ blogAdmin.categoryList }" var="categoryVo">
				      					<option value="${ categoryVo.cateNo }">${ categoryVo.cateName }</option>
				      				</c:forEach>
				      				<!-- 카테고리 리스트 영역 -->
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td>내용</td>
			      			<td colspan="2"><textarea name="postContent"></textarea></td>
			      		</tr>
			      	</table>
			      	<div id="btnArea">
			      		<button class="btn_l" type="submit" >포스트하기</button>
			      	</div>
				</form>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	</div>
	<!-- //wrap -->
</body>
</html>
