package com.ng.app.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {
    TextView tvResult;
    EditText editText;
    Button btnOk;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);

        //Bundle bundle = intent.getBundleExtra("cBundle");
        //int x = bundle.getInt("x");
        //int y = bundle.getInt("y");
        //int z = bundle.getInt("z");

        //ordinateSerializable c2 = (CoordinateSerializable) intent.getSerializableExtra("xSerializable");

        //CoordinateParcelable c3 = intent.getParcelableExtra("cParcelable");

        initInstances();

    }

    private void initInstances() {
        tvResult = (TextView) findViewById(R.id.tvResult);
        editText = (EditText) findViewById(R.id.editText);
        btnOk = (Button) findViewById(R.id.btnOk);
        tvResult.setText("Result = " + sum);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", editText.getText().toString());
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.face_out);
    }
}
