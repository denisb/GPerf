package edu.bdenis.gperf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SortActivity extends AppCompatActivity {

    String strResultatTri;
    long timeStampMillisInit;
    long timeStampMillisFin;
    int donnees [];

    static {
        System.loadLibrary("native-lib");}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnIns = (Button) findViewById(R.id.buttonInsertion);
        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                tv.setText("... calcul en cours ...");
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        int i, j, temp;
                        for (i = 1; i < donnees.length; i++) {
                            j = i - 1;
                            while (j >= 0 && donnees[j] > donnees[i]) {
                                temp = donnees[i];
                                donnees[i] = donnees[j];
                                donnees[j] = temp;
                                i = j;
                                j--; } }
                        strResultatTri = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                                tv.setText(strResultatTri + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
                    }
                }).start(); }});

        Button btnSel = (Button) findViewById(R.id.buttonMinimum);
        btnSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                tv.setText("... calcul en cours ...");
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        int i, j, temp;
                        for(i = 0;i<donnees.length-1;i++){
                            int min = Integer.MAX_VALUE;
                            int imin = i+1;
                            for(j = i; j<donnees.length;j++){
                                if(donnees[j]<min){
                                    imin = j;
                                    min = donnees[j]; } }
                            temp = donnees[i];
                            donnees[i] = donnees[imin];
                            donnees[imin] = temp; };
                        strResultatTri = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                                tv.setText(strResultatTri + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
                    }
                }).start(); }});

        Button btnMerge = (Button) findViewById(R.id.buttonFusion);
        btnMerge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                tv.setText("... calcul en cours ...");
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        rcmerge_sort(0, donnees.length-1);
                        strResultatTri = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                                tv.setText(strResultatTri + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
                    }
                }).start(); }});

        Button btnQuick = (Button) findViewById(R.id.buttonRapide);
        btnQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                tv.setText("... calcul en cours ...");
                ThreadGroup group = new ThreadGroup("threadGroup");
                //new Thread(group, runnableObject, "YourThreadName", 2000000).start();
                new Thread(group,new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        rcquicksort(0,donnees.length-1);
                        //stringFromJNICalcQuick(donnees.length);
                        strResultatTri = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatTri);
                                tv.setText(strResultatTri + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
                    }
                },"GPerfThreadName", 2000000).start(); }});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home:
                intent = new Intent(SortActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.quiz:
                intent = new Intent(SortActivity.this, QuizActivity.class);
                startActivity(intent);
                return true;
            case R.id.information:
                intent = new Intent(SortActivity.this, DetailActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}



    public int[] getDonnees() {
        int pTailleDonnees = 0;
        RadioButton rb100 = (RadioButton) findViewById(R.id.radioButton100);
        RadioButton rb1000 = (RadioButton) findViewById(R.id.radioButton1000);
        RadioButton rb10000 = (RadioButton) findViewById(R.id.radioButton10000);
        RadioButton rb100000 = (RadioButton) findViewById(R.id.radioButton100000);
        if (rb100.isChecked()) {
            pTailleDonnees = 100; }
        else if (rb1000.isChecked()) {
            pTailleDonnees = 1000; }
        else if (rb10000.isChecked()) {
            pTailleDonnees = 10000; }
        else if (rb100000.isChecked()) {
            pTailleDonnees = 100000; }
        donnees = new int[pTailleDonnees];
        RadioButton rbId = (RadioButton) findViewById(R.id.radioButtonIdentiques);
        RadioButton rbOrd = (RadioButton) findViewById(R.id.radioButtonOrdonnees);
        RadioButton rbPresq = (RadioButton) findViewById(R.id.radioButtonPresqueOrdonnees);
        RadioButton rbInv = (RadioButton) findViewById(R.id.radioButtonALEnvers);
        RadioButton rbAlea = (RadioButton) findViewById(R.id.radioButtonAleatoire);
        if (rbId.isChecked()) {
            for(int i=0;i<donnees.length;i++) {
                donnees[i]=pTailleDonnees;}}
        else if (rbOrd.isChecked()) {
            for(int i=0;i<donnees.length;i++) {
                donnees[i]=i;}}
        else if (rbPresq.isChecked()) {
            int val = 0;
            for(int i=0;i<donnees.length;i++) {
                if (Math.random()>0.999) {
                    donnees[i]=(int) (pTailleDonnees*Math.random());}
                else {
                    donnees[i]=val++;}}}
        else if (rbInv.isChecked()) {
            for(int i=0;i<donnees.length;i++) {
                donnees[i]=pTailleDonnees-i;}}
        else if (rbAlea.isChecked()) {
            for(int i=0;i<donnees.length;i++) {
                donnees[i]=(int) (pTailleDonnees*Math.random());}}
        return donnees;}

    void rcmerge (int z, int n, int m) {
        int i, j, k;
        int x [] =  new int[n];
        for (i = 0, j = m, k = 0; k < n-z; k++) {
            x[k] = j == n      ? donnees[z+i++]
                    : i == m      ? donnees[z+j++]
                    : donnees[z+j] < donnees[z+i] ? donnees[z+j++]
                    :               donnees[z+i++]; }
        for (i = 0; i < n; i++) {
            donnees[z+i] = x[i]; }
    }

    void rcmerge_sort (int z, int n) {
        if (n < 2)
            return;
        int m = n / 2;
        rcmerge_sort(z, m);
        rcmerge_sort(z+m, n - m);
        rcmerge(z, n, m); }


    int rcpartition ( int low, int high) {
        int pivot = donnees[high];
        int i = (low - 1);
        int j, tmp;
        for ( j = low; j <= high- 1; j++) {
            if (donnees[j] <= pivot) {
                i++;
                tmp = donnees[i];
                donnees[i] = donnees[j];
                donnees[j] = tmp; } }
        tmp = donnees[i+1];
        donnees[i+1] = donnees[high];
        donnees[high] = tmp;
        return (i + 1); }

    void rcquicksort( int low, int high) {
        if (low < high) {
            int pi = rcpartition(low, high);
            //try {
                rcquicksort(low,pi - 1);
                rcquicksort(pi + 1, high);}
            //catch (Exception  e) {
            //    Toast.makeText(this,"Erreur", Toast.LENGTH_LONG).show();}
        }

}

