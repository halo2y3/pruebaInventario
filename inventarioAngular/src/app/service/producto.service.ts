import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../model/Producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private http: HttpClient) { }

  getFindAll():Observable<any>{
    return this.http.get('http://localhost:9090/api/productos/findAll');
  }

  guardarProducto(producto: Producto):Observable<any>{
    return this.http.post('http://localhost:9090/api/productos/save', producto);
  }

  modificarProducto(producto: Producto):Observable<any>{
    return this.http.put('http://localhost:9090/api/productos/update', producto);
  }

}