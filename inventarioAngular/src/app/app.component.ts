import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { InventarioRegistro } from './model/InventarioRegistro';
import { Parametros } from './model/Parametros';
import { Producto } from './model/Producto';
import { Usuario } from './model/Usuario';
import { InventarioRegistroService } from './service/inventario-registro.service';
import { ProductoService } from './service/producto.service';
import { UsuarioService } from './service/usuario.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'inventarioAngular';
  listaInventario: InventarioRegistro[];
  listaUsuarios: Usuario[];
  listaProductos: Producto[];
  parametro: Parametros;
  inventarioRegistro: InventarioRegistro;
  inventarioDialog: boolean;
  confirmacionDialog: boolean;
  idUsuarioModificador: number;
  listaTipo: any[];
  producto: Producto;
  productoDialog: boolean;

  constructor(private productoService: ProductoService, private inventarioService: InventarioRegistroService, private usuarioService: UsuarioService, private messageService: MessageService) {
    this.parametro = new Parametros();
    this.inventarioDialog = false;
    this.productoDialog=false;
    this.listaTipo = [{label:'INGRESO', value:'INGRESO'},
    {label:'SALIDA', value:'SALIDA'}];
  }

  ngOnInit() {

    this.usuarioService.getFindAll().subscribe(
      (resp) => {
        console.log(resp);
        this.listaUsuarios = resp;
      }
    );

    this.productoService.getFindAll().subscribe(
      (resp) => {
        console.log(resp);
        this.listaProductos = resp;
      }
    );
  }

  consultarInventario() {
    this.inventarioService.consutarInventario(this.parametro).subscribe(
      (resp) => {
        console.log(resp);
        this.listaInventario = resp;
      },
      (error) => {
        this.listaInventario = null;
        this.messageService.add({ severity: 'error', summary: error.error.mensaje, sticky: true });
      }
    );
  }

  hideDialog() {
    this.inventarioDialog = false;
  }

  abrirDialogo() {
    this.inventarioRegistro = new InventarioRegistro();
    this.inventarioDialog = true;
  }

  saveRegistro() {
    if (this.inventarioRegistro.idregistro == null) {
      this.inventarioService.guardarInventario(this.inventarioRegistro).subscribe(
        (resp) => {
          console.log(resp);
          this.hideDialog();
          this.inventarioRegistro = null;
          this.messageService.add({ severity: 'success', summary: "Registro creado con exito", sticky: true });
        },
        (error) => {
          console.log(error.error.mensaje);
          this.messageService.add({ severity: 'error', summary: error.error.mensaje, sticky: true });
        }
      );
    } else {
      this.inventarioService.editarInventario(this.inventarioRegistro).subscribe(
        (resp) => {
          console.log(resp);
          this.hideDialog();
          this.inventarioRegistro = null;
          this.messageService.add({ severity: 'success', summary: "Registro editado con exito", sticky: true });
        },
        (error) => {
          console.log(error.error.mensaje);
          this.messageService.add({ severity: 'error', summary: error.error.mensaje, sticky: true });
        }
      );
    }
  }

  editarInventario(resgistro: InventarioRegistro) {
    this.inventarioRegistro = { ...resgistro };
    this.inventarioRegistro.fechaingresoegreso = new Date(resgistro.fechaingresoegreso);
    this.inventarioDialog = true;
  }

  dialogoBorrarInventario(resgistro: InventarioRegistro){
    this.inventarioRegistro = { ...resgistro };
    this.confirmacionDialog=true;
  }

  hideBorradoDialog(){
    this.confirmacionDialog=false;
  }

  confirmarBorrado(){
    this.inventarioService.borrarInventario(this.inventarioRegistro, this.idUsuarioModificador).subscribe(
      (resp) => {
        console.log(resp);
        this.hideBorradoDialog();
        this.inventarioRegistro = null;
        this.messageService.add({ severity: 'success', summary: "Registro borrado con exito", sticky: true });
        this.consultarInventario2();
      },
      (error) => {
        console.log(error.error.mensaje);
        this.messageService.add({ severity: 'error', summary: error.error.mensaje, sticky: true });
      }
    );
    
  }

  consultarInventario2() {
    this.inventarioService.consutarInventario(this.parametro).subscribe(
      (resp) => {
        console.log(resp);
        this.listaInventario = resp;
      },
      (error) => {
        this.listaInventario = null;
      }
    );
  }

  hideProductoDialog(){
    this.productoDialog=false;
  }

  abrirProductoDialogo(){
    this.producto =new Producto();
    this.productoDialog = true;
  }

  saveProducto(){
    if (this.producto.idproducto == null) {
      this.productoService.guardarProducto(this.producto).subscribe(
        (resp) => {
          console.log(resp);
          this.productoService.getFindAll().subscribe(
            (resp) => {
              this.hideProductoDialog();
              this.listaProductos = resp;
              this.messageService.add({ severity: 'success', summary: "Producto creado con exito", sticky: true });
            }
          );
        },
        (error) => {
          this.messageService.add({ severity: 'error', summary: error.error.mensaje, sticky: true });
        }
      );
    }else{
      this.productoService.modificarProducto(this.producto).subscribe(
        (resp) => {
          console.log(resp);
          this.productoService.getFindAll().subscribe(
            (resp) => {
              this.hideProductoDialog();
              this.listaProductos = resp;
              this.messageService.add({ severity: 'success', summary: "Producto editado con exito", sticky: true });
            }
          );
        },
        (error) => {
          this.messageService.add({ severity: 'error', summary: error.error.mensaje, sticky: true });
        }
      );
    }
    
  }

}
