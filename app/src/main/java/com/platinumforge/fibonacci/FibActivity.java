package com.platinumforge.fibonacci;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class FibActivity extends ActionBarActivity implements OnClickListener
{
    private EditText input;
    private RadioGroup type;
    private TextView output;

    static
    {
        System.loadLibrary("com_platinumforge_fibonacci_FibLib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fib);
        this.input = (EditText) super.findViewById(R.id.input);
        this.type = (RadioGroup) super.findViewById(R.id.type);
        this.output = (TextView) super.findViewById(R.id.output);
        Button button = (Button) super.findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick (View view)
    {
        String s = this.input.getText().toString();
        if (TextUtils.isEmpty(s))
            return;

        final ProgressDialog dialog = ProgressDialog.show(this, "", "Calculating....", true );
        final long n = Long.parseLong(s);
        new AsyncTask<Void, Void, String>()
        {
            @Override
            protected String doInBackground(Void... params)
            {
                long result = 0;
                long t = System.currentTimeMillis();
                switch (FibActivity.this.type.getCheckedRadioButtonId())
                {
                    case R.id.type_fib_jr:
                        result = FibLib.fibJR(n);
                        break;
                    case R.id.type_fib_nr:
                        result = FibLib.fibNR(n);
                        break;
                    case R.id.type_fib_ji:
                        result = FibLib.fibJI(n);
                        break;
                    case R.id.type_fib_ni:
                        result = FibLib.fibNI(n);
                        break;
                }
                t = System.currentTimeMillis() - t;
                return String.format("Fib(%d) = %d in %d ms", n, result, t);
            }

            @Override
            protected void onPostExecute(String result)
            {
                dialog.dismiss();
                FibActivity.this.output.setText(result);
            }
        }.execute();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fib, menu);
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
}
