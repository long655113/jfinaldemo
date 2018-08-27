

var currentItemCount = 1;
var currentStatus = -1;
var urlArray = new Array();
    
function loadItem(itemCount) {

    if (currentStatus != -1 && currentStatus != itemCount) {
        return;
    }
    
    if (currentItemCount >= itemCount) {
        return;
    }

    currentStatus = itemCount;
    document.getElementsByClassName("nr_set")[0].style.backgroundColor="#e0c85a"

    var nextItemA = document.getElementById("pb_next");
    var url = nextItemA.href;
    var query = "{}";
    doAjax(url, query, function(result) {
        var div=document.createElement("div");
        div.innerHTML = result.split("</body>")[0].split('nr_all c_nr">')[1];

        var content = div.getElementsByClassName("content")[0];
        var thisContent = document.getElementsByClassName("nr_nr");
        thisContent[0].appendChild(content);

        var next = div.getElementsByClassName("next");
        var thisNext = document.getElementsByClassName("next");
        thisNext[0].innerHTML = next[0].innerHTML;
        thisNext[1].innerHTML = next[1].innerHTML;

        currentItemCount ++;

        if (currentItemCount < itemCount) {
            loadItem(itemCount);
        } else {
            currentStatus = -1;
            document.getElementsByClassName("nr_set")[0].removeAttribute("style");
        }
        urlArray[urlArray.length] = url;

    }, "POST", 0);
}

function dedupe(array){
    return Array.from(new Set(array));
}

function toVoice() {
//    var urls = dedupe(urlArray);
    var params = "url=" + urlArray[0];
    for (var i = 1; i < urlArray.length; i++) {
        params += "&url=" + urlArray[i];
    }
    window.open('http://zjl.hmxingkong.com/speech/toAudio?'+ params);
}

function novelListToEdit() {
    var editContents = document.getElementsByClassName("editStatus");
    for (var i=0; i < editContents.length; i++) {
        editContents[i].style.display="block";
    }
    var linkContents = document.getElementsByClassName("readLink");
    for (var i=0; i < linkContents.length; i++) {
        linkContents[i].style.display="none";
    }
    document.getElementById("concel").style.display="block";
    document.getElementById("delete").style.display="block";
    document.getElementById("deleteBefore").style.display="block";
    document.getElementById("edit").style.display="none";
}
function novelListToRead() {
    var editContents = document.getElementsByClassName("editStatus");
    for (var i=0; i < editContents.length; i++) {
        editContents[i].style.display="none";
    }
    var linkContents = document.getElementsByClassName("readLink");
    for (var i=0; i < linkContents.length; i++) {
        linkContents[i].style.display="block";
    }
    document.getElementById("edit").style.display="block";
    document.getElementById("concel").style.display="none";
    document.getElementById("delete").style.display="none";
    document.getElementById("deleteBefore").style.display="none";
}
function checkItem(obj) {
    obj.previousSibling.checked=!(obj.previousSibling.checked);
}

function deleteNovel() {
    var novelIds = document.getElementsByName("novelId");
    
    var params = "";
    for (var i=0; i < novelIds.length; i++) {
        if (novelIds[i].checked) {
            var novelId = novelIds[i].value;
            if (params.length > 0) {
                params += "&";
            }
            params = params + "novelId=" + novelId;
        }
    }
    if (params.length == 0) {
        alert("未选择条目");
        return;
    }
    
    var url = ctx + "/novel/delete";
    var query = params;
    doAjax(url, query, function(result) {
        var resultObj = JSON.parse(result);
        if (resultObj.code == '0000') {
            document.location.reload();
        } else {
            alert(resultObj.msg);
        }
    }, "POST", 0);
}

function deleteNovelItem() {
    var novelIds = document.getElementsByName("novelItemId");
    
    var params = "";
    for (var i=0; i < novelIds.length; i++) {
        if (novelIds[i].checked) {
            var novelId = novelIds[i].value;
            if (params.length > 0) {
                params += "&";
            }
            params = params + "novelItemId=" + novelId;
        }
    }
    if (params.length == 0) {
        alert("未选择条目");
        return;
    }
    
    var url = ctx + "/novel/deleteItem";
    var query = params;
    doAjax(url, query, function(result) {
        var resultObj = JSON.parse(result);
        if (resultObj.code == '0000') {
            document.location.reload();
        } else {
            alert(resultObj.msg);
        }
    }, "POST", 0);
}

function deleteNovelBeforeItem() {
    var novelIds = document.getElementsByName("novelItemId");
    
    var params = "";
    var paramCount = 0;
    for (var i=0; i < novelIds.length; i++) {
        if (novelIds[i].checked) {
            var novelId = novelIds[i].value;
            if (params.length > 0) {
                params += "&";
            }
            params = params + "novelItemId=" + novelId;
            paramCount++;
        }
    }
    if (params.length == 0) {
        alert("未选择条目");
        return;
    }
    
    if (paramCount > 1) {
        alert("只能选择一个章节");
        return;
    }
    
    var url = ctx + "/novel/deleteBeforeItem";
    var query = params;
    doAjax(url, query, function(result) {
        var resultObj = JSON.parse(result);
        if (resultObj.code == '0000') {
            document.location.reload();
        } else {
            alert(resultObj.msg);
        }
    }, "POST", 0);
}

function changToReadMode() {
    var readMode = getCookie("readMode");
    var style;
    var newReadMode;
    if (readMode !== "1") {
        style = "none";
        newReadMode = 1;
    } else {
        newReadMode = 0;
        style = "block";
    }
    
    var hidClass = document.getElementsByClassName("hidClass");
    for (var i=0; i < hidClass.length; i++) {
        var element = hidClass[i];
        element.style.display = style;
    }
    
    setCookie("readMode", newReadMode);
}

function ready(fn){
    if(document.addEventListener) {
        document.addEventListener('DOMContentLoaded', function() {
            //注销事件, 避免反复触发
            document.removeEventListener('DOMContentLoaded',arguments.callee, false);
            fn();            //执行函数
        }, false);
    }else if(document.attachEvent) {        //IE
        document.attachEvent('onreadystatechange', function() {
            if(document.readyState == 'complete') {
                document.detachEvent('onreadystatechange', arguments.callee);
                fn();        //函数执行
            }
        });
    }
};

ready(function() {
    var readMode = getCookie("readMode");
    var style;
    if (readMode === "1") {
        style = "none";
    } else {
        style = "block";
    }
    
    var hidClass = document.getElementsByClassName("hidClass");
    for (var i=0; i < hidClass.length; i++) {
        var element = hidClass[i];
        element.style.display = style;
    }
    urlArray[0] = window.location.href;
});