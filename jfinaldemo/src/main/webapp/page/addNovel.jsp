<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>配置新小说网站</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/page/header.jsp"%>
    </head>
    <body>
        <div>新增小说配置</div>
        <form action="${pageContext.request.contextPath}/novel/addNovel"  method="post">
            <div>网站名称：<input type="text" name="webSiteName" value="随梦小说网" /></div>
            <div>目录地址：<input type="text" name="indexUrl" value="http://www.suimeng.la/files/article/html/120/120901/" /></div>
            <div>
                获取目录策略：
                <select name="getIndexWayValue">
                    <option value="1">id</option>
                    <option value="2" selected>class</option>
                </select>
            </div>
            <div>目录策略KEY：<input type="text" name="key" value="acss" /></div>
            <div>目录策略KEY2：<input type="text" name="key" value="acss" /></div>
            <div>
                获取封面策略：
                <select name="getImgWayValue">
                    <option value="1">id</option>
                    <option value="2" >class</option>
                    <option value="4" selected>meta</option>
                </select>
            </div>
            <div>封面KEY1：<input type="text" name="imgKeys" value="property" /></div>
            <div>封面KEY2：<input type="text" name="imgKeys" value="og:image" /></div>
            
            <div>
                获取小说名称策略：
                <select name="getNovelNameWayValue">
                    <option value="1">id</option>
                    <option value="2" >class</option>
                    <option value="4" selected>meta</option>
                    <option value="5">tag</option>
                </select>
            </div>
            <div>小说名称KEY1(id)：<input type="text" name="novelNameKeys" value="property" /></div>
            <div>小说名称KEY1：(tag)<input type="text" name="novelNameKeys" value="og:novel:book_name" /></div>
            
            <div>
                获取作者策略：
                <select name="getAuthorWayValue">
                    <option value="1">id</option>
                    <option value="2">class</option>
                    <option value="3">contain</option>
                    <option value="4" selected>meta</option>
                </select>
            </div>
            <div>作者KEY1(id)：<input type="text" name="novelAuthorKeys" value="property" /></div>
            <div>作者KEY1：(tag)<input type="text" name="novelAuthorKeys" value="og:novel:author" /></div>
            
            <div>
                获取简介策略：
                <select name="getDescWayValue">
                    <option value="1">id</option>
                    <option value="2">class</option>
                    <option value="4" selected>meta</option>
                </select>
            </div>
            <div>简介KEY1(id)：<input type="text" name="novelDescKeys" value="property" /></div>
            <div>简介KEY1：(tag)<input type="text" name="novelDescKeys" value="og:description" /></div>
            
            <div>简介key2value：<input type="number" name="descKey2Index" value="0" /></div>
            
            <div>
                获取章节内容策略：
                <select name="getContentWayValue">
                    <option value="1" selected>id</option>
                    <option value="2">class</option>
                </select>
            </div>
            <div>章节KEY：<input type="text" name="itemKey" value="ccontent" /></div>
            
            <div><input type="submit" value="提交" /></div>
            
        </form>
    </body>
</html>
