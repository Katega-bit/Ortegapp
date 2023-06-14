export interface ProductoResponse {
    content: Producto[]
    totalElements: number
    totalPages: number
    page: number
  }
  
  export interface Producto {
    id: number
    nombre: string
    foto: string
    tipo: string
    descripcion: string
    precio: number
   // likes: any[]
    //comentarios: any[]
  }
  