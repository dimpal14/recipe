import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { routes } from './app/app.routes'; // Ensure app.routes.ts exists and is configured properly

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes), // Provide the routes
    importProvidersFrom(HttpClientModule, FormsModule), // Import HttpClientModule and FormsModule
  ],
}).catch((err) => console.error(err));




