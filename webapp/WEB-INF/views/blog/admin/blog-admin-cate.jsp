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

	//리스트 출력
	fetchList();
	
	$("#btnAddCate").on("click", function(){
		console.log("카테고리 추가");
		var categoryVo = {
				cateName: $("[name = 'name']").val(),
				description: $("[name = 'desc']").val()
		};
		console.log(categoryVo.cateName);
		if( categoryVo.cateName < 1){
			alert("카테고리명은 필수 입력사항이쥐");
			
		}else {
			$.ajax({
				
				url : "${pageContext.request.contextPath }/${ authUser.id }/admin/addCategory",
				type : "post",
				//contentType : "application/json",
				data : categoryVo,
	
				//dataType : "json",
				success : function(categoryList) {
					render(categoryList, "up");
					$("[name = 'name']").val("");
					$("[name = 'desc']").val("");
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
				
			});//ajax
		};//if
	});//추가버튼
	
	$("#cateList").on("click", ".btnCateDel", function (){
		console.log("삭제 버튼");
		var cateNo = $(this).data("no");
		var no = $(this).parents("tr").attr("id");
		console.log(no);
		
		$.ajax({
			url : "${ pageContext.request.contextPath }/${ authUser.id }/admin/delete",
			type : "post",
			data : { cateNo },
	
			dataType : "json",
			success : function(count) {
				console.log(count);
				if(count == 1){
					$("#" + no).remove();
					
				}
	
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});	
		
	});//삭제버튼
	
	function fetchList(){
		$.ajax({
			url : "${ pageContext.request.contextPath }/${ authUser.id }/admin/getCategory",
			type : "post",
	
			dataType : "json",
			success : function(categoryList) {
				console.log(categoryList);
	
				for (var i = 0; i < categoryList.length; i++) {
					render(categoryList[i], "down");
				};
	
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};//fetchList
	
	function render(categoryList, type){
		var str = '';
		str += '<tr class="trDel" id="t-' + categoryList.rNo + '">';	
		str += '<td>' + categoryList.rNo + '</td>';	
		str += '<td>' + categoryList.cateName +'</td>';	
		str += '<td>' + categoryList.postCount + '</td>';	
		str += '<td>' + categoryList.description + '</td>';	
		str += '<td class="text-center">';	
		str += '<img class="btnCateDel" data-no=' + categoryList.cateNo + ' src="${pageContext.request.contextPath}/assets/images/delete.jpg">';	
		str += '</td>';	
		str += '</tr>';	
	
		if (type === 'down') {
			$("#cateList").append(str);
		} else if (type === 'up') {
			$("#cateList").prepend(str);
		} else {
			console.log("타입미스");
		}
	};//redner
	
	
	
	
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
				<li class="tabbtn selected"><a href="${ pageContext.request.contextPath }/${ authUser.id }/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${ pageContext.request.contextPath }/${ authUser.id }/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->
						
						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="desc"></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

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