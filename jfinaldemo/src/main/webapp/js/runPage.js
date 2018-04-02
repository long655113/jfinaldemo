window.onload = function () {
    function $(ele){
        return document.querySelector(ele);
    }
    
    var node=document.createElement("div");
    var dodyElement = document.getElementsByTagName("body")[0];
    node.setAttribute("class", "runPageUp rotate90");
    node.innerHTML = "<div class='topText'>&lt;</div>";
    
    var nodeDown=document.createElement("div");
    nodeDown.setAttribute("class", "runPageDown rotate90");
    nodeDown.innerHTML = "<div class='topText'>&gt;</div>";
    
    dodyElement.appendChild(node);
    dodyElement.appendChild(nodeDown);
    
    function getScrollTop() {
        var scrollTop = 0;
        if (document.documentElement && document.documentElement.scrollTop) {
            scrollTop = document.documentElement.scrollTop;
        } else if (document.body) {
            scrollTop = document.body.scrollTop;
        }
        return scrollTop;
    }
    
    $('.runPageUp').onclick=function(){
        var totalHeight = $("body").offsetHeight;	//div总高度
        var pageHeight = window.innerHeight;    //屏幕高度
        var currentScroll = getScrollTop();	//当前滚动条位置
        
        var targetScroll = currentScroll - pageHeight;	//准备滚动到位置
        
        window.scrollTo( 0, targetScroll);
    };
    
    $('.runPageDown').onclick=function(){
        var totalHeight = $("body").offsetHeight;	//div总高度
        var pageHeight = window.innerHeight;    //屏幕高度
        var currentScroll = getScrollTop();	//当前滚动条位置
        var targetScroll = pageHeight + currentScroll;	//准备滚动到位置
        window.scrollTo( 0, targetScroll);
    };
};