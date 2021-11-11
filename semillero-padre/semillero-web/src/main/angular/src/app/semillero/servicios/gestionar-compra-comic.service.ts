import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { DatosCompraComicDTO } from '../dto/datos-compra-comic-dto copy';
import { AbstractService } from './template.service';

@Injectable({
  providedIn: 'root'
})
export class GestionarCompraComicService extends AbstractService {

  constructor(private injector : Injector, private httpClient : HttpClient) { 
    super(injector);
  }

  public comprarComic(datosCompraComic : DatosCompraComicDTO) : Observable<any> {
    let params = new HttpParams().set('datosCompraComic', JSON.stringify(datosCompraComic));
    return this.httpClient.get(
      'http://localhost:8085/semillero-servicios/rest/gestionarCompraComic/comprarComic',
      {params: params}
    );
  } 

/*   public comprarComic(datosCompraComic : DatosCompraComicDTO) : Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/gestionarCompraComic/comprarComic', datosCompraComic);
  } */
}
