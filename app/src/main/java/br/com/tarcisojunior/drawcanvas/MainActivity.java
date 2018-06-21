package br.com.tarcisojunior.drawcanvas;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int status; //{ 0-Idle, 1-mark, 2-draw}
    Button btnBack, btnMark, btnDraw, btnBackGround, btnClear;
    TextView txtStatus;
    MyCanvas imageView;

    private static String TAG="Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStatus = findViewById(R.id.txtStatus);

        imageView = findViewById(R.id.imageView);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = MyCanvas.NONE;
                imageView.setMode(MyCanvas.NONE);
                updateStatus();
            }
        });

        btnMark = findViewById(R.id.btnMark);
        btnMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = MyCanvas.MARK;
                imageView.setMode(MyCanvas.MARK);
                updateStatus();
            }
        });

        btnDraw = findViewById(R.id.btnDraw);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = MyCanvas.DRAW;
                imageView.setMode(MyCanvas.DRAW);
                updateStatus();
            }
        });

        btnBackGround = findViewById(R.id.btnBackground);
        btnBackGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setBackgroundResource(R.mipmap.ic_launcher);
            }
        });

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setBackgroundColor(getResources().getColor(android.R.color.white));
                imageView.clear();
            }
        });
    }

    private void updateStatus(){
        switch(status){
            case 0: txtStatus.setText("Aguardando");
                    break;
            case 1: txtStatus.setText("Marcação");
                    break;
            case 2: txtStatus.setText("Desenho");
                    break;
        }

        btnBack.setEnabled(status != 0);
        btnMark.setEnabled(status == 0);
        btnDraw.setEnabled(status == 0);
    }
}
