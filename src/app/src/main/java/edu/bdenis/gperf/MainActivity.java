package edu.bdenis.gperf;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int tailleDonnees = 10;
    long timeStampMillisInit;
    long timeStampMillisFin;
    String strResultat;

    static {
        System.loadLibrary("native-lib");}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bCalcJava = (Button) findViewById(R.id.buttonCalcVarJava);
        bCalcJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.texteResultat);
                tv.setText("... calcul en cours ...");
                tailleDonnees = getTailleDonnees();
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        int i,n,r;
                        n=1;
                        r=n;
                        for(i=tailleDonnees;i!=0;i--) {
                            if ((r==1) || (r==-34) || (r==-10) || (r==-1)) {n=n+1; r = n;}
                            //if ((r & 1)==1) {r=(r*3+1)/2;} else {r=r/2;}
                            r = nextSyracuse(r);}
                        strResultat = "Calcul fini avec ";
                        strResultat += Integer.toString(n);
                        strResultat += ". ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.texteResultat);
                                //tv.setText(strResultat+Integer.toString(tailleDonnees)+"/"+Long.toString((timeStampMillisFin-timeStampMillisInit))+"ms");}});
                                tv.setText("Calcul fini en "+Long.toString((timeStampMillisFin-timeStampMillisInit))+"ms");}});
                    }}).start();}});

      Button bCalcVar = (Button) findViewById(R.id.buttonCalcVar);
        bCalcVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.texteResultat);
                tv.setText("... calcul en cours ...");
                tailleDonnees = getTailleDonnees();
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        strResultat = stringFromJNICalcVar(tailleDonnees);
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.texteResultat);
                                //tv.setText(strResultat+Integer.toString(tailleDonnees)+"/"+Long.toString((timeStampMillisFin-timeStampMillisInit))+"ms");}});
                                tv.setText("Calcul fini en "+Long.toString((timeStampMillisFin-timeStampMillisInit))+"ms");}});
                    }}).start();}});

    Button bCalcReg = (Button) findViewById(R.id.buttonCalcReg);
        bCalcReg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            TextView tv = (TextView) findViewById(R.id.texteResultat);
            tv.setText("... calcul en cours ...");
            tailleDonnees = getTailleDonnees();
            new Thread(new Runnable() {
                public void run() {
                    timeStampMillisInit = System.currentTimeMillis();
                    strResultat = stringFromJNICalcReg(tailleDonnees);
                    timeStampMillisFin = System.currentTimeMillis();
                    v.post(new Runnable() {
                        public void run() {
                            TextView tv = (TextView) findViewById(R.id.texteResultat);
                            //tv.setText(strResultat+Integer.toString(tailleDonnees)+"/"+Long.toString((timeStampMillisFin-timeStampMillisInit))+"ms");}});
                            tv.setText("Calcul fini en "+Long.toString((timeStampMillisFin-timeStampMillisInit))+"ms");}});
                }}).start();}});}

    public int nextSyracuse(int n) {
        if ((n & 1)==1) {return (n*3+1)/2;}
        else { return n/2;}}

    public int getTailleDonnees() {
        int pTailleDonnees = 0;
        RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton rb5 = (RadioButton) findViewById(R.id.radioButton5);
        RadioButton rb6 = (RadioButton) findViewById(R.id.radioButton6);
        RadioButton rb7 = (RadioButton) findViewById(R.id.radioButton7);
        RadioButton rb9 = (RadioButton) findViewById(R.id.radioButton9);
        if (rb2.isChecked()) {
            pTailleDonnees = 100; }
        else if (rb3.isChecked()) {
            pTailleDonnees = 1000; }
        else if (rb4.isChecked()) {
            pTailleDonnees = 10000; }
        else if (rb5.isChecked()) {
            pTailleDonnees = 100000; }
        else if (rb6.isChecked()) {
            pTailleDonnees = 1000000; }
        else if (rb7.isChecked()) {
            pTailleDonnees = 10000000; }
        else if (rb9.isChecked()) {
            pTailleDonnees = 1000000000; }
        RadioButton rbLog = (RadioButton) findViewById(R.id.radioButtonLog);
        RadioButton rbLin = (RadioButton) findViewById(R.id.radioButtonLin);
        RadioButton rbNLogN = (RadioButton) findViewById(R.id.radioButtonNLogN);
        RadioButton rbQuad = (RadioButton) findViewById(R.id.radioButtonQuad);
        if (rbLog.isChecked()) {
            pTailleDonnees = (int) java.lang.Math.log10(pTailleDonnees); }
        else if (rbLin.isChecked()) {
            pTailleDonnees = pTailleDonnees; }
        else if (rbNLogN.isChecked()) {
            pTailleDonnees = (int) (pTailleDonnees * java.lang.Math.log10(pTailleDonnees)); }
        else if (rbQuad.isChecked()) {
            pTailleDonnees = pTailleDonnees * pTailleDonnees; }
        return pTailleDonnees;
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
            case R.id.quiz:
                intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
                //Toast.makeText(this,"Pas encore", Toast.LENGTH_LONG).show();
                return true;
            case R.id.sort:
                intent = new Intent(MainActivity.this, SortActivity.class);
                startActivity(intent);
                return true;
            case R.id.information:
                intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNICalcVar(int tailleDonnees);
    public native String stringFromJNICalcReg(int tailleDonnees);
}
