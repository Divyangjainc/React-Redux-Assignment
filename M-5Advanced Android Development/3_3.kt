class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var wakeLock: PowerManager.WakeLock

    private val videoUrl = "https://yourserver.com/video.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoView = findViewById(R.id.videoView)

        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(
            PowerManager.SCREEN_DIM_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP,
            "MyApp::VideoWakeLock"
        )

        videoView.setVideoURI(Uri.parse(videoUrl))
        videoView.setOnPreparedListener {
            wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/)
            it.start()
        }

        videoView.setOnCompletionListener {
            if (wakeLock.isHeld) wakeLock.release()
        }
    }

    override fun onPause() {
        super.onPause()
        if (wakeLock.isHeld) wakeLock.release()
    }
}
