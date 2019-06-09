<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>正文 ${novelItem.title}_张尼码小说</title>
<meta name="keywords" content="正文 ${novelItem.title}"/>
<meta name="description" content="正文 ${novelItem.title},如果你想第一时间观看下一章节,请留意以及收藏,方便你下次阅读最新章节。"/>
<meta name="MobileOptimized" content="240"/>
<meta name="applicable-device" content="mobile"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
<meta http-equiv="Cache-Control" content="no-transform"/>
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/read.css?version=20190603"/>
<script src="${pageContext.request.contextPath}/js/ajax.js?version=201701312053" ></script>
<script src="${pageContext.request.contextPath}/js/read.js?version=2018082423" ></script>
<script src="${pageContext.request.contextPath}/js/cookie.js?version=test1" ></script>
<script src="${pageContext.request.contextPath}/js/runPage.js?version=20180328" ></script>
<%@include file="/page/header.jsp"%>
<c:if test="${novelItem.content == null || novelItem.content==''}">
<script>
    reFreshContent();
    function reFreshContent() {
        var url = "${pageContext.request.contextPath}/novel/refresh/${novelItem.id}";
        var query = "{}";
        
        doAjax(url, query, function(result) {
            console.log(result);
            var resultObj = JSON.parse(result);
            if (resultObj.code == '0000') {
                document.location.reload();
            } else {
                alert(resultObj.msg);
            }
        }, "POST", 0);
    }
    
</script>
</c:if>
</head>
<body id="nr_body" class="nr_all c_nr">
    <div class="header hidClass" id="_bqgmb_head">
        <div class="back_r"><a href="${pageContext.request.contextPath}/">首页</a></div>
    </div>
    <div class="nr_set">
        <div class="set1 hid_icon" onclick="changToReadMode()"><div></div></div>
	<div id="huyandiv" class="set1 hidClass" onclick="javascript:toVoice()">语音</div>
        <div id="huyandiv" class="set1 hidClass" onclick="javascript:location.href='${novelItem.url}'">网页</div>
        <div class="set2 hidClass">
            <div onclick="loadItem(2)">》</div>
            <div onclick="loadItem(5)">5</div>&nbsp;&nbsp;&nbsp;
            <div onclick="loadItem(10)">10</div>
            <div onclick="loadItem(15)">15</div>
            <div onclick="loadItem(25)">25</div>
            <div onclick="loadItem(50)">50</div>
        </div>
        <div class="nr_page hidClass">
            <table cellpadding="0" cellspacing="0">
                <tr class="itemDir1">
                    <td class="prev"><a id="pt_prev" href="${pageContext.request.contextPath}/novel/read/${novelItem.preId}">上一章</a></td>
                    <td class="mulu"><a id="pt_mulu" href="${pageContext.request.contextPath}/novel/novelIndex/${novelItem.novelId}">目录</a></td>
                    <c:if test="${novelItem.nextId != null}">
                        <td class="next"><a id="pb_next" href="${pageContext.request.contextPath}/novel/read/${novelItem.nextId}">下一章</a></td>
                    </c:if>

                    <c:if test="${novelItem.nextId == null}">
                        <td class="next">下一章</td>
                    </c:if>
                </tr>
            </table>
        </div>

        <div id="nr" class="nr_nr">
         <div id="nr1" class="content">
            <div class="nr_title" id="nr_title">正文 ${novelItem.title}</div>
            <br />
            ${novelItem.content}
            <p>&nbsp;</p>
        </div>
        </div>
        <div class="nr_page">
            <table cellpadding="0" cellspacing="0">
                <tr class="itemDir2">
                    <td class="prev"><a id="pb_prev" href="${pageContext.request.contextPath}/novel/read/${novelItem.preId}">上一章</a></td>
                    <td class="mulu"><a id="pb_mulu" href="${pageContext.request.contextPath}/novel/novelIndex/${novelItem.novelId}">目录</a></td>
                    <c:if test="${novelItem.nextId != null}">
                        <td class="next"><a id="pb_next" href="${pageContext.request.contextPath}/novel/read/${novelItem.nextId}">下一章</a></td>
                    </c:if>

                    <c:if test="${novelItem.nextId == null}">
                        <td class="next">下一章</td>
                    </c:if>
                </tr>
            </table>
        </div>
        <div class="footer">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">首页</a></li>
            </ul>
        </div>
    </div>
</body>
</html>