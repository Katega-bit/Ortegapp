import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Producto } from 'src/app/interface/producto.interface';
import { User } from 'src/app/interface/user.interface';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  displayedColumns: string[] = ['Id', 'Username', 'FullName','Email', 'Actions'];
  
  // TODO DATA RESPONSE
  dataSource!: MatTableDataSource<User>;
  
  // NEVER FALSE
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;



  users: User[] = [];
  index = 0;


  totalPages: number = 0;
  totalElements: number = 0;



  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  
  constructor(private userService : UserService){
    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource<User>();
  }
  
  ngOnInit(): void {
    this.getAllUsersByPageIndex(this.index);
  }


  getAllUsersByPageIndex(page : number){
    this.userService.getUsers(page).subscribe((res => {
      this.users = res.content;
      this.dataSource.data = this.users;
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
