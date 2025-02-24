import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import { JwtInterceptor } from './auth/interceptor/JwtInterceptor';
import { employeeRoutes } from './employee/employee.module';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes,withComponentInputBinding()),provideRouter(employeeRoutes),provideHttpClient(withInterceptorsFromDi()),
    {provide: HTTP_INTERCEPTORS,useClass:JwtInterceptor,multi:true}]
};
