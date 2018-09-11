<%@ page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<script src="js/date.js" type="text/javascript"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
</head>

<body>


	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">

				<div align="center" width="120">
					<form action="${pageContext.request.contextPath}/OrdersServlet?method=find" name="form1" method="post">
						<table id="table1" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">
							<tbody style="margin: 0; padding: 0">

								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按用户ID查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="userid" size="20"> </td>
								</tr>
								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按菜品名称查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="menuname" size="20"> <input
										type="submit" value="查询"></td>
								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按销售日期查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="date" size="20" readOnly
										onClick="setDay(this);"> </td>
						</table>
					</form>
				</div>



				<div align="center">
					<table id="table2" class="line_table"
						style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
						cellPadding="0">
						<tbody style="margin: 0; padding: 0">
							<tr>
								<td class="line_table" align="center" colspan="12"><span
									class="left_bt2">销售订单查询结果信息列表</span></td>
							</tr>
							<tr>
								<td class="line_table" align="center"><span
									class="left_bt2">用户ID</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">真实姓名</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">联系方式</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">家庭住址</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">菜品名称</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">单价(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">合计(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购时间</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">是否派送</span></td>

							</tr>
							<c:forEach items="${ordersSearchPage.list}" var="item">
								<tr>
									<td class="line_table" align="center"><span
										class="left_txt">${item.userid}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.realname}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.username}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.phone}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.address}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.menuname}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.menusum}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.price}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.times}</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">
								<c:choose>
									<c:when test="${item.delivery eq 1}">
										是
									</c:when>
									<c:otherwise>
										否
									</c:otherwise>
								</c:choose>
									</span></td>
								</tr>
							</c:forEach>
							<tr>
								<td class="line_table" align="center" colspan="12" height="20">
								<span class="left_bt2">第${ordersSearchPage.currentPage}页
										&nbsp;&nbsp;共${ordersSearchPage.totalPage}页
								</span>&nbsp;&nbsp; 
								    <c:choose>
									<c:when test="${ordersSearchPage.currentPage eq 1}">
										[首页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=find&currentPage=1&userid=${search.userid}&menuname=${search.menuname}&date=${search.date}">[首页]</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ordersSearchPage.currentPage eq ordersSearchPage.totalPage}">
										[尾页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=find&currentPage=${ordersSearchPage.totalPage}&userid=${search.userid}&menuname=${search.menuname}&date=${search.date}">[尾页]</a>
									</c:otherwise>
								</c:choose>
								    &nbsp;&nbsp;
								<c:choose>
									<c:when test="${ordersSearchPage.hasPrePage eq false}">
										[上一页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=find&currentPage=${ordersSearchPage.currentPage-1}&userid=${search.userid}&menuname=${search.menuname}&date=${search.date}">[上一页]</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ordersSearchPage.hasNextPage eq false}">
										[下一页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=find&currentPage=${ordersSearchPage.currentPage+1}&userid=${search.userid}&menuname=${search.menuname}&date=${search.date}">[下一页]</a>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
					</table>
				</div>

			</td>

		</tr>
	</table>

</body>
</html>
