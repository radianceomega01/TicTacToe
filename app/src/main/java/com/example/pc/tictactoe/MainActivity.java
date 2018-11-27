package com.example.pc.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currplayer=0;

    int gamestate[] = {2,2,2,2,2,2,2,2,2};

    int winpositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    String winner = "wrong";

    Boolean playable = true;

    Boolean gameover = true;

    public void initialize(View view)
    {
        ImageView setimages = (ImageView) view;

        TextView winnermsg = findViewById(R.id.winnermsg);

        int imgtag = Integer.parseInt(setimages.getTag().toString());

  if(gamestate[imgtag] == 2 && playable)
        {
            setimages.setScaleX(0f);
            setimages.setScaleY(0f);
            gamestate[imgtag] = currplayer;


            if (currplayer == 1) {
                setimages.setImageResource(R.drawable.wrong);
                currplayer = 0;
            } else {
                setimages.setImageResource(R.drawable.right);
                currplayer = 1;
            }


            setimages.animate().scaleX(1f).scaleY(1f);

            for (int[] winposition : winpositions) {
                if (gamestate[winposition[0]] == gamestate[winposition[1]] && gamestate[winposition[1]] == gamestate[winposition[2]]
                        && gamestate[winposition[currplayer]] != 2) {

                    LinearLayout layout = findViewById(R.id.msg);

                    layout.setVisibility(View.VISIBLE);

                   if(gamestate[winposition[0]] == 0)
                       winner = "right";

                       winnermsg.setText(" " + winner + " has won.");
                       playable = false;

                }
                else
                {
                    for(int over : winposition) {
                        if (over == 2)
                            gameover = false;

                    }
                        if(gameover)
                        {

                            LinearLayout layout = findViewById(R.id.msg);

                            layout.setVisibility(View.VISIBLE);

                            winnermsg.setText("Its a draw.");
                        }


                }
            }
        }
    }

    public void play_again(View view)
    {
        LinearLayout layout = findViewById(R.id.msg);
        layout.setVisibility(View.INVISIBLE);

        currplayer=0;

        for(int i=0; i<gamestate.length; i++)
        {
            gamestate[i]=2;
        }

        playable = true;

        GridLayout gridLayout = findViewById(R.id.gridlayout);
        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}