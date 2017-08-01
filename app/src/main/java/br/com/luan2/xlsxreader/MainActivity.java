package br.com.luan2.xlsxreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.poiji.internal.Poiji;
import com.poiji.internal.PoijiOptions;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    128);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            File filePath = new File(Environment.getExternalStoragePublicDirectory("").getAbsolutePath() + "/ring.wav" );

            FileInputStream is = new FileInputStream(filePath);

            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(is.getFD());
            mp.prepare();
            mp.start();

            File mFile = new File(Environment.getExternalStoragePublicDirectory("").getAbsolutePath() + "/employees.xlsx");



            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(1).build();
            List<Employee> employees = Poiji.fromExcel(mFile, Employee.class, options);
            Employee firstEmployee = employees.get(0);
        } catch (Exception e) {
            Log.e("e",e.getMessage());
        }
    }
}
