<%-- 
    Document   : welcome
    Created on : 2017-1-27, 16:13:31
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/page/header.jsp"%>
    </head>
    <body>
        <h1>hello,肥威</h1>
        <br>
        <a href="${pageContext.request.contextPath}/novel/toAddNovel">添加小说</a>
        <br>
        <a href="${pageContext.request.contextPath}/novel/novelList">小说列表</a>
        
        <table>
            <thead>
                <tr class="header" id="theader">
                    <th onclick="javascript:sortTable(0);">名称</th>
                    <th class="detailsColumn" onclick="javascript:sortTable(1);">
                        大小
                    </th>
                    <th class="detailsColumn" onclick="javascript:sortTable(2);">
                        修改日期
                    </th>
                </tr>
            </thead>
            <tbody id="tbody">
                <tr>
                    <td data-value="img/">
                        <a class="icon dir" href="/img/">img/</a>
                    </td>
                    <td class="detailsColumn" data-value="0"></td>
                    <td class="detailsColumn" data-value="1519255740">2018/2/22 上午7:29:00</td>
                </tr>
                <tr>
                    <td data-value="jsp/">
                        <a class="icon dir" href="/jsp/">jsp/</a>
                    </td>
                    <td class="detailsColumn" data-value="0"></td>
                    <td class="detailsColumn" data-value="1518396780">2018/2/12 上午8:53:00</td>
                </tr>
                <tr>
                    <td data-value="mp4/">
                        <a class="icon dir" href="/mp4/">mp4/</a>
                    </td>
                    <td class="detailsColumn" data-value="0"></td>
                    <td class="detailsColumn" data-value="1517009280">2018/1/27 上午7:28:00</td>
                </tr>
                <tr>
                    <td data-value="music/">
                        <a class="icon dir" href="/music/">music/</a>
                    </td>
                    <td class="detailsColumn" data-value="0"></td>
                    <td class="detailsColumn" data-value="1518392940">2018/2/12 上午7:49:00</td>
                </tr>
                <tr>
                    <td data-value="player/">
                        <a class="icon dir" href="/player/">player/</a>
                    </td>
                    <td class="detailsColumn" data-value="0"></td>
                    <td class="detailsColumn" data-value="1517010120">2018/1/27 上午7:42:00</td>
                </tr>
                <tr>
                    <td data-value="soft/">
                        <a class="icon dir" href="/soft/">soft/</a>
                    </td>
                    <td class="detailsColumn" data-value="0"></td>
                    <td class="detailsColumn" data-value="1519831980">2018/2/28 下午11:33:00</td>
                </tr>
                <tr>
                    <td data-value="xiangqi/">
                        <a class="icon dir" href="/xiangqi/">xiangqi/</a>
                    </td>
                    <td class="detailsColumn" data-value="0"></td>
                    <td class="detailsColumn" data-value="1517009880">2018/1/27 上午7:38:00</td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
