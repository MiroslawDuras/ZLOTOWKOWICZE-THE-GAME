package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.logging.LogRecord;

public class Pytanie extends AppCompatActivity {
    private SoundPool dzwieki;
    private int click, wrong, correct, victory, victory2;
    String[] odpowiedzipoprawne={"1945", "Drugą", "Cztery", "Wrocek", "Roadster","2009","Nil","Maine","Radom","2011","George W. Bush","Nowy Jork","8 i pol minuty","Lionel Messi","1969","2,1 sekundy","Szanghaj","Rosja","Bill Gates","1996"};
    Random losowanie = new Random();
    int numerpytania = losowanie.nextInt(20);
    int licznikklikniec=0;
    int numerpytaniaa=1;
    int kasa=1;
    int licznikintencji;
    private static final String numerek= "numer";
    private static final String kaska= "kasa";
    private static final String przyjacielstring= "przyjaciel";
    private static final String zmianapytaniastring= "zmianapytania";
    private static final String odrzucodpowiedzistring= "odrzucodpowiedzi";
    int przjaciel;
    int zmianapytania;
    int odrzucodpowiedzi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pytanie);


        AudioAttributes atrybuty = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        dzwieki = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(atrybuty)
                .build();



            Intent intent = getIntent();
            numerpytaniaa= intent.getIntExtra(Pytanie.numerek,1);
            kasa=intent.getIntExtra(Pytanie.kaska,1);
            odrzucodpowiedzi=intent.getIntExtra(Pytanie.odrzucodpowiedzistring,0);
            zmianapytania=intent.getIntExtra(Pytanie.zmianapytaniastring,0);
            przjaciel=intent.getIntExtra(Pytanie.przyjacielstring,0);


        click = dzwieki.load(this,R.raw.click,1);
        wrong = dzwieki.load(this,R.raw.wrong,1);
        correct = dzwieki.load(this,R.raw.correct,1);
        victory = dzwieki.load(this,R.raw.victory,1);
        victory2 = dzwieki.load(this,R.raw.victory2,1);

            if(numerpytaniaa>10){
                dzwieki.play(victory,1,1,0,0,1);
                dzwieki.play(victory2,1,1,0,0,1);
                Intent intent2 = new Intent(getApplicationContext(), Wygrana.class);
                startActivity(intent2);


            }


        String[] pytania = {"W którym roku powstała Politechnika Wrocławska?", "Która z kolei planetą od słońca jest Wenus?", "Ile par odnóży krocznych posiada skorpion?","Jak nazywa się kot prezydenta Wrocławia?","Który model samochodu Tesla powstał najwcześniej?","W którym roku wydana została gra League of Legends?"
            , "Jaka jest najdłuższa rzeka na świecie?", "Który stan USA graniczy lądowo z tylko jednym stanem?","Które z poniższych miast jest największe terytorialnie?","W którym roku miała miejsce katastrofa Elektrowni Atomowej Fukushima?"
            ,"Kto w latach 2000-2008 pełnił rolę prezydenta USA?", "Które miasto w USA jest najliczniejsze?", "W ile czasu światło pokonuję drogę ze słońca na ziemię?","Który z poniższych piłkarzy posiada najwięcej złotych piłek?","W któym roku miejsce miała misja kosmiczna Apollo 11?"
            ,"W ile czasu Tesla Model S osiąga prędkość 100km/h?", "Jakie jest najludniejsze miasto w Chinach?","Jakie jest największe terytorialnie państwo na świecie?","Kto jest założycielem Microsoft?", "W któym roku powstał język Java?"};
        String[] odpowiedzi1= {"1945", "Drugą", "Trzy","Wroceł","Cybertruck","2011", "Amazonka","Teksas","Paryż","2012","Barack Obama","Los Angeles", "5 minut","Cristiano Ronaldo","1967","3,2 sekundy","Szanghaj","Chiny", "Steve Jobs","1999"};
        String[] odpowiedzi2 = {"1915","Trzecią", "Pięć","Klakier","Model S","2010","Nil","Maine","Radom","2010","Bill Clinton","Chicago", "8 i pol minuty","Michel Platini","1959", "2,1 sekundy","Pekin","USA", "Elon Musk","1995"};
        String[] odpowiedzi3={"1950", "Pierwszą","Sześć","Wrocław", "Model Y","2009","Jangcy","Floryda","Malmo","2011", "George W. Bush","Houston","7 sekund","Lionel Messi","1970","4,3 sekundy","Wuhan","Rosja","Jeff Bezos","1996"};
        String[] odpowiedzi4={"1939", "Czwartą", "Cztery","Wrocek", "Roadster","2008","Huang He","Washington","Kalisz","2009","Donald Trump","Nowy Jork", "2 minuty","Diego Maradona","1969","2,7 sekundy","Kanton","Indie","Bill Gates","1990"};

        TextView pytanie = (TextView) findViewById(R.id.textfield1);
        TextView numeracjapytania = (TextView) findViewById(R.id.textfield2);
        TextView kasa1 = (TextView) findViewById(R.id.textfield3);
        Button odpowiedz1 = (Button) findViewById(R.id.button1);
        Button odpowiedz2 = (Button) findViewById(R.id.button2);
        Button odpowiedz3 = (Button) findViewById(R.id.button3);
        Button odpowiedz4 = (Button) findViewById(R.id.button4);
        pytanie.setText(pytania[numerpytania]);
        odpowiedz1.setText(odpowiedzi1[numerpytania]);
        odpowiedz2.setText(odpowiedzi2[numerpytania]);
        odpowiedz3.setText(odpowiedzi3[numerpytania]);
        odpowiedz4.setText(odpowiedzi4[numerpytania]);

        Button przyjacielbutton = (Button) findViewById(R.id.buttontelefondoprzyjaciela);
        Button zmienpytaniebutton = (Button) findViewById(R.id.buttozmianapytania);
        Button odrzucodpowiedzibutton = (Button) findViewById(R.id.buttonodrzucdwie);

        if (zmianapytania >=1){
            zmienpytaniebutton.setBackgroundColor(Color.RED);
        }
        if(odrzucodpowiedzi>=1){
            odrzucodpowiedzibutton.setBackgroundColor(Color.RED);
        }
        if(przjaciel>=1){
            przyjacielbutton.setBackgroundColor(Color.RED);
        }




        kasa1.setText("" + kasa + " ZŁ");
        numeracjapytania.setText("PYTANIE: " + numerpytaniaa);






    }

   // private Runnable czekanie = new Runnable() {
   //     @Override
   //     public void run() {
   //         System.out.println(1);
//
   //     }
   // };


    public void wstecz(View view) {Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        dzwieki.play(click,1,1,0,0,1);

    }



    public void odpowiedz4(View view) throws InterruptedException {
        licznikklikniec++;
        if(licznikklikniec<=1) {
            Button odpowiedz4 = (Button) findViewById(R.id.button4);
            odpowiedz4.setBackgroundColor(Color.YELLOW);
            dzwieki.play(click,1,1,0,0,1);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (odpowiedz4.getText().equals(odpowiedzipoprawne[numerpytania])) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz4.setBackgroundColor(Color.GREEN);
                                dzwieki.play(correct,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        numerpytaniaa++;
                                        kasa++;
                                        Intent intent = new Intent(getApplicationContext(), Pytanie.class);
                                        intent.putExtra(numerek, numerpytaniaa);
                                        intent.putExtra(kaska, kasa);
                                        intent.putExtra(przyjacielstring, przjaciel);
                                        intent.putExtra(odrzucodpowiedzistring, odrzucodpowiedzi);
                                        intent.putExtra(zmianapytaniastring, zmianapytania);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    } else {
                        numerpytania=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz4.setBackgroundColor(Color.RED);
                                dzwieki.play(wrong,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), Koniec.class);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    }


                }
            }, 1500);

        }
    }



    public void sprawdz(View view) {
    }

    public void odpowiedz2(View view) throws InterruptedException {
        licznikklikniec++;
        if(licznikklikniec<=1) {
            Button odpowiedz2 = (Button) findViewById(R.id.button2);
            odpowiedz2.setBackgroundColor(Color.YELLOW);
            dzwieki.play(click,1,1,0,0,1);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (odpowiedz2.getText().equals(odpowiedzipoprawne[numerpytania])) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz2.setBackgroundColor(Color.GREEN);
                                dzwieki.play(correct,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        numerpytaniaa++;
                                        kasa++;
                                        Intent intent = new Intent(getApplicationContext(), Pytanie.class);
                                        intent.putExtra(kaska, kasa);
                                        intent.putExtra(przyjacielstring, przjaciel);
                                        intent.putExtra(odrzucodpowiedzistring, odrzucodpowiedzi);
                                        intent.putExtra(zmianapytaniastring, zmianapytania);
                                        intent.putExtra(numerek, numerpytaniaa);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    } else {
                        numerpytania=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz2.setBackgroundColor(Color.RED);
                                dzwieki.play(wrong,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), Koniec.class);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    }


                }
            }, 1500);

        }
    }

    public void odpowiedz3(View view) throws InterruptedException {
        licznikklikniec++;
        if(licznikklikniec<=1) {
            Button odpowiedz3 = (Button) findViewById(R.id.button3);
            odpowiedz3.setBackgroundColor(Color.YELLOW);
            dzwieki.play(click,1,1,0,0,1);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (odpowiedz3.getText().equals(odpowiedzipoprawne[numerpytania])) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz3.setBackgroundColor(Color.GREEN);
                                dzwieki.play(correct,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        numerpytaniaa++;
                                        kasa++;
                                        Intent intent = new Intent(getApplicationContext(), Pytanie.class);
                                        intent.putExtra(kaska, kasa);
                                        intent.putExtra(numerek, numerpytaniaa);
                                        intent.putExtra(przyjacielstring, przjaciel);
                                        intent.putExtra(odrzucodpowiedzistring, odrzucodpowiedzi);
                                        intent.putExtra(zmianapytaniastring, zmianapytania);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    } else {
                        numerpytania=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz3.setBackgroundColor(Color.RED);
                                dzwieki.play(wrong,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), Koniec.class);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    }


                }
            }, 1500);

        }
    }

    public void odpowiedz1(View view) throws InterruptedException {
        licznikklikniec++;
        if(licznikklikniec<=1) {
            Button odpowiedz1 = (Button) findViewById(R.id.button1);
            odpowiedz1.setBackgroundColor(Color.YELLOW);
            dzwieki.play(click,1,1,0,0,1);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (odpowiedz1.getText().equals(odpowiedzipoprawne[numerpytania])) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz1.setBackgroundColor(Color.GREEN);
                                dzwieki.play(correct,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        numerpytaniaa++;
                                        kasa++;
                                        Intent intent = new Intent(getApplicationContext(), Pytanie.class);
                                        intent.putExtra(kaska, kasa);
                                        intent.putExtra(numerek, numerpytaniaa);
                                        intent.putExtra(przyjacielstring, przjaciel);
                                        intent.putExtra(odrzucodpowiedzistring, odrzucodpowiedzi);
                                        intent.putExtra(zmianapytaniastring, zmianapytania);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    } else {
                        numerpytania=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                odpowiedz1.setBackgroundColor(Color.RED);
                                dzwieki.play(wrong,1,1,0,0,1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), Koniec.class);
                                        startActivity(intent);

                                    }
                                }, 1500);

                            }
                        }, 1500);


                    }


                }
            }, 1500);

        }



    }

    public void zmianapytania(View view) {
        dzwieki.play(click, 1, 1, 0, 0, 1);
        zmianapytania++;
        Button zmienpytaniebutton = (Button) findViewById(R.id.buttozmianapytania);
        if(zmianapytania==1) {
            zmienpytaniebutton.setBackgroundColor(Color.RED);
            Intent intent = new Intent(getApplicationContext(), Pytanie.class);
            intent.putExtra(kaska, kasa);
            intent.putExtra(numerek, numerpytaniaa);
            intent.putExtra(przyjacielstring, przjaciel);
            intent.putExtra(odrzucodpowiedzistring, odrzucodpowiedzi);
            intent.putExtra(zmianapytaniastring, zmianapytania);
            startActivity(intent);
        }

    }

    public void odrzucdwie(View view) {
        Button odrzucodpowiedzibutton = (Button) findViewById(R.id.buttonodrzucdwie);
        dzwieki.play(click,1,1,0,0,1);
        odrzucodpowiedzi++;
        if(odrzucodpowiedzi==1) {
            odrzucodpowiedzibutton.setBackgroundColor(Color.RED);
            int licznikodpowiedzidobrych = 0;
            Button odpowiedz1 = (Button) findViewById(R.id.button1);
            Button odpowiedz2 = (Button) findViewById(R.id.button2);
            Button odpowiedz3 = (Button) findViewById(R.id.button3);

            Button odpowiedz4 = (Button) findViewById(R.id.button4);
            // Random losowanieodpowiedzi = new Random();

            // int losowanieodpowiedziwartosc = losowanieodpowiedzi.nextInt(4);

            if (!odpowiedz1.getText().equals(odpowiedzipoprawne[numerpytania])) {
                odpowiedz1.setBackgroundColor(Color.RED);
            } else {
                licznikodpowiedzidobrych++;

            }

            if (!odpowiedz2.getText().equals(odpowiedzipoprawne[numerpytania])) {
                odpowiedz2.setBackgroundColor(Color.RED);
            } else {
                licznikodpowiedzidobrych++;
            }
            if (licznikodpowiedzidobrych == 1) {
                odpowiedz3.setBackgroundColor(Color.RED);

            }
        }

    }

    public void telefondoprzyjaciela(View view) {
        dzwieki.play(click, 1, 1, 0, 0, 1);
        przjaciel++;
        Button przyjacielbutton = (Button) findViewById(R.id.buttontelefondoprzyjaciela);
        if (przjaciel == 1) {
            przyjacielbutton.setBackgroundColor(Color.RED);
            TextView pytanie = (TextView) findViewById(R.id.textfield1);
            Button odpowiedz1 = (Button) findViewById(R.id.button1);
            Button odpowiedz2 = (Button) findViewById(R.id.button2);
            Button odpowiedz3 = (Button) findViewById(R.id.button3);
            Button odpowiedz4 = (Button) findViewById(R.id.button4);
            String odpowiedzprzyjaciela;

            if (odpowiedz1.getText().equals(odpowiedzipoprawne[numerpytania])) {
                odpowiedzprzyjaciela = "A";
            } else if (odpowiedz2.getText().equals(odpowiedzipoprawne[numerpytania])) {
                odpowiedzprzyjaciela = "B";
            } else if (odpowiedz3.getText().equals(odpowiedzipoprawne[numerpytania])) {
                odpowiedzprzyjaciela = "C";
            } else {
                odpowiedzprzyjaciela = "D";
            }
            pytanie.setText("Przyjaciel: Myślę że poprawną odpowiedzią jest " + odpowiedzprzyjaciela);

        }
    }
}