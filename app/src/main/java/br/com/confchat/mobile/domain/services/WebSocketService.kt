package br.com.confchat.mobile.domain.services

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketService : WebSocketListener(){
    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        webSocket.send("Hellow world")
        Log.e("burak", "connected")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        outPut("Received : $text")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(NORMAL_CLOSSURE_STATUS,null)
        outPut("Closeing : $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        outPut("Error : ${t.message}")
    }
    fun outPut(text:String){
        Log.d("WebSocket",text)
    }
    companion object{
        private const val NORMAL_CLOSSURE_STATUS = 1000
    }
}