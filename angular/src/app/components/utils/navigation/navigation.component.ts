import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

    // variable to determin of menu should be shown or not
    isShow = true;

    // toggle function that toggles between true and false
    toggleDisplay() {
        this.isShow = !this.isShow;
    }

    constructor() {
    }

    ngOnInit(): void {
    }

    logout(){
        window.localStorage.clear()
        window.location.reload()
    }


}
