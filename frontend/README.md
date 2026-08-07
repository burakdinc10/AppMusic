# AppMusic Angular Frontend

Bu klasor mevcut Spring Boot backend'i icin ayrik bir Angular arayuzu icerir.

## Kurulum

1. Backend'i proje kokunden calistir:
   `./mvnw spring-boot:run`
2. Frontend klasorune gec:
   `cd frontend`
3. Paketleri kur:
   `npm install`
4. Angular uygulamasini baslat:
   `npm start`

Not:
7 Agustos 2026 tarihli Angular uyumluluk tablosuna gore Angular `19.2.x`,
Node `^18.19.1`, `^20.11.1` veya `^22.0.0` ile uyumludur.
Bu proje Node `v22.22.2` icin Angular 19'a sabitlenmistir.

Temiz kurulum icin mevcut `node_modules` klasorunu silip yeniden `npm install` calistirman gerekir.

## Baglanan endpoint'ler

- `GET /api/song`
- `POST /api/song/create`
- `GET /api/artists`
- `GET /api/category/all`

Varsayilan olarak Angular uygulamasi `http://localhost:4200`, backend ise `http://localhost:8080` adresini kullanir.
