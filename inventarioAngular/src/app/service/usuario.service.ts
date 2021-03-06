import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {  
  constructor(private http: HttpClient) { }

  getFindAll():Observable<any>{
    return this.http.get('http://localhost:9090/api/usuarios/findAll');
  }
}
