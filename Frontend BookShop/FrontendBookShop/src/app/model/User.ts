// @ts-ignore
import {Role} from "../model/Role";
export class User{
  id: number | undefined;
  firstName: string | undefined;
  lastName: string | undefined;
  email: string | undefined;
  password: string | undefined;
  role: Role | undefined;
  userLogged: boolean | undefined;

}
