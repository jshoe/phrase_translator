package com.example.jonathan.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.app.ActionBar;
import android.content.Context;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.jonathan.myapplication.MESSAGE";

    TextView phrase_text;

    String[] hello = new String[] {"Hello", "Hola", "您好 (Nín hǎo)"};
    String[] how_much = new String[] {"How much", "Cuánto", "多少 (Duōshǎo)"};
    String[] please = new String[] {"Please", "Por favor", "請 (Qǐng)"};
    String[] thank_you = new String[] {"Thank you", "Gracias", "謝謝 (Xièxiè)"};
    String[] goodbye = new String[] {"Goodbye", "Adiós", "再見 (Zàijiàn)"};

    String[][] phrase_array = {hello, how_much, please, thank_you, goodbye};

    int source_lang_index = 0;
    int target_lang_index = 0;
    int cur_phrase = 0;
    ArrayAdapter<CharSequence> eng_adapter;
    ArrayAdapter<CharSequence> spa_adapter;
    ArrayAdapter<CharSequence> man_adapter;
    Spinner phrase_spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SOURCE LANGUAGE SPINNER
        Spinner source_lang_spin = (Spinner) findViewById(R.id.source_lang_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        source_lang_spin.setAdapter(adapter);

        eng_adapter = ArrayAdapter.createFromResource(this, R.array.eng_array, android.R.layout.simple_spinner_item);
        spa_adapter = ArrayAdapter.createFromResource(this, R.array.spa_array, android.R.layout.simple_spinner_item);
        man_adapter = ArrayAdapter.createFromResource(this, R.array.man_array, android.R.layout.simple_spinner_item);
        OnItemSelectedListener langSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source_lang_index = position;
                switch (position) {
                    case 0:
                        eng_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        phrase_spin.setAdapter(eng_adapter);
                        break;
                    case 1:
                        spa_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        phrase_spin.setAdapter(spa_adapter);
                        break;
                    case 2:
                        man_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        phrase_spin.setAdapter(man_adapter);
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        source_lang_spin.setOnItemSelectedListener(langSelectedListener);

        // TARGET LANGUAGE SPINNER
        final Spinner target_lang_spin = (Spinner) findViewById(R.id.target_lang_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.tar_lang_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        target_lang_spin.setAdapter(adapter2);
        phrase_text = (TextView) findViewById(R.id.output);
        OnItemSelectedListener langSelectedListener2 = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                target_lang_index = position;
                if (position == 3) {
                    phrase_text.setText(phrase_array[cur_phrase][0] + " (English)\n" + phrase_array[cur_phrase][1] + " (Spanish)\n" + phrase_array[cur_phrase][2] + " (Mandarin)");
                }
                else {
                    phrase_text.setText(phrase_array[cur_phrase][target_lang_index]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        target_lang_spin.setOnItemSelectedListener(langSelectedListener2);

        // PHRASE SELECT SPINNER
        phrase_spin = (Spinner) findViewById(R.id.phrase_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.eng_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phrase_spin.setAdapter(adapter3);
        OnItemSelectedListener langSelectedListener3 = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cur_phrase = position;
                if (target_lang_index == 3) {
                    phrase_text.setText(phrase_array[cur_phrase][0] + " (English)\n" + phrase_array[cur_phrase][1] + " (Spanish)\n" + phrase_array[cur_phrase][2] + " (Mandarin)");
                } else {
                    phrase_text.setText(phrase_array[position][target_lang_index]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        phrase_spin.setOnItemSelectedListener(langSelectedListener3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Intent intent = new Intent(this, DisplayMessageActivity.class);
        // EditText editText = (EditText) findViewById(R.id.edit_message);
        // String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        // startActivity(intent);
    }
}
