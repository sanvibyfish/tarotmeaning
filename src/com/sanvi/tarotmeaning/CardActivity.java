package com.sanvi.tarotmeaning;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * @author Sanvi E-mail:sanvibyfish@gmail.com
 * @version 创建时间：Apr 18, 2011 1:00:50 PM
 */
public class CardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);
		
		Bundle bundle = getIntent().getExtras();  
		Card card = (Card) bundle.getSerializable("card");
		TextView cardName = (TextView)findViewById(R.id.txt_card_name);
		cardName.setText(card.name);
		
		TextView cardKeyword = (TextView)findViewById(R.id.txt_card_keyword);
		cardKeyword.setText(card.keyword);
		
		TextView cardAstrology = (TextView)findViewById(R.id.txt_card_astrology);
		cardAstrology.setText(card.astrology);
		
		TextView cardIngredient = (TextView)findViewById(R.id.txt_card_ingredient);
		cardIngredient.setText(card.ingredient);
		
		TextView cardDescription = (TextView)findViewById(R.id.txt_card_description);
		cardDescription.setText(card.description);
		
		TextView cardNormal = (TextView)findViewById(R.id.txt_card_normal);
		cardNormal.setText(card.normal);
		
		TextView cardInvert = (TextView)findViewById(R.id.txt_card_invert);
		cardInvert.setText(card.invert);
	}
}
