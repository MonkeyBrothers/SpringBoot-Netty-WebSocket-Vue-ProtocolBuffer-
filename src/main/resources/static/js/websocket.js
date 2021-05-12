let websocket;
const port = 9999;
const ip = "localhost";
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://" + ip + ":" + port + "/ws");
} else {
    alert("您的浏览器不支持，请尝试更换浏览器！");
}

//连接发生错误的回调方法
websocket.onerror = function () {
    console.log("连接发生异常")
};

//连接成功建立的回调方法
websocket.onopen = function (event) {
    console.log("open")


}
window.setInterval(function () {
    console.log("~~~~~~~~~~~~~")
    websocket.send("WEBSOCKET_HEARTBEAT_INFO_FLAG");
}, 5000);

// 收到服务器发送的消息
websocket.onmessage = function () {
    video.addBarrage(event.data)
}
//连接关闭的回调方法
websocket.onclose = function () {
    console.log("close")
    alert("弹幕连接异常，请刷新重试")
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
}