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
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    String strResultatData;
    long timeStampMillisInit;
    long timeStampMillisFin;
    int donnees [];

    static {
        System.loadLibrary("native-lib");}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btnPrem = (Button) findViewById(R.id.buttonPremier);
        btnPrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                tv.setText("... calcul en cours ...");
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        rcquicksortPremier(0,donnees.length-1);
                        strResultatData = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                                tv.setText(strResultatData + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
                    }
                }).start(); }});

        Button btnMil = (Button) findViewById(R.id.buttonMilieu);
        btnMil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                tv.setText("... calcul en cours ...");
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        rcquicksortMilieu(0,donnees.length-1);
                        strResultatData = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                                tv.setText(strResultatData + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
                    }
                }).start(); }});

        Button btnMoy = (Button) findViewById(R.id.buttonMoyenne);
        btnMoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                tv.setText("... calcul en cours ...");
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        rcquicksortMoyenne(0,donnees.length-1);
                        strResultatData = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                                tv.setText(strResultatData + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
                    }
                }).start(); }});

        Button btnAlea = (Button) findViewById(R.id.buttonAleatoire);
        btnAlea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                tv.setText("... calcul en cours ...");
                ThreadGroup group = new ThreadGroup("threadGroup");
                //new Thread(group, runnableObject, "YourThreadName", 2000000).start();
                new Thread(group,new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        getDonnees();
                        rcquicksortAleatoire(0,donnees.length-1);
                        strResultatData = "Calcul fini en ";
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textViewResultatData);
                                tv.setText(strResultatData + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }});
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
                intent = new Intent(DataActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.sort:
                intent = new Intent(DataActivity.this, SortActivity.class);
                startActivity(intent);
                return true;
            case R.id.quiz:
                intent = new Intent(DataActivity.this, QuizActivity.class);
                startActivity(intent);
                return true;
            case R.id.information:
                intent = new Intent(DataActivity.this, DetailActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}



    public int[] getDonnees() {
        int pTailleDonnees = 0;
        RadioButton rb100 = (RadioButton) findViewById(R.id.radioButtonData100);
        RadioButton rb1000 = (RadioButton) findViewById(R.id.radioButtonData1000);
        RadioButton rb10000 = (RadioButton) findViewById(R.id.radioButtonData10000);
        RadioButton rb100000 = (RadioButton) findViewById(R.id.radioButtonData100000);
        if (rb100.isChecked()) {
            pTailleDonnees = 100; }
        else if (rb1000.isChecked()) {
            pTailleDonnees = 1000; }
        else if (rb10000.isChecked()) {
            pTailleDonnees = 10000; }
        else if (rb100000.isChecked()) {
            pTailleDonnees = 100000; }
        donnees = new int[pTailleDonnees];
        RadioButton rbId = (RadioButton) findViewById(R.id.radioButtonIdentiquesData);
        RadioButton rbOrd = (RadioButton) findViewById(R.id.radioButtonOrdonneesData);
        RadioButton rbPresq = (RadioButton) findViewById(R.id.radioButtonPresqueOrdonneesData);
        RadioButton rbCent = (RadioButton) findViewById(R.id.radioButtonCentreesData);
        RadioButton rbAlea = (RadioButton) findViewById(R.id.radioButtonAleatoireData);
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
        else if (rbCent.isChecked()) {
            for(int i=0;i<donnees.length;i++) {
                donnees[i]= Math.round((pTailleDonnees-2*i)*(pTailleDonnees-Math.round(i/pTailleDonnees)%2))+(1-Math.round((pTailleDonnees-2*i)))*(Math.round(i/pTailleDonnees)%2); }}
        else if (rbAlea.isChecked()) {
            for(int i=0;i<donnees.length;i++) {
                donnees[i]=(int) (pTailleDonnees*Math.random());}}
        return donnees;}

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

    void rcquicksortPremier( int low, int high) {
        if (low < high) {
            int tmp = donnees[low];
            donnees[low] = donnees[high];
            donnees[high] = tmp;
            int pi = rcpartition(low, high);
            rcquicksortPremier(low,pi - 1);
            rcquicksortPremier(pi + 1, high);}
    }

    void rcquicksortMilieu( int low, int high) {
        if (low < high) {
            int tmp = donnees[(low+high)/2];
            donnees[(low+high)/2] = donnees[high];
            donnees[high] = tmp;
            int pi = rcpartition(low, high);
            rcquicksortPremier(low,pi - 1);
            rcquicksortPremier(pi + 1, high);}
    }

    void rcquicksortMoyenne( int low, int high) {
        if (low < high) {
            int somme=0;
            int moyenne=-1;
            int iMoyenne=low;
            for(int i=low;i<=high;i++) {
              somme += donnees[i];}
            moyenne = somme / (high-low+1);
            for(int i=low+1;i<=high;i++) {
                if (Math.abs(donnees[i]-moyenne)<Math.abs(donnees[i]-donnees[iMoyenne])) {
                    iMoyenne = i;}}
            int tmp = donnees[iMoyenne];
            donnees[iMoyenne] = donnees[high];
            donnees[high] = tmp;
            int pi = rcpartition(low, high);
            rcquicksortPremier(low,pi - 1);
            rcquicksortPremier(pi + 1, high);}
    }

    void rcquicksortAleatoire( int low, int high) {
        if (low < high) {
            int iAleatoire = Math.round((int)(low+Math.random()*(high-low))); //Math.round(low+Math.random()*(high-low+1));
            int tmp = donnees[iAleatoire];
            donnees[iAleatoire] = donnees[high];
            donnees[high] = tmp;
            int pi = rcpartition(low, high);
            rcquicksortPremier(low,pi - 1);
            rcquicksortPremier(pi + 1, high);}
    }

}

