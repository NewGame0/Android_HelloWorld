package com.ng.app.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText number1,number2;
    TextView result;
    Button btnCalculator;
    RadioGroup radioGroup;
    CustomViewGroup viewGroup1,viewGroup2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getBoolean(R.bool.portrait_only))
        {
            // Fix screen orientation to Portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_main);

        initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(MainActivity.this,
                "Width = " + width + "Height = " + height,
                Toast.LENGTH_LONG).show();


    }

    private void initInstances(){
        number1 = (EditText) findViewById(R.id.editText1);
        number2 = (EditText) findViewById(R.id.editText2);
        result = (TextView) findViewById(R.id.textResult);
        btnCalculator = (Button) findViewById(R.id.btnCalculator);
        btnCalculator.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.rbGroup);

        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");

    }

    @Override
    public void onClick(View view) {
        if(view == btnCalculator)
        {
            int num1 = 0;
            int num2 = 0;
            int resultNum = 0;
            try {
                num1 =Integer.parseInt(number1.getText().toString());
            }
            catch (NumberFormatException e)
            {

            }
            try {
                num2 = Integer.parseInt(number2.getText().toString());
            }
            catch (NumberFormatException e)
            {

            }

            int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
            if(index == 0)
            {
                resultNum = num1 + num2;
            }
            else if(index == 1)
            {
                resultNum = num1 - num2;
            }
            else if(index == 2)
            {
                resultNum = num1 * num2;
            }
            else if(index == 3)
            {
                if(num2 != 0)
                    resultNum = num1 / num2;
            }

            result.setText("= " + resultNum);
            Log.d("Calculation", "Result = " + resultNum);
            Toast.makeText(MainActivity.this,
                    "Result = " + resultNum,
                    Toast.LENGTH_LONG)
                    .show();

            Intent intent = new Intent(MainActivity.this,
                    SecondActivity.class);
            intent.putExtra("result",resultNum);

            // Playground
            Coordinate c1 = new Coordinate();
            c1.x = 5;
            c1.y = 10;
            c1.z = 20;
            Bundle bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBubdle", bundle);

            // Serializ
            CoordinateSerializable c2  = new CoordinateSerializable();
            c2.x = 5;
            c2.y = 10;
            c2.z = 20;
            intent.putExtra("cSerializable", c2);

            // Parcelable
            CoordinateParcelable c3 = new CoordinateParcelable();
            c3.x = 5;
            c3.y = 10;
            c3.z = 20;
            intent.putExtra("cParcelable", c3);

            startActivityForResult(intent, 12345);
            overridePendingTransition(R.anim.fade_in, R.anim.face_out);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if it is aresult from SecondActivity
        if(requestCode == 12345)
        {
            if(resultCode == RESULT_OK)
            {
                String test = data.getStringExtra("result");
                Toast.makeText(MainActivity.this,
                        ">> " + test,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_setting)
        {
            Toast.makeText(MainActivity.this,
                    "Test Menue",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save things here
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore things here
    }
}


