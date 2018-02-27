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

<title>${novel.novelName}最新章节(${novel.author})_移动版</title>
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
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script src="/js/push.js"></script>
<script src="/js/common.js"></script>
<script src="/js/ajax.js"></script>

</head>
<body>
<div class="header" id="bqgmb_head">
<div class="back"><a href="javascript:history.go(-1);">返回</a></div>
<h1 id="bqgmb_h1">${novel.novelName}</h1>
<div class="back_r"><a href="/">首页</a></div>
<!--<script>fixwidth();</script>-->
</div>
<div class="search">
    <form name="articlesearch" action="/novel/novelAddNovel" method="post">
<input type="hidden" name="s" value="8050404393306617446">
<table cellpadding="0" cellspacing="0" style="width:100%;">
<tbody><tr>
<td style="background-color:#fff; border:1px solid #CCC;">
    <input id="url" name="url" type="text" class="key" value="输入小说目录url" onfocus="this.value=&#39;&#39;">
</td>
<td style="width:35px; background-color:#0080C0; background-image:url(&#39;http://m.wenxuemi.com/images/search.png&#39;); background-repeat:no-repeat; background-position:center">
    <input name="submit" type="submit" value="" class="go">
</td>
</tr>
</tbody>
</table>
</form>
</div>
<div class="cover">
<div style="clear:both"></div>
<div class="ablum_read">
</div>
<div class="intro">说明</div>
<div class="intro_info">
    以下网站小说可以直接复制目录url到提交给系统，系统将抓取小说内容
</div>
<div class="intro">已识别网站</div>
<c:if test="${configs != null && !empty configs}">
<ul class="chapter">
    <c:forEach var="item" items="${configs}" >
        <li><a href="${item.url}" target="_blank">${item.webSiteName}</a></li>
    </c:forEach>
</ul>
</c:if>
</div>
<div class="footer">
<ul>
<li><a href="/">首页</a></li>
<li><a href="http://www.wenxuemi.com/files/article/html/0/169/">电脑版</a></li>
<li><a href="http://m.wenxuemi.com/bookcase.php">书架</a></li>
<li><a href="http://m.wenxuemi.com/bookcase.html">足迹</a></li>
</ul>
</div>

</body></html>