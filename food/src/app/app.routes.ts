import { Routes } from '@angular/router';
import { RecipeSearchComponent } from './recipe-search/recipe-search.component';
import { RecipeDetailsComponent } from './recipe-details/recipe-details.component';

export const routes: Routes = [
  { path: '', component: RecipeSearchComponent },
  { path: 'recipe/:id', component: RecipeDetailsComponent },
];

  
