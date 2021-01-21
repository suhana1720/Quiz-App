package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex=0;
    private Question[] questionBank= new Question[]{
            new Question(R.string.question_declaration,true),
            new Question(R.string.question_amendments,false),
            new Question(R.string.question_constitution,true),
            new Question(R.string.question_independence_rights,true),
            new Question(R.string.question_religion,true),
            new Question(R.string.question_government,false),
            new Question(R.string.question_government_feds,false),
            new Question(R.string.question_government_senators,true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        falseButton= findViewById(R.id.false_button);
        trueButton=findViewById(R.id.true_button);
        nextButton=findViewById(R.id.next_button);
        prevButton=findViewById(R.id.previous_button);
        questionTextView=findViewById(R.id.answer_text_view);
        // when more want to set onclick on more than 2 buttons
        // here in method we gonna pass actual onclick listener class interface that allow us din't override the click mth
        //instead of having files that a listener nd pass the entie impl entire class of on click listenable
        nextButton=findViewById(R.id.next_button);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        //register our button to listen to click events


    }

    @Override
    public void onClick(View v) {
        // here we'll listen the click
        // v gets the id
        switch (v.getId()) {
            case R.id.false_button:
                //this using to grt the context
                //Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                break;
            case R.id.true_button:
               // Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                break;
            case R.id.next_button:
                //go to next question
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                 updateQuestion();
                 break;
            case R.id.previous_button:
                if(currentQuestionIndex >0) {

                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    updateQuestion();
                }

        }
    }//wrapping in 1 function
    private void updateQuestion(){

        Log.d("Current","onClick" + currentQuestionIndex);
        //incrementing the question
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());

    }
    private void checkAnswer(boolean userChooseCorrect){
          boolean answerIsTrue= questionBank[currentQuestionIndex].isAnswerTrue();
          int toastMesssageId=0;
          if(userChooseCorrect==answerIsTrue){
              toastMesssageId=R.string.correct_answer;}
          else{
              toastMesssageId=R.string.wrong_answer;

          }
          Toast.makeText(MainActivity.this,toastMesssageId, Toast.LENGTH_SHORT)
                  .show();

    }


    }

