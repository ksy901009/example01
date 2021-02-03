<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	// 페이지 로딩이 끝난 후 실행되는 함수
	$(document).ready(function() {
		list();
		$("#btnSave").click(function() {
			insert();
		});
	});
	
	function insert() {
		var writer = $("#writer").val();
		var content = $("#content").val();
		var param = {
				"writer" : writer,
				"content" : content
		};
		
		if(writer == '') {
			alert('이름을 입력하세요');
			$("#writer").select();
			
			return;
		}
		
		if(content == '') {
			alert('내용을 입력하세요');
			$("#content").select();
			
			return;
		}
		
		$.ajax({
			type: "post",
			data: param,
			url: "${path}/memo_servlet/writeProc.do",
			success: function() {
				list();
				$("#writer").val("");
				$("#content").val("");
			}
		});
	}
	
	function list() {
		var param = {
				"pageNumber" : '${pageNumber}'
		};
		
		$.ajax({
			type: "post",
			data: param,
			url: "${path}/memo_servlet/list.do",
			success: function(result) {
				$("#result").html(result);
			}
		});
	}
</script>
</head>
<body>

<table border="1" width="100%">
	<tr>
		<td colspan="2"><h2>메모장</h2></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" id="writer" size="10"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<input type="text" id="content" size="40">
			<input type="button" id="btnSave" value="확인">
		</td>
	</tr>
	<tr>
		<td colspan="2">
 			<!-- 결과가 출력되는 영역 -->
			<div id="result"></div>
		</td>
	</tr>
</table>
</body>
</html>