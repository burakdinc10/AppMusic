import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { forkJoin } from 'rxjs';
import { Artist } from '../models/artist.model';
import { Category } from '../models/category.model';
import { CreateSongRequest } from '../models/create-song-request.model';
import { LoginRequest } from '../models/login-request.model';
import { RegisterRequest } from '../models/register-request.model';
import { Song } from '../models/song.model';

@Injectable({
  providedIn: 'root'
})
export class MusicApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8080/api';

  loadDashboardData() {
    return forkJoin({
      songs: this.getSongs(),
      artists: this.getArtists(),
      categories: this.getCategories()
    });
  }

  getSongs() {
    return this.http.get<Song[]>(`${this.baseUrl}/song`);
  }

  getArtists() {
    return this.http.get<Artist[]>(`${this.baseUrl}/artists`);
  }

  getCategories() {
    return this.http.get<Category[]>(`${this.baseUrl}/category/all`);
  }

  createSong(payload: CreateSongRequest) {
    return this.http.post(`${this.baseUrl}/song/create`, payload, {
      responseType: 'text'
    });
  }

  login(payload: LoginRequest) {
    return this.http.post(`${this.baseUrl}/user/login`, payload, {
      responseType: 'text'
    });
  }

  register(payload: RegisterRequest) {
    return this.http.post(`${this.baseUrl}/user/create`, payload, {
      responseType: 'text'
    });
  }
}
