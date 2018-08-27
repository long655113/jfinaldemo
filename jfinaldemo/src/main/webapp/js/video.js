window.onload = function () {
    function $(ele) {
        return document.querySelector(ele);
    }

    var audio = $('#myAudio');

    //==============================================播放结束后播放下一曲
    audio.addEventListener('ended', function () {
        console.log("end");
    }, false);
}