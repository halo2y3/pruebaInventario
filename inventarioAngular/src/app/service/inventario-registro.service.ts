import { HttpClient } from '@angular/common/http';
import { NullTemplateVisitor } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InventarioRegistro } from '../model/InventarioRegistro';
import { Parametros } from '../model/Parametros';

@Injectable({
  providedIn: 'root'
})
export class InventarioRegistroService {

  constructor(private http: HttpClient) { }

  consutarInventario(param: Parametros):Observable<any>{
    return this.http.post('http://localhost:9090/api/resgistromercancia/findByUsuarioFecha', param);
  }
  
  guardarInventario(registro: InventarioRegistro):Observable<any>{ 
    return this.http.post('http://localhost:9090/api/resgistromercancia/save', registro);
  }
  
  editarInventario(registro: InventarioRegistro):Observable<any>{ 
    return this.http.put('http://localhost:9090/api/resgistromercancia/update', registro);
  }

  borrarInventario(registro: InventarioRegistro, idUsuario: number):Observable<any>{ 
    return this.http.delete('http://localhost:9090/api/resgistromercancia/delete/'+registro.idregistro+"/"+idUsuario);
  }


}
