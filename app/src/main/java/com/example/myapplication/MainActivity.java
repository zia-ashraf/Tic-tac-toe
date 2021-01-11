package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //cross=1, circle=0
    int activePlayer=1;
    int winning_positions[][]={{0,1,2},{3,4,5},{6,7,8,},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] slot={2,2,2,2,2,2,2,2,2};
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int pos=Integer.valueOf(counter.getTag().toString());
        if(slot[pos]==2){
            slot[pos]=activePlayer;
            counter.setTranslationY(-1000f);

            if(activePlayer==1){

                counter.setImageResource(R.drawable.cross22);
                activePlayer=0;
            }
            else{
                counter.setImageResource(R.drawable.circles);
                activePlayer=1;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] winningPos : winning_positions){
                if(slot[winningPos[0]]==slot[winningPos[1]] &&
                        slot[winningPos[1]]==slot[winningPos[2]] && slot[winningPos[2]]!=2 ){
                    System.out.println(slot[winningPos[0]]);
                    String winner;
                    if(slot[winningPos[0]]==1){
                        winner="Player 1  (crosses) ";
                    }
                    else{
                        winner="Player 2  (circles) ";
                    }

                    TextView winnerMessage=(TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+"is the winner of this game");

                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }

            }

        }

    }
    public void playAgain(View view){   //important to give this argument cuz this function should work only when it gets a view.
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;
        for(int i=0;i<slot.length;i++){
            slot[i]=2;

        }
        GridLayout mygrids=(GridLayout)findViewById(R.id.myGrid);
        for(int ii=0;ii<mygrids.getChildCount();ii++){
            ((ImageView) mygrids.getChildAt(ii)).setImageResource(0);

        }


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}