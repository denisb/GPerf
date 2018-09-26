package edu.bdenis.gperf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    String strResultatTri;
    long timeStampMillisInit;
    long timeStampMillisFin;
    int quizdonnees [];
    int tpsAlgo=1;
    int nomAlgo=1;
    int formeDonnees=1;
    int numTest = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnQuiz = (Button) findViewById(R.id.buttonQuiz);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView tv = (TextView) findViewById(R.id.textViewQuizResultat);
                tv.setText("... calcul en cours ...");
                new Thread(new Runnable() {
                    public void run() {
                        timeStampMillisInit = System.currentTimeMillis();
                        int i, j, temp;
                        EditText etQuiz = (EditText) findViewById(R.id.editTextQuiz);
                        int pTailleDonnee;
                        if (etQuiz.getText().toString().equals("")) {
                            pTailleDonnee = 10;}
                        else if ((etQuiz.getText().toString().equals("0"))||(etQuiz.getText().toString().equals("00"))) {
                            pTailleDonnee = -1;}
                        else {
                            pTailleDonnee = Integer.parseInt(etQuiz.getText().toString());}
                        //if (pTailleDonnee<1) {pTailleDonnee = 10;}
                        if (formeDonnees==6) { ;}
                        else if ((formeDonnees==7)&&(pTailleDonnee!=-1)) {
                            pTailleDonnee = (int) (pTailleDonnee * java.lang.Math.log10(pTailleDonnee));}
                        else if ((formeDonnees==8)&&(pTailleDonnee!=-1)) {
                            pTailleDonnee = pTailleDonnee*pTailleDonnee;}
                        else if (pTailleDonnee!=-1) {
                          quizdonnees = new int[pTailleDonnee];
                          for (i = 1; i < quizdonnees.length; i++) {
                            if (formeDonnees==1) {
                                quizdonnees[i] = 99; }
                            else if (formeDonnees==2) {
                                quizdonnees[i] = i; }
                            else if (formeDonnees==3) {
                                quizdonnees[i] = pTailleDonnee-i; }
                            else if (formeDonnees==4) {
                                quizdonnees[i] = (int) (Math.random()*pTailleDonnee); }}}
                        if (nomAlgo==1) {
                          if (pTailleDonnee==-1) {
                              strResultatTri = "L'infini ? vous êtes fou.";}
                          else {
                              for (i = 1; i < quizdonnees.length; i++) {
                                  j = i - 1;
                                  while (j >= 0 && quizdonnees[j] > quizdonnees[i]) {
                                      temp = quizdonnees[i];
                                      quizdonnees[i] = quizdonnees[j];
                                      quizdonnees[j] = temp;
                                      i = j;
                                      j--; } }
                              strResultatTri = "Calcul fini.";}}
                      else if (nomAlgo==2) {
                            if (pTailleDonnee==-1) {
                                strResultatTri = "L'infini ? vous êtes fou.";}
                            else {
                                for(i = 0;i<quizdonnees.length-1;i++){
                                    int min = Integer.MAX_VALUE;
                                    int imin = i+1;
                                    for(j = i; j<quizdonnees.length;j++){
                                        if(quizdonnees[j]<min){
                                            imin = j;
                                            min = quizdonnees[j]; } }
                                    temp = quizdonnees[i];
                                    quizdonnees[i] = quizdonnees[imin];
                                    quizdonnees[imin] = temp; };
                            strResultatTri = "Calcul fini.";}}
                        else if (nomAlgo==3) {
                            if (pTailleDonnee==-1) {
                                strResultatTri = "L'infini ? vous êtes fou.";}
                            else {
                                quizmerge_sort(0,quizdonnees.length-1);
                                strResultatTri = "Calcul fini.";}}
                        else if (nomAlgo==4) {
                            if (pTailleDonnee==-1) {
                                strResultatTri = "L'infini ? vous êtes fou.";}
                            else {
                                quizquicksort(0,quizdonnees.length-1);
                                strResultatTri = "Calcul fini.";}}
                        else if (nomAlgo==5) {
                            if ((pTailleDonnee==-1)&&(formeDonnees==4)) {
                                strResultatTri = "L'infini ! Bonne réponse.";}
                            else if ((pTailleDonnee==-1)&&((formeDonnees==1)||(formeDonnees==2))) {
                                strResultatTri = "L'infini ? vous êtes fou.";}
                            else {
                                if (formeDonnees==1) {
                                  for(i=0;i<pTailleDonnee;i++) {
                                    if (i<i) {break;}}}
                                else if (formeDonnees==2) {
                                    for(i=0;i<pTailleDonnee;i++) {
                                        if ((i+1)<i) {break;}}}
                                else if (formeDonnees==4) {
                                    for(i=0;i<pTailleDonnee;i++) {
                                        if ((Math.random()*pTailleDonnee)<(Math.random()*pTailleDonnee)) {break;}}}
                                strResultatTri = "Calcul fini.";}}
                        else if (nomAlgo==6) {
                            if (pTailleDonnee==-1) {
                                strResultatTri = "L'infini ? vous êtes fou.";}
                            else {
                                int n,r;
                                n=1;
                                r=n;
                                for(i=pTailleDonnee;i!=0;i--) {
                                    if ((r==1) || (r==-34) || (r==-10) || (r==-1)) {n=n+1; r = n;}
                                    if ((r & 1)==1) {r=(r*3+1)/2;} else {r=r/2;}}
                                strResultatTri = "Calcul fini.";}}
                        timeStampMillisFin = System.currentTimeMillis();
                        v.post(new Runnable() {
                            public void run() {
                                long tps = timeStampMillisFin - timeStampMillisInit;
                                TextView tv = (TextView) findViewById(R.id.textViewQuizResultat);
                                if (!strResultatTri.equals("Calcul fini.")) {
                                    tv.setText(strResultatTri);}
                                else {
                                    if (tps > tpsAlgo*1000) {
                                        tv.setText("Non, c'est trop :" + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }
                                    else if (tps > tpsAlgo*800) {
                                        tv.setText("Yeah :" + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }
                                    else if (tps > tpsAlgo*400) {
                                        tv.setText("Pas mal, mais vous pouvez faire mieux :" + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }
                                    else {
                                        tv.setText("bof, un effort :" + Long.toString((timeStampMillisFin - timeStampMillisInit)) + "ms"); }}
                                Button btnQuiz = (Button) findViewById(R.id.buttonQuiz);
                                numTest--;
                                if (numTest == 0) {
                                    changeTest(); }
                                else {
                                  btnQuiz.setText("Test "+Integer.toString(numTest));}
                            }});
                    }
                }).start(); }});


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        changeTest();
        return true; //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home:
                intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.sort:
                intent = new Intent(QuizActivity.this, SortActivity.class);
                startActivity(intent);
                return true;
            case R.id.data:
                intent = new Intent(QuizActivity.this, DataActivity.class);
                startActivity(intent);
                return true;
            case R.id.information:
                intent = new Intent(QuizActivity.this, DetailActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item); } }

    void changeTest() {
        Button btnQuiz = (Button) findViewById(R.id.buttonQuiz);
        btnQuiz.setText("Test 3");
        numTest = 3;
        TextView tv = (TextView) findViewById(R.id.textViewQuizTemps);
        tv.setText("Temps : " + Integer.toString(tpsAlgo) + "s");
        int numProp = (int) (9*Math.random());
        if (numProp == 1)      {tpsAlgo = 1;  nomAlgo = 4;  formeDonnees = 1;}
        else if (numProp == 2) {tpsAlgo = 2;  nomAlgo = 2;  formeDonnees = 2;}
        else if (numProp == 3) {tpsAlgo = 3;  nomAlgo = 3;  formeDonnees = 4;}
        else if (numProp == 4) {tpsAlgo = 3;  nomAlgo = 5;  formeDonnees = 2;}
        else if (numProp == 5) {tpsAlgo = 3;  nomAlgo = 6;  formeDonnees = 6;}
        else if (numProp == 6) {tpsAlgo = 3;  nomAlgo = 6;  formeDonnees = 7;}
        else if (numProp == 7) {tpsAlgo = 3;  nomAlgo = 6;  formeDonnees = 8;}
        else if (numProp == 8) {tpsAlgo = 1;  nomAlgo = 5;  formeDonnees = 4;}
        else if (numProp == 9) {tpsAlgo = 2;  nomAlgo = 5;  formeDonnees = 1;}
        else                   {tpsAlgo = 4;  nomAlgo = 1;   formeDonnees = 3;}
        tv = (TextView) findViewById(R.id.textViewQuizAlgo);
        if (nomAlgo == 1)      {tv.setText("Algorithme : Tri par insertion");}
        else if (nomAlgo == 2) {tv.setText("Algorithme : Tri par selection (du minimum)");}
        else if (nomAlgo == 3) {tv.setText("Algorithme : Tri fusion");}
        else if (nomAlgo == 4) {tv.setText("Algorithme : Tri rapide");}
        else if (nomAlgo == 5) {tv.setText("Algorithme : Test trié ?");}
        else if (nomAlgo == 6) {tv.setText("Algorithme : Simulation (Syracuse)");}
        else                   {tv.setText("Algorithme : Tri fusion"); nomAlgo = 3;}
        tv = (TextView) findViewById(R.id.textViewQuizFormeDonnees);
        if (formeDonnees == 1)      {tv.setText("Forme des données : toutes identiques");}
        else if (formeDonnees == 2) {tv.setText("Forme des données : ordonnées");}
        else if (formeDonnees == 3) {tv.setText("Forme des données : à l'envers");}
        else if (formeDonnees == 4) {tv.setText("Forme des données : aleatoire");}
        else if (formeDonnees == 5) {tv.setText("Forme des données : ordonnées");}
        else if (formeDonnees == 6) {tv.setText("Complexité algo : linéaire");}
        else if (formeDonnees == 7) {tv.setText("Complexité algo : n*log(n)");}
        else if (formeDonnees == 8) {tv.setText("Complexité algo : quadratique");}
        else                        {tv.setText("Forme des données : aleatoire"); formeDonnees = 4;}
    }

    void quizmerge (int z, int n, int m) {
        int i, j, k;
        int x [] =  new int[n];
        for (i = 0, j = m, k = 0; k < n-z; k++) {
            x[k] = j == n      ? quizdonnees[z+i++]
                    : i == m      ? quizdonnees[z+j++]
                    : quizdonnees[z+j] < quizdonnees[z+i] ? quizdonnees[z+j++]
                    :               quizdonnees[z+i++]; }
        for (i = 0; i < n; i++) {
            quizdonnees[z+i] = x[i]; }
    }

    void quizmerge_sort (int z, int n) {
        if (n < 2)
            return;
        int m = n / 2;
        quizmerge_sort(z, m);
        quizmerge_sort(z+m, n - m);
        quizmerge(z, n, m); }


    int quizpartition ( int low, int high) {
        int pivot = quizdonnees[high];
        int i = (low - 1);
        int j, tmp;
        for ( j = low; j <= high- 1; j++) {
            if (quizdonnees[j] <= pivot) {
                i++;
                tmp = quizdonnees[i];
                quizdonnees[i] = quizdonnees[j];
                quizdonnees[j] = tmp; } }
        tmp = quizdonnees[i+1];
        quizdonnees[i+1] = quizdonnees[high];
        quizdonnees[high] = tmp;
        return (i + 1); }

    void quizquicksort( int low, int high) {
        if (low < high) {
            int pi = quizpartition(low, high);
            quizquicksort(low,pi - 1);
            quizquicksort(pi + 1, high);}
    }
}
