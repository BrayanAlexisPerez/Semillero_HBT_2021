import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ComicDTO } from '../../dto/comic-dto';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

/**
 * @description Componente encargado de gestionar la logica para comprar un comic
 * @author Brayan Perez
 * @see SEMILLERO 2021
 */

@Component({
  selector: 'gestionar-compra-comic',
  templateUrl: './gestionar-compra-comic.component.html',
})
export class GestionarCompraComicComponent implements OnInit {

  public comicDTO : ComicDTO;

  public gestionarCompraComicForm : FormGroup;

  constructor(private router : Router,  private activatedRoute: ActivatedRoute) { 
    console.log("componente gestionar-compra-comic");
  }

  ngOnInit(): void{
    let comic : any = this.activatedRoute.snapshot.params;
    this.comicDTO = comic;
  }

  public iniciarNombre() {
    this.gestionarCompraComicForm.controls.nombre.setValue(this.comicDTO.nombre);
    this.gestionarCompraComicForm.controls.nombre.disable();
  }

}
