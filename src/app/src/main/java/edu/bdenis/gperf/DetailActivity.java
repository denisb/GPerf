package edu.bdenis.gperf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView wvDetail = (WebView) findViewById(R.id.webViewDetail);
        wvDetail.loadUrl("file:///android_asset/index.html");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true; //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home:
                intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.sort:
                intent = new Intent(DetailActivity.this, SortActivity.class);
                startActivity(intent);
                return true;
            case R.id.quiz:
                intent = new Intent(DetailActivity.this, QuizActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item); } }
}
