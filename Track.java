/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    // Almacena el número de veces que se ha reproducido la canción.
    private int playCount;
    // Calificación para la canción.
    private int rank;
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
    }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
        
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ")" + "Rank: " + rank;
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
        this.playCount = 0;
        this.rank = 0;
    }
    
    /**
     * Constructor del método para almacenar el número de veces que se ha reproducido cada canción.
     */
    public void playCountInc()
    {
        playCount = playCount +1;
    }
    
    /**
     * Devuelve el valor del contador
     */
    public int getCount()
    {
        return playCount;
    }
    
    /**
     * Constructor del método para reiniciar el contador y dejarlo a 0.
     */
    public void playCountRestart()
    {
        playCount = 0;
    }
    
    /**
     * Permite poner una calificación a las canciones, entre 0 y 5;
     */
    public void setRankAsign(int rango)
    {
        if (rango >= 0 && rango <=5)
        {
            rank = rango;
        }
        else
        {
            System.out.println(rango + " no es un valor válido, el valor debe estar entre 0 y 5.");
        }
    }
}
