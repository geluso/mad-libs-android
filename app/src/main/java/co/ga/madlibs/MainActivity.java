package co.ga.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, ResultActivity.class);
              if (validateInput(intent))  {
                startActivity(intent);
              }
            }
        });
    }

    private class Canary {
        boolean alive = true;
    }

    private boolean validateInput(Intent intent) {
        Canary canary = new Canary();

        // put extra for every single text field so their errors are set too
        putExtraOrFail(intent, R.id.adjective1_edittext, ResultActivity.ADJECTIVE1, canary);
        putExtraOrFail(intent, R.id.adjective2_edittext, ResultActivity.ADJECTIVE2, canary);
        putExtraOrFail(intent, R.id.noun1_edittext, ResultActivity.NOUN1, canary);
        putExtraOrFail(intent, R.id.noun2_edittext, ResultActivity.NOUN2, canary);
        putExtraOrFail(intent, R.id.animals_edittext, ResultActivity.ANIMALS, canary);
        putExtraOrFail(intent, R.id.game_edittext, ResultActivity.GAME, canary);

        return canary.alive;
    }

    private void putExtraOrFail(Intent intent, int viewId, String messageId, Canary canary) {
        EditText editText = (EditText) findViewById(viewId);
        String text = editText.getText().toString();

        if (text.length() > 0) {
            intent.putExtra(messageId, text);
        } else  {
            editText.setError("Field must not be empty.");
            intent.putExtra("canary", false);
            canary.alive = false;
        }
    }
}

