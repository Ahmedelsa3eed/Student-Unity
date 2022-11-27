import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountComponent } from './account/account.component';
import { AccountPageComponent } from './account-page/account-page.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    AccountPageComponent,
    NavigationBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
