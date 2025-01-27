import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // Makes the service available throughout the app
})
export class RecipeService {
  private baseUrl = 'http://localhost:8080/recipes';

  constructor(private http: HttpClient) {}

  searchRecipes(keyword: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/search?keyword=${keyword}`);
  }

  getAllRecipes(page: number = 0, size: number = 6): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}?page=${page}&size=${size}`);
  }



  getRecipeDetails(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}


