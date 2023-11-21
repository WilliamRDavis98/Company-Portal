import { Component } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent {
  admin = true;

  handleLogout = () => {
    console.log('logout called');
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
