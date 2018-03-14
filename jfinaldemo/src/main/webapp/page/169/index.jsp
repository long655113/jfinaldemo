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
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${novel.novelName}章节列表(${novel.author})_移动版</title>
<meta name="keywords" content="${novel.novelName},${novel.novelName}最新章节">
<meta name="description" content="${novel.novelName}最新章节由网友提供，《${novel.novelName}》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的都市言情，免费提供${novel.novelName}最新清爽干净的文字章节在线阅读。">
<meta name="MobileOptimized" content="240">
<meta name="applicable-device" content="mobile">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="shortcut icon" href="http://m.wenxuemi.com/favicon.ico">
<meta http-equiv="Cache-Control" content="no-transform ">
<meta property="og:type" content="novel">
<meta property="og:title" content="${novel.novelName}">
<meta property="og:description" content="${novel.desc}">
<meta property="og:image" content="${novel.image}">
<meta property="og:novel:category" content="都市言情">
<meta property="og:novel:author" content="${novel.author}">
<meta property="og:novel:book_name" content="${novel.novelName}">
<meta property="og:novel:read_url" content="/novel/novelIndex/${novel.id}">
<meta property="og:url" content="/novel/novelIndex/${novel.id}">
<meta property="og:novel:status" content="连载中">
<meta property="og:novel:author_link" content="http://zhannei.baidu.com/cse/search?s=8050404393306617446&amp;q=${novel.author}">
<meta property="og:novel:update_time" content="2017-01-16 21:39">
<meta property="og:novel:latest_chapter_name" content="${novelItems[fn:length(novelItems) - 1].title}">
<meta property="og:novel:latest_chapter_url" content="/novel/read/${novelItems[fn:length(novelItems) - 1].id}">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css?version=20170920">
<script src="${pageContext.request.contextPath}/js/ajax.js"></script>
<script src="${pageContext.request.contextPath}/js/read.js?version=2017050524" ></script>
<script src="${pageContext.request.contextPath}/js/cookie.js" ></script>
<%@include file="/page/header.jsp"%>
</head>
<body>
    <div class="header" id="bqgmb_head">
        <div class="back">
            <a href="javascript:history.go(-1);">返回</a>
        </div>
        <div id="edit" class="back">
            <a href="javascript:novelListToEdit();">编辑</a>
        </div>
        <div id="concel" class="back" style="display: none">
            <a href="javascript:novelListToRead();">取消</a>
        </div>
        <h1 id="bqgmb_h1" style="float: left;">${novel.novelName}</h1>
        <div class="back_r"><a href="${pageContext.request.contextPath}/">首页</a></div>
        <div id="deleteBefore" class="back_r" style="display: none"><a href="javascript:deleteNovelBeforeItem();">删除之前章节</a></div>
        <div id="delete" class="back_r" style="display: none"><a href="javascript:deleteNovelItem();">删除</a></div>
        <!--<script>fixwidth();</script>-->
    </div>
    <div class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/">首页</a></li>
            <li><a href="/player">音乐</a></li>
            <li><a href="http://m.wenxuemi.com/quanben/">全本</a></li>
            <li><a href="http://m.wenxuemi.com/bookcase.php">书架</a></li>
            <li><a href="http://m.wenxuemi.com/bookcase.html">足迹</a></li>
            <div class="cc"></div>
        </ul>
    </div>
    <div class="search">
        <form name="articlesearch" action="http://zhannei.baidu.com/cse/search">
            <input type="hidden" name="s" value="8050404393306617446">
            <table cellpadding="0" cellspacing="0" style="width:100%;">
                <tbody>
                    <tr>
                        <td style="background-color:#fff; border:1px solid #CCC;"><input id="s_key" name="q" type="text" class="key" value="输入书名后搜索，宁可少字不要错字" onfocus="this.value=&#39;&#39;"></td>
                        <td style="width:35px; background-color:#0080C0; background-image:url(&#39;http://m.wenxuemi.com/images/search.png&#39;); background-repeat:no-repeat; background-position:center"><input name="submit" type="submit" value="" class="go"></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
    <div class="cover">
        <div class="block">
        <div class="block_img2"><img src="${novel.image}" border="0" width="92" height="116" onerror="this.src=&#39;http://www.wenxuemi.com/modules/article/images/nocover.jpg&#39;"></div>
        <div class="block_txt2">
        <p><a href="${pageContext.request.contextPath}/novel/novelIndex/${novel.id}"></a></p><h2><a href="${pageContext.request.contextPath}/novel/novelIndex/${novel.id}">${novel.novelName}</a></h2><p></p>
        <p>作者：${novel.author}</p>
        <p>分类：<a href="http://m.wenxuemi.com/dushi/">都市言情</a></p>
        <p>状态：连载中</p>
        <p>更新：<fmt:formatDate type="both" 
                    dateStyle="medium" timeStyle="medium" 
                    value="${lastestItem.createTime}" /></p>
        <p>最新：<a href="${pageContext.request.contextPath}/novel/read/${lastestItem.id}">${lastestItem.title}</a></p>
        </div>
        </div>
        <div style="clear:both"></div>
        <div class="ablum_read">
            <span class="margin_right">
                <c:if test="${novelItems != null && !empty novelItems}">
                    <a href="${pageContext.request.contextPath}/novel/read/${novelItems[0].id}">开始阅读</a>
                </c:if>
            </span>
            <span class="margin_right">
                <a href="${pageContext.request.contextPath}/novel/download/${novel.id}">下载</a>
            </span>
            <span class="margin_right">
                <a href="${pageContext.request.contextPath}/novel/download/${novel.id}?fileType=pdf">下载pdf</a>
            </span>
        </div>
        <div class="intro">简介</div>
        <div class="intro_info">
            ${novel.desc}
        </div>
        <div class="intro">
            正文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/novel/novelIndex/${novel.id}?page=${currentPage}&sortType=asc" class="login_tips">[正序]</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/novel/novelIndex/${novel.id}?page=${currentPage}&sortType=desc" class="login_tips">[倒序]</a>
        </div>
        <c:if test="${novelItems != null && !empty novelItems}">
            <ul class="chapter">
                <c:forEach var="item" items="${novelItems}" >
                    <li>
                        <span class="editStatus">
                            <input type="checkbox" name="novelItemId" value="${item.id}" /><span onclick="checkItem(this)">${item.title}</span>
                        </span>
                        <a href="${pageContext.request.contextPath}/novel/read/${item.id}" class="readLink">${item.title}</a>
                    </li>
                </c:forEach>
                    
            </ul>
        </c:if>
        <li><a href="${pageContext.request.contextPath}/novel/update/${novel.id}">更新</a></li>
        
        <c:if test="${pageNumbers != null && !empty pageNumbers}">
            <div class="listpage">
                <span class="left">
                    <a 
                        <c:if test="${1 == currentPage}">
                            class="before"
                        </c:if>
                        <c:if test="${1 < currentPage}">
                            href="${pageContext.request.contextPath}/novel/novelIndex/${novel.id}?page=${currentPage-1}&sortType=${sortType}"
                            class="onclick"
                        </c:if>
                        >上一页</a>
                </span>
                <span class="middle">
                    <select name="pageselect" onchange="self.location.href='${pageContext.request.contextPath}/novel/novelIndex/${novel.id}?page=' + options[selectedIndex].value + '&sortType=${sortType}'">
                        <c:forEach var="item" items="${pageNumbers}" >
                            <option value="${item}" 
                                <c:if test="${item == currentPage}">
                                selected="selected"
                                </c:if>
                                >第${item}页</option>
                        </c:forEach>
<!--                        <option value="index.html" selected="selected">第1章 - 第20章</option>
                        <option value="index_2.html">第21章 - 第40章</option>
                        <option value="index_3.html">第41章 - 第60章</option>
                        <option value="index_4.html">第61章 - 第80章</option>
                        <option value="index_5.html">第81章 - 第100章</option>-->
                    </select>
                </span>
                <span class="right">
                        <a
                            <c:if test="${fn:length(pageNumbers) == currentPage}">
                                class="before"
                            </c:if>
                            <c:if test="${fn:length(pageNumbers) > currentPage}">
                                href="${pageContext.request.contextPath}/novel/novelIndex/${novel.id}?page=${currentPage+1}&sortType=${sortType}"
                                class="onclick"
                            </c:if>
                            >下一页</a>
                </span>
            </div>
        </c:if>
    </div>
    <p id="bottom"></p>
    <script>style_bottom()</script>
    <div class="footer">
        <ul>
            <li><a href="${pageContext.request.contextPath}/">首页</a></li>
<!--            <li><a href="http://www.wenxuemi.com/files/article/html/0/169/">电脑版</a></li>
            <li><a href="http://m.wenxuemi.com/bookcase.php">书架</a></li>
            <li><a href="http://m.wenxuemi.com/bookcase.html">足迹</a></li>-->
        </ul>
    </div>

</body>
</html>