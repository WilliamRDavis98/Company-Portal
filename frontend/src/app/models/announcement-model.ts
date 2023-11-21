import { Time } from "@angular/common";

export interface Announcement {
    id: number,
    date: Time, //Time Object of { Hours: number, Minutes: number }
    title: String,
    message: String,
    company: number, //The Company ID
    author: number // The User ID of creator
}