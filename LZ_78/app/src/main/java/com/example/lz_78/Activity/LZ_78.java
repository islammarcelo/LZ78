package com.example.lz_78.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lz_78.Class.LZfun_78;
import com.example.lz_78.R;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.lz_78.R.*;

public class LZ_78 extends AppCompatActivity {
    //declaration
    EditText editText ;
    TextView textViewDict ;
    TextView textViewPair ;
    TextView textViewText;
    LZfun_78 lz;
    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_lz_78);
        //getSupportActionBar().hide();

        editText = findViewById(id.et_input);
        textViewDict = findViewById(id.tv_result_dict);
        textViewPair = findViewById(id.tv_result_pair);
        textViewText = findViewById(id.tv_result_str);
        lz = new LZfun_78();

        inflater();


    }

    private void inflater() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = (View) layoutInflater.inflate(layout.welcome, (ViewGroup)findViewById(R.id.constrain));
        TextView txt = view.findViewById(id.tv_txt);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.FILL,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu){
            Toast.makeText(this,"Exit",Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(LZ_78.this, MainActivity.class);
            //startActivity(intent);
            finish();
        }
        if(id == R.id.reset){
            textViewDict.setText("");
            textViewPair.setText("");
            textViewText.setText("");
            editText.setText("");
            lz.result = "";
            lz = new LZfun_78();
            Toast.makeText(this,"Reset is clicked",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void compress(View view) {

        text = editText.getText().toString();
        if(text.isEmpty()){
            Toast.makeText(this,"Please enter the text first :)",Toast.LENGTH_SHORT).show();

        }
      else {
            //text = editText.getText().toString();
            lz.compress(text);
            for (int i = 0; i < lz.dict.size(); i++) {
                if (i + 1 >= lz.dict.size()) {
                    textViewDict.append(lz.dict.get(i));
                } else {
                    textViewDict.append(lz.dict.get(i) + ",");
                }
            }
            for (int i = 0; i < lz.pair.size(); i++) {
                if (i + 1 >= lz.pair.size()) {
                    textViewPair.append("<" + lz.pair.get(i).getKey() + "," + lz.pair.get(i).getValue() + ">");
                } else {
                    textViewPair.append("<" + lz.pair.get(i).getKey() + "," + lz.pair.get(i).getValue() + ">" + ",");
                }

            }
        }


    }

    public void decompress(View view) {
        if(lz.pair.isEmpty()){
            Toast.makeText(this,"Please do compress first then decompress :)",Toast.LENGTH_SHORT).show();
        }
        else {
            lz.decompress();
            textViewDict.setText("");
            textViewPair.setText("");

            for (int i = 0; i < lz.dict.size(); i++) {
                if (i + 1 >= lz.dict.size()) {
                    textViewDict.append(lz.dict.get(i));
                } else {
                    textViewDict.append(lz.dict.get(i) + ",");
                }
            }

            textViewText.setText(lz.result);
            String string = lz.result;

            if (text.equals(string)) {
                Toast.makeText(this, "Successful operation", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "failed Please make the operation again", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}
