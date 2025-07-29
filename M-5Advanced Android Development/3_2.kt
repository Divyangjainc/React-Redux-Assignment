class MusicPlayerActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private val audioUrl = "https://yourserver.com/song.mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        playMusicFromUrl(audioUrl)
    }

    private fun playMusicFromUrl(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            prepareAsync()
            setOnPreparedListener { start() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
