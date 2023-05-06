import {ChatComponent} from "./chat.component";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Component} from "@angular/core";


export class WebSocketAPI {
  webSocketEndPoint: string = 'http://localhost:8082/ws';
  topic: string = "/topic/greetings";
  stompClient: any;
  chatComponent: ChatComponent;
  constructor(chatComponent: ChatComponent){
    this.chatComponent = chatComponent;
  }
  _connect() {
    console.log("Initialize WebSocket Connection");
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    // @ts-ignore
    _this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe(_this.topic, function (sdkEvent: any) {
        _this.onMessageReceived(sdkEvent);
      });
      //_this.stompClient.reconnect_delay = 2000;
    }, this.errorCallBack);
  };

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error: string) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this._connect();
    }, 5000);
  }

  /**
   * Send message to sever via web socket
   * @param {*} message
   */
  _send(message: any) {
    console.log("calling logout api via web socket");
    this.stompClient.send("/app/hello", {}, JSON.stringify(message));
  }

  onMessageReceived(message: any) {
    console.log("Message Recieved from Server :: " + message);
    this.chatComponent.handleMessage(JSON.stringify(message.content));
    console.log("Message Recieved from Server :: " + message);
  }
}
