<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
								<td class="line_table" align="center" colspan="2"><span
									class="left_bt2">确认订单</span></td>
							</tr>
						<c:forEach items="${ordersPage.list}" var="item">
                             <tr>
								<td class="line_table" align="center"><span
									class="left_txt">${item.userid}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${item.realname}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${item.phone}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${item.adress}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${item.menuname}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${item.menusum}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${item.price}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${item.sum}</span></td>
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
								
								<c:choose>
									<c:when test="${item.delivery eq 1}">
										<td class="line_table" align="center" colspan="2"/>
									</c:when>
									<c:otherwise>
										<td class="line_table" align="center">
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=yes&id=${item.id}&currentPage=${ordersPage.currentPage}">确认</a>
										</td>
										<td class="line_table" align="center">
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=no&id=${item.id}&currentPage=${ordersPage.currentPage}">取消</a>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							</c:forEach>
							<tr>
								<td class="line_table" align="center" colspan="12" height="20">
								<span class="left_bt2">第${ordersPage.currentPage}页
										&nbsp;&nbsp;共${ordersPage.totalPage}页
								</span>&nbsp;&nbsp; 
								    <c:choose>
									<c:when test="${ordersPage.currentPage eq 1}">
										[首页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=findByPage&currentPage=1">[首页]</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ordersPage.currentPage eq ordersPage.totalPage}">
										[尾页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=findByPage&currentPage=${ordersPage.totalPage}">[尾页]</a>
									</c:otherwise>
								</c:choose>
								    &nbsp;&nbsp;
								<c:choose>
									<c:when test="${ordersPage.hasPrePage eq false}">
										[上一页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=findByPage&currentPage=${ordersPage.currentPage-1}">[上一页]</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ordersPage.hasNextPage eq false}">
										[下一页]
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/OrdersServlet?method=findByPage&currentPage=${ordersPage.currentPage+1}">[下一页]</a>
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