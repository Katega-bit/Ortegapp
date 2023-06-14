export interface UserResponse {
    content: User[]
    totalElements: number
    totalPages: number
    page: number
  }
  
  export interface User {
    id: string
    username: string
    avatar: string
    fullName: string
    email: string
    createdAt: string
  }
  