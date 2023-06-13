import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { MatTableDataSource } from '@angular/material/table';
import { Router, NavigationEnd } from '@angular/router';
import { untilDestroyed } from '@ngneat/until-destroy';
import { delay, filter } from 'rxjs/operators';
import { Producto } from 'src/app/interface/producto.interface';
import { ProductoService } from 'src/app/service/producto-service';

@Component({
  selector: 'app-home',
  templateUrl: './producto.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  productoList:  Producto[] = [];
  numPages = 0;

  public displayedColumns: string[] = ['ID', 'Nombre', 'Tipo', 'Precio'];
  public columnsToDisplay: string[] = [...this.displayedColumns, 'actions'];
  public dataSource!: MatTableDataSource<Producto>;


  constructor(private observer: BreakpointObserver, private router: Router, private productoService : ProductoService) {}
  ngOnInit(): void {
    this.getProducto(0)
    
  }


  getProducto(page : number){
    this.productoService.getProductos(page).subscribe(resp =>{
      this.productoList = resp.content
      this.numPages = Math.ceil(resp.totalPages);
      this.dataSource.data = resp.content
      console.log(this.productoList)
      console.log("Jhony walker")
      console.log(resp.content)
    })

  }

  ngAfterViewInit() {
    this.observer
      .observe(['(max-width: 800px)'])
      .pipe(delay(1), untilDestroyed(this))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });

    this.router.events
      .pipe(
        untilDestroyed(this),
        filter((e) => e instanceof NavigationEnd)
      )
      .subscribe(() => {
        if (this.sidenav.mode === 'over') {
          this.sidenav.close();
        }
      });
  }
}
