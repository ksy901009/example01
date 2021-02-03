<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<table border="1" width="80%" align="center">
	<tr>
		<th>ID</th>
		<th>이름</th>
		<th>메모</th>
		<th>날짜</th>
	</tr>
	<c:forEach var="row" items="${list }">
		<tr>
			<td>${jj}</td>
			<td>${row.writer}</td>
			<td>
				<a href="#">
					${row.content}
				</a>
			</td>
			<td>${row.regiDate}</td>
		</tr>
		<c:set var="jj" value="${jj = jj - 1 }"></c:set>
	</c:forEach>
	
	<c:if test="${totalRecord > 0 }">
		<tr align="center">
			<td colspan="11">
				<a href="#" onclick="goPage('memo_list', '1');">[첫페이지]</a>&nbsp;&nbsp;
				
				<c:if test="${startPage > blockSize }">
					<a href="#" onclick="goPage('memo_list', '${startPage - blockSize }');">[이전10개]</a>
				</c:if>
				<c:if test="${startPage <= blockSize }">
					[이전10개]
				</c:if>
				&nbsp;
				
				<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
					<c:if test="${i == pageNumber }">
						[${i }]
					</c:if>
					<c:if test="${i != pageNumber }">
						<a href="#" onclick="goPage('memo_list', '${i}');">${i }</a>
					</c:if>
				</c:forEach>
				
				&nbsp;
				<c:if test="${lastPage < totalPage }">
					<a href="#" onclick="goPage('memo_list', '${startPage + blockSize}');">[다음10개]</a>
				</c:if>
				<c:if test="${lastPage >= totalPage }">
					[다음10개]
				</c:if>
				
				<a href="#" onclick="goPage('memo_list', '${totalPage }');">[끝페이지]</a>&nbsp;&nbsp;
			</td>
		</tr>
	</c:if>
</table>
<script>
	function goPage(value1, value2) {
		if(value1 == 'memo_list') {
			location.href = '${path}/memo_servlet/write.do?pageNumber=' + value2;
		} 
	}
</script>