import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";
import {Book} from "../model/Book";

@Injectable({
  providedIn: 'root'
})
export class GreetingService {
  baseURL:string="http://localhost:8082/messageInFrontend";
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream=new BehaviorSubject<any>(null);
  }

  public getMessageInFrontend(){
    return this.httpClient.get<string[]>(this.baseURL)
  }
}
