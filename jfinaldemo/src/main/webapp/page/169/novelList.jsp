<%-- 
    Document   : index
    Created on : 2017-1-27, 16:26:38
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>张尼码小说</title>
        <meta name="MobileOptimized" content="240">
        <meta name="applicable-device" content="mobile">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css?version=2017030822">
        <script src="${pageContext.request.contextPath}/js/read.js?version=20180301" ></script>
        <script src="${pageContext.request.contextPath}/js/ajax.js?version=201701312053" ></script>
        <script src="${pageContext.request.contextPath}/js/cookie.js" ></script>
        <%@include file="/page/header.jsp"%>
    </head>
    <body>
        <div class="header" id="bqgmb_head">
            <div id="edit" class="back"><a href="javascript:novelListToEdit();">编辑</a></div>
            <div id="concel" class="back" style="display: none"><a href="javascript:novelListToRead();">取消</a></div>
            <h1 id="bqgmb_h1" style="float: left;">张尼码小说</h1>
            <div class="back_r"><a href="${pageContext.request.contextPath}/novel/toAddNovel">添加</a></div>
            <div id="delete" class="back_r" style="display: none"><a href="javascript:deleteNovel();">删除</a></div>
        </div>
        <div class="cover">
            <div class="intro">小说列表</div>
            <c:if test="${novels != null && !empty novels}">
                <ul class="chapter">
                    <c:forEach var="item" items="${novels}" >
                        <li>
                            <span class="editStatus">
                                <input type="checkbox" name="novelId" value="${item.id}" /><span onclick="checkItem(this)">${item.novelName}</span>
                            </span>
                            <a href="${pageContext.request.contextPath}/novel/novelIndex/${item.id}" class="readLink">${item.novelName}</a></li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </body>
</html>