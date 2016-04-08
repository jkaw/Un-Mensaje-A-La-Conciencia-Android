package andoniantechnologies.videoviewapplication;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    VideoView mVideoView;

    String VideoURL = "http://video.conciencia.net/2016/2016abr07.mp4";
    //"http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        mVideoView = (VideoView) findViewById(R.id.VideoView);

        mProgressDialog = new ProgressDialog(VideoViewActivity.this);

        mProgressDialog.setTitle("Un Mensaje A La Conciencia");

        mProgressDialog.setMessage("Buffering....");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);

        mProgressDialog.show();

        try {
            MediaController mediaController = new MediaController(VideoViewActivity.this);

            mediaController.setAnchorView(mVideoView);
            Uri video = Uri.parse(VideoURL);
            mVideoView.setMediaController(mediaController);
            mVideoView.setVideoURI(video);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mProgressDialog.dismiss();
                mVideoView.start();
            }
        });
    }


}
