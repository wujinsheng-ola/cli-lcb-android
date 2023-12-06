package sg.partying.lcb.websocket

class WebSocketClient {

    enum class WebSocketStatus(val value: Int) {
        Connect_None(0),
        Connect_Connecting(1),
        Connect_Open(2),
        Connect_Closing(3),
        Connect_Closed(4)
    }

    interface WebSocketListener {

        // 状态码，错误消息
        fun conecntStatus(status: Int, message: String)

        //收到消息的回调
        fun onMessageReceived(index: Int, message: String)
    }

    var webSocketListenerList = mutableListOf<WebSocketListener>();

    // 开始链接
    fun connect() {

    }


    // 发送消息
    fun send(message: String) {

    }

    // ping
    fun ping() {

    }

    fun receive() {

    }

    fun addListener() {}

    fun deleteListener() {}

    // 释放资源
    fun release() {

    }


}


