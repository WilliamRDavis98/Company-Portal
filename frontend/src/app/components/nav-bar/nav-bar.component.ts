import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent {
  constructor(router: Router) {
    //this.admin = 'true';
    this.router = router;
  }

  router: Router | null = null;
  admin = sessionStorage.getItem('admin');

  handleLogout = () => {
    sessionStorage.clear;
    this.router?.navigateByUrl('');
  };

  handleCssBorder = () => {
    let nav = document.getElementById('navbar');
    let button = document.getElementById('navbar-toggler');
    let isExpanded = button?.ariaExpanded;
    if (!nav) {
      return;
    }
    nav.style.borderStyle = isExpanded === 'true' ? 'none' : 'solid';
  };
}
