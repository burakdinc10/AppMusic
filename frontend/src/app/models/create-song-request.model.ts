export interface CreateSongRequest {
  songName: string;
  songTime: string;
  artistId: number | null;
  categoryId: number | null;
}
