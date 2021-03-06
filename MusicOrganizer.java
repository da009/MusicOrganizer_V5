import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    // Boolean para saber si se esta repriduciendo.
    private boolean playingComplete;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer(String carpetaDeAudios)
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary(carpetaDeAudios);
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        playingComplete = false;
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(playingComplete == true)
        {
            System.out.println("Se esta reproduciendo un track completo, no se puede cambiar de track.");
        }
        
        if(indexValid(index)) {
            Track track = tracks.get(index);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            tracks.get(0).playCountInc();
            playingComplete = true;
        }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(playingComplete == true)
        {
            System.out.println("Se esta reproduciendo un track completo, no se puede cambiar de track.");
        }
        
        if(tracks.size() > 0) {
            player.startPlaying(tracks.get(0).getFilename());
            tracks.get(0).playCountInc();
            playingComplete = true;
        }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        playingComplete = false;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    /**
     * Busca la cadena introducida entre los nombres que tiene y devuelve el n�mero.
     */
    public void findTitle(String titulo)
    {
        for(Track track : tracks)
        {
            if (track.getTitle().contains(titulo))
            {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Informa por pantalla de si en este momento se est� reproduciendo un track completo o si no.
     */
    public void isPlaying()
    {
        if (playingComplete == true)
        {
            System.out.println("Se esta reproduciendo una canci�n");
        }
        else
        {
            System.out.println("No se esta reproduciendo ninguna canci�n");
        }
    }
    
    /**
     * Da los detalles de todos los tracks.
     */
    public void listAllTrackWithIterator()
    {
        Iterator<Track> listIt = tracks.iterator();
        while (listIt.hasNext() == true)
        {
            Track tracks = listIt.next();
            System.out.println(tracks.getDetails());
        }
    }
    
    /**
     * Elimina un track buscando por el artista.
     */
    public void removeByArtist(String artista)
    {
        Iterator<Track> listIt = tracks.iterator();
        while (listIt.hasNext() == true)
        {
            Track tracks = listIt.next();
            if (tracks.getArtist().contains(artista))
            {
                listIt.remove();
            }
        }
    }
    
    /**
     * Elimina un track buscando por el t�tulo.
     */
    public void removeByTitle(String titulo)
    {
        Iterator<Track> listIt = tracks.iterator();
        while (listIt.hasNext() == true)
        {
            Track tracks = listIt.next();
            if (tracks.getTitle().contains(titulo))
            {
                listIt.remove();
            }
        }
    }
    
    /**
     * Reproduce una canci�n aleatoria.
     */
    public void playRandom()
    {
        Random random = new Random();
        int max = tracks.size();
        int ind = random.nextInt(max);
        player.startPlaying(tracks.get(ind).getFilename());
        tracks.get(random.nextInt(max)).playCountInc();
    }
    
    /**
     * Permite reproducir los primeros segundos de cada canci�n en orden aleatorio
     */
    public void playShuffle()
    {
        Collections.shuffle(tracks);
        for(Track track : tracks)
        {
            System.out.println(track.getDetails());
            track.playCountInc();
            player.playSample(track.getFilename());
        }
    }
    
    /**
     * que tenga la misma funcionalidad que el anterior pero que est� basado en hacer una copia del ArrayList 
     * contenido en el atributo tracks. Una vez hecha la copia, podemos seleccionar aleatoriamente una canci�n 
     * de la copia, reproducirla y eliminarla de la lista.
     */
    public void playShuffle2()
    {
        Collections.shuffle(tracks);
        ArrayList<Track> copia = new ArrayList<>();
        copia = (ArrayList)tracks.clone();
        for(Track track : tracks)
        {
            System.out.println(track.getDetails());
            track.playCountInc();
            player.playSample(track.getFilename());
        }
    }
}