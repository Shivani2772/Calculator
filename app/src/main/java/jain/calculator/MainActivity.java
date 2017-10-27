package jain.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView font;
    double rslt;
    boolean dot = false;
    boolean zero = false;
    double memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        font = (TextView)findViewById(R.id.textView4);
        font.setText("0");

        findViewById(R.id.one).setOnClickListener(lstn);
        findViewById(R.id.two).setOnClickListener(lstn);
        findViewById(R.id.three).setOnClickListener(lstn);
        findViewById(R.id.four).setOnClickListener(lstn);
        findViewById(R.id.five).setOnClickListener(lstn);
        findViewById(R.id.six).setOnClickListener(lstn);
        findViewById(R.id.seven).setOnClickListener(lstn);
        findViewById(R.id.eight).setOnClickListener(lstn);
        findViewById(R.id.nine).setOnClickListener(lstn);
        findViewById(R.id.zero).setOnClickListener(lstn);
        findViewById(R.id.div).setOnClickListener(lstn);
        findViewById(R.id.mul).setOnClickListener(lstn);
        findViewById(R.id.plus).setOnClickListener(lstn);
        findViewById(R.id.minus).setOnClickListener(lstn);
        findViewById(R.id.dot).setOnClickListener(lstn);
        findViewById(R.id.zero2).setOnClickListener(lstn);

        findViewById(R.id.plusminus).setOnClickListener(pm);

        findViewById(R.id.c).setOnClickListener(clear);

        findViewById(R.id.eq).setOnClickListener(result);
        findViewById(R.id.eq2).setOnClickListener(result);




    }
    View.OnClickListener mr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener mp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(memory!= 0)
                font.setText(font.getText().toString() + memory);
        }
    };
    View.OnClickListener mm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener mc = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            memory = 0;
        }
    };
    View.OnClickListener pm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(font.getText().charAt(0) == '-' )
            {
                font.setText(font.getText().toString().substring(1));
            }
            else
            {
                font.setText("-"+ font.getText());
            }
        }
    };

    View.OnClickListener clear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            font.setText("0");
            rslt=0;
            dot=false;
        }
    };



    View.OnClickListener result = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            char[] arr = font.getText().toString().toCharArray();
            char[] oper = new char[19];
            String[] nums = new String[20];

            int pos =0;
            int num =0;
            for (int i=0;i< arr.length;i++)
            {//if(!Character.toString(arr[i]).equals("/")||!Character.toString(arr[i]).equals("+") ||!Character.toString(arr[i]).equals("-")||!Character.toString(arr[i]).equals("×"))
                if(Character.isDigit(arr[i]) || arr[i]== '.')
                {
                    if(nums[num] != null){
                        nums[num]=nums[num] +arr[i];}
                    else
                    {
                        nums[num]= Character.toString(arr[i]);

                    }

                }
                else
                {
                    oper[pos]=arr[i];
                    num++;
                    pos++;
                }

            }
            //for(int j=0;j<nums.length-1;j++)
            //   Log.d("TAG", "num "+ nums[j] +"  char "+ arr[j]);
            //zero=false;
            for(int j=0;j<num;j++)
            {
                rslt=calc(nums[j],nums[j+1], oper[j]);
                if(zero == false){
                    if(j!=num-1)
                    {
                        nums[j+1]= String.valueOf(rslt);
                    }
                    else
                    {
                        break;
                    }}else{break;}
            }
            if(zero==false){
                if(rslt == Math.floor(rslt) && !Double.isInfinite(rslt))
                {
                    //int rslt1 =(int) rslt;
                    //font.setText("" + rslt1);
                    font.setText(String.format("%.0f", rslt));
                    //Log.d("TAG", "Yo +");

                }else
                {
                    font.setText(String.format("%.02f", rslt));
                    // Log.d("TAG", "Yo -");
                }}else{zero=false;}


        }
    };
    double calc(String num1, String num2, char oper)
    {   double rslt=0;
        Log.d("TAG", "num1 "+ num1 +"  num2 "+ num2);
        double n1= Double.parseDouble(num1);
        double n2= Double.parseDouble(num2);
        switch(oper)
        {
            case '-':
                rslt = n1-n2;
                break;
            case'+':
                rslt = n1+n2;
                break;
            case '/':
                if(n2!=0){
                    rslt = n1/n2;}
                else{
                    Toast.makeText(getApplicationContext(),"Cant divide by 0",Toast.LENGTH_SHORT);
                    font.setText("0");
                    zero=true;}
                break;
            case'×':
                rslt= n1*n2;
                break;
        }
        return rslt;
    }

    View.OnClickListener lstn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button btn = (Button)v;
            //Log.d("TAG", font.getText().toString());
            if(font.getText().toString().equals("0"))
            {   if (!btn.getText().toString().equals("0")){
                if(btn.getText().toString().equals("."))
                {
                    Log.d("TAG","HEREEEE");
                    font.append(btn.getText());
                    dot=true;
                    Log.d("TAG", btn.getText().toString());


                }else
                {if(Character.isDigit(btn.getText().charAt(0))){
                    font.setText("");
                    font.setText(btn.getText());
                    Log.d("TAG", "HERE");
                    Log.d("TAG", btn.getText().toString());
                }
                }
            }
            }
            else {
                if(btn.getText().toString().equals(".")){
                    if(Character.isDigit(font.getText().toString().charAt(font.length()-1)))
                        if (dot == false)
                        {
                            font.append(btn.getText());
                            Log.d("TAG", "dot");
                            //Log.d("TAG", btn.getText().toString());
                            dot= true;
                        }
                        else
                        {

                        }}
                else
                {
                    if(!Character.isDigit(font.getText().toString().charAt(font.length()-1)) && !Character.isDigit(btn.getText().charAt(0)))
                    {

                    }else{if(!Character.isDigit(btn.getText().charAt(0)))
                    {dot= false;}
                        font.append(btn.getText());
                        Log.d("TAG", "ADD");
                        Log.d("TAG", btn.getText().toString());}}
            }
        }
    };
}
