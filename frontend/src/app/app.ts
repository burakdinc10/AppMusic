import { CommonModule } from '@angular/common';
import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { finalize } from 'rxjs';
import { MusicApiService } from './services/music-api.service';
import { Song } from './models/song.model';
import { Artist } from './models/artist.model';
import { Category } from './models/category.model';
import { CreateSongRequest } from './models/create-song-request.model';
import { LoginRequest } from './models/login-request.model';
import { RegisterRequest } from './models/register-request.model';

const AUTH_STORAGE_KEY = 'appmusic-auth-user';
type AuthMode = 'login' | 'register';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent implements OnInit {
  private readonly api = inject(MusicApiService);

  protected readonly songs = signal<Song[]>([]);
  protected readonly artists = signal<Artist[]>([]);
  protected readonly categories = signal<Category[]>([]);
  protected readonly loading = signal(false);
  protected readonly saving = signal(false);
  protected readonly errorMessage = signal('');
  protected readonly successMessage = signal('');
  protected readonly authMode = signal<AuthMode>('login');
  protected readonly authenticated = signal(false);
  protected readonly currentUserEmail = signal('');
  protected readonly authLoading = signal(false);
  protected readonly authErrorMessage = signal('');
  protected readonly authSuccessMessage = signal('');

  protected readonly model: CreateSongRequest = {
    songName: '',
    songTime: '',
    artistId: null,
    categoryId: null
  };

  protected readonly loginModel: LoginRequest = {
    email: '',
    password: ''
  };

  protected readonly registerModel: RegisterRequest = {
    email: '',
    password: '',
    username: '',
    birthDate: '',
    nationalId: ''
  };

  protected readonly songCountLabel = computed(() => `${this.songs().length} sarki listelendi`);

  ngOnInit(): void {
    const rememberedUser = globalThis.localStorage?.getItem(AUTH_STORAGE_KEY);
    if (rememberedUser) {
      this.authenticated.set(true);
      this.currentUserEmail.set(rememberedUser);
      this.loadDashboard();
    }
  }

  protected loadDashboard(): void {
    if (!this.authenticated()) {
      return;
    }

    this.loading.set(true);
    this.errorMessage.set('');

    this.api.loadDashboardData()
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe({
        next: ({ songs, artists, categories }) => {
          this.songs.set(songs);
          this.artists.set(artists);
          this.categories.set(categories);
        },
        error: () => {
          this.errorMessage.set('API verileri yuklenemedi. Spring Boot uygulamasinin 8080 portunda calistigindan emin ol.');
        }
      });
  }

  protected setAuthMode(mode: AuthMode): void {
    this.authMode.set(mode);
    this.authErrorMessage.set('');
    this.authSuccessMessage.set('');
  }

  protected submitLogin(): void {
    this.authLoading.set(true);
    this.authErrorMessage.set('');
    this.authSuccessMessage.set('');

    this.api.login(this.loginModel)
      .pipe(finalize(() => this.authLoading.set(false)))
      .subscribe({
        next: (message) => {
          if (message.toLocaleLowerCase('tr-TR').includes('başarıyla')) {
            this.completeLogin(this.loginModel.email, 'Giris basarili. Muzik ekranina yonlendiriliyorsun.');
            return;
          }

          this.authErrorMessage.set(message);
        },
        error: () => {
          this.authErrorMessage.set('Giris yapilamadi. Email ve sifre bilgilerini kontrol et.');
        }
      });
  }

  protected submitRegister(): void {
    this.authLoading.set(true);
    this.authErrorMessage.set('');
    this.authSuccessMessage.set('');

    this.api.register(this.registerModel)
      .pipe(finalize(() => this.authLoading.set(false)))
      .subscribe({
        next: (message) => {
          if (message.toLocaleLowerCase('tr-TR').includes('başarılı')) {
            this.authSuccessMessage.set('Kayit tamamlandi. Simdi giris yapabilirsin.');
            this.loginModel.email = this.registerModel.email;
            this.loginModel.password = this.registerModel.password;
            this.resetRegisterForm();
            this.authMode.set('login');
            return;
          }

          this.authErrorMessage.set(message);
        },
        error: () => {
          this.authErrorMessage.set('Kayit olusturulamadi. Alanlari kontrol edip tekrar dene.');
        }
      });
  }

  protected logout(): void {
    this.authenticated.set(false);
    this.currentUserEmail.set('');
    this.songs.set([]);
    this.artists.set([]);
    this.categories.set([]);
    this.successMessage.set('');
    this.errorMessage.set('');
    globalThis.localStorage?.removeItem(AUTH_STORAGE_KEY);
    this.setAuthMode('login');
  }

  protected submitSong(): void {
    this.saving.set(true);
    this.successMessage.set('');
    this.errorMessage.set('');

    this.api.createSong(this.model)
      .pipe(finalize(() => this.saving.set(false)))
      .subscribe({
        next: (message) => {
          this.successMessage.set(message);
          this.resetForm();
          this.loadSongs();
        },
        error: () => {
          this.errorMessage.set('Sarki eklenemedi. Form verilerini ve artist/kategori secimlerini kontrol et.');
        }
      });
  }

  protected artistName(artistId: number | null): string {
    return this.artists().find((artist) => artist.id === artistId)?.artistName ?? 'Bilinmeyen artist';
  }

  protected categoryName(categoryId: number | null): string {
    return this.categories().find((category) => category.id === categoryId)?.categoryName ?? 'Bilinmeyen kategori';
  }

  private loadSongs(): void {
    this.api.getSongs().subscribe({
      next: (songs) => this.songs.set(songs),
      error: () => {
        this.errorMessage.set('Sarki listesi yenilenemedi.');
      }
    });
  }

  private resetForm(): void {
    this.model.songName = '';
    this.model.songTime = '';
    this.model.artistId = null;
    this.model.categoryId = null;
  }

  private completeLogin(email: string, message: string): void {
    this.authenticated.set(true);
    this.currentUserEmail.set(email);
    this.authSuccessMessage.set(message);
    globalThis.localStorage?.setItem(AUTH_STORAGE_KEY, email);
    this.loadDashboard();
  }

  private resetRegisterForm(): void {
    this.registerModel.email = '';
    this.registerModel.password = '';
    this.registerModel.username = '';
    this.registerModel.birthDate = '';
    this.registerModel.nationalId = '';
  }
}
