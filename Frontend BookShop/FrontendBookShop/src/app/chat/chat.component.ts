import { Component } from '@angular/core';
import {WebSocketAPI} from "./WebSocketAPI";
import {BookService} from "../services/book.service";
import {ClientService} from "../services/client.service";
import {FormBuilder} from "@angular/forms";
import {GreetingService} from "../services/greeting.service";


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  // @ts-ignore
  webSocketAPI: WebSocketAPI;
  greeting: any;
  name: any;

  stringList: string[] = []

  constructor(private greetingService: GreetingService){}

  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new ChatComponent(this.greetingService));
  }

  connect(){
    this.webSocketAPI._connect();
  }

  disconnect(){
    this.webSocketAPI._disconnect();
  }

  sendMessage(){
    this.webSocketAPI._send(this.name);
  }

  handleMessage(name: any){
    this.greeting = name;
  }

  seeInFrontend(){
    this.greetingService.getMessageInFrontend().subscribe(
      (s: any) => {
        this.stringList = s
      }

    ),
      (_error: Error) => {

      }
  }

}
