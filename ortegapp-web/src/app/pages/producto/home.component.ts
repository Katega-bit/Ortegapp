import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSort } from '@angular/material/sort';
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
/* export class HomeComponent implements OnInit {

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
} */

export class HomeComponent implements OnInit{
  // TODO COLUMNS NAME
  displayedColumns: string[] = ['Id', 'Nombre', 'Tipo', 'Precio'];
  
  // TODO DATA RESPONSE
  dataSource!: MatTableDataSource<Producto>;
  
  // NEVER FALSE
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;



  products: Producto[] = [];
  index = 0;


  totalPages: number = 0;
  totalElements: number = 0;



  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  
  constructor(private productoService : ProductoService){
    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource<Producto>();
  }
  
  ngOnInit(): void {
    this.getAllProductByPageIndex(this.index);
  }


  getAllProductByPageIndex(page : number){
    this.productoService.getProductos(page).subscribe((res => {
      this.products = res.content;
      this.dataSource.data = this.products;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.totalElements = res.totalElements;
      this.totalPages = res.totalPages;

    }))
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}