import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-drag-drop',
  templateUrl: './drag-drop.component.html',
  styles: ['./drag-drop.component.css']
})
export class DragDropComponent implements OnInit {

  constructor() { }


  drop(ev) {
    ev.preventDefault();
    let data = ev.dataTransfer.getData('text');
    ev.target.appendChild(document.getElementById(data));
    
  }

  allowDrop(ev) {
    ev.preventDefault();
  }

  drag(ev) {
    ev.dataTransfer.setData('text', ev.target.id);
  }

  ngOnInit() {
  }

}
