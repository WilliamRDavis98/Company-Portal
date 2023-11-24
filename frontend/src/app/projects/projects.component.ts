import { Component, ViewChild } from '@angular/core';
import { ModalComponent } from '../components/modals/modal/modal.component';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css'],
})
export class ProjectsComponent {
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;
  modalType: string = 'create-edit-project';
  projectId: number | undefined;

  teamId = 5;
  isAdmin = true;
  projects = [
    {
      id: 1,
      name: 'project1',
      timestamp: 1699938793 * 1000,
      // active: true,
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dignissim enim sit amet venenatis urna cursus. Odio ut enim blandit volutpat. Malesuada bibendum arcu vitae elementum curabitur vitae nunc. Porttitor rhoncus dolor purus non enim praesent. Consectetur libero id faucibus nisl tincidunt eget. Quam vulputate dignissim suspendisse in est. Congue eu consequat ac felis donec et odio pellentesque. A lacus vestibulum sed arcu non odio euismod lacinia at. Eget nullam non nisi est sit. Varius duis at consectetur lorem donec. Cursus in hac habitasse platea dictumst quisque. Enim tortor at auctor urna nunc id cursus. Cursus turpis massa tincidunt dui ut. Sagittis vitae et leo duis ut diam quam. Commodo sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. At urna condimentum mattis pellentesque id nibh.',
    },
    {
      id: 2,
      name: 'project2',
      timestamp: 1700457193 * 1000,
      // active: false,
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum',
    },
    {
      id: 3,
      name: 'project3',
      timestamp: 1700522344 * 1000,
      // active: true,
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra vel turpis nunc eget lorem dolor sed viverra. Mauris augue neque gravida in fermentum. Viverra nibh cras pulvinar mattis nunc sed. Quam pellentesque nec nam aliquam sem et tortor. Egestas congue quisque egestas diam. Iaculis eu non diam phasellus. Turpis egestas sed tempus urna. Quisque egestas diam in arcu cursus. Lacus sed viverra tellus in hac habitasse platea dictumst. Duis convallis convallis tellus id interdum. Etiam sit amet nisl purus. Viverra nibh cras pulvinar mattis. In fermentum et sollicitudin ac orci phasellus egestas. Porta non pulvinar neque laoreet suspendisse interdum consectetur.',
    },
    {
      id: 4,
      name: 'project4',
      timestamp: Date.now(),
      // active: true,
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra vel turpis nunc eget lorem dolor sed viverra. Mauris augue neque gravida in fermentum. Viverra nibh cras pulvinar mattis nunc sed. Quam pellentesque nec nam aliquam sem et tortor. Egestas congue quisque egestas diam. Iaculis eu non diam phasellus. Turpis egestas sed tempus urna. Quisque egestas diam in arcu cursus. Lacus sed viverra tellus in hac habitasse platea dictumst. Duis convallis convallis tellus id interdum. Etiam sit amet nisl purus. Viverra nibh cras pulvinar mattis. In fermentum et sollicitudin ac orci phasellus egestas. Porta non pulvinar neque laoreet suspendisse interdum consectetur.',
    },
  ];

  constructor() {}

  ngOnInit(): void {
    if (this.teamId) {
      this.getProjects(this.teamId);
    }
  }

  openNewModal() {
    this.projectId = undefined; // or null
    this.modalComponent.setProjectId(undefined);
    this.modalComponent.toggleModal();
  }

  openEditModal(projectId: number) {
    this.projectId = projectId;
    this.modalComponent.setProjectId(projectId);
    this.modalComponent.toggleModal();
  }

  getProjects = async (id: number) => {};
}
