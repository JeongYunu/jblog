<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

<script type="text/javascript">
window.onload = function(){
	if(${blogAdmin.blogInfo.blogTitle == null}){
		$("h1 > a").html("${blogAdmin.adminUser.userName}의 블로그입니다.");
	}else if(${blogAdmin.blogInfo.blogTitle == "의 블로그입니다."}){
		$("h1 > a").html("${blogAdmin.adminUser.userName}의 블로그입니다.");
	}else{
		$("h1 > a").html("${blogAdmin.blogInfo.blogTitle}");
		//console.log("${blogAdmin.blogInfo.blogTitle}")
	}
};
</script>

<div id="header" class="clearfix">
	<h1>
		<!-- 블로그 타이틀 -->
		<a href="${ pageContext.request.contextPath }/${ blogAdmin.adminUser.id }"></a>
	</h1>
	<ul class="clearfix">
		<c:if test="${ empty authUser }">
			<!-- 로그인 전 메뉴 -->
			<li><a class="btn_s" href="${ pageContext.request.contextPath }/user/loginForm">로그인</a></li>
		</c:if>

		<c:if test="${ not empty authUser }">
			<!-- 로그인 후 메뉴 -->
			<!-- 자신의 블로그일때만 관리 메뉴가 보인다. -->
			<c:if test="${ blogAdmin.adminUser.id eq authUser.id }">
				<li><a class="btn_s" href="${ pageContext.request.contextPath }/${ blogAdmin.adminUser.id }/admin/basic">내블로그 관리</a></li>
			</c:if>
			<li><a class="btn_s" href="${ pageContext.request.contextPath }/user/logOut">로그아웃</a></li>
		</c:if>
		<!-- //header -->
	</ul>
</div>

