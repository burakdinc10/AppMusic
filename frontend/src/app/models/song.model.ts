export interface Song {
  id: number;
  songName: string;
  songTime: string | null;
  artistId: number | null;
  categoryId: number | null;
}
