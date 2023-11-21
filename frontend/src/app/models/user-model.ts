export interface User {
    id: number,
    username: string,
    password?: string,
    firstName: string,
    lastName: string,
    email: string,
    phoneNumber?: string,
    active: boolean,
    admin: boolean,
    status: string
}