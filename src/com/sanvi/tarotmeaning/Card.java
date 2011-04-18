package com.sanvi.tarotmeaning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.widget.FrameLayout;

/**
 * @author Sanvi E-mail:sanvibyfish@gmail.com
 * @version 创建时间：Apr 18, 2011 10:05:47 AM
 */
public class Card implements Serializable{

	public String name;
	/**
	 * 关键字
	 */
	public String keyword;
	/**
	 * 星相
	 */
	public String astrology;
	/**
	 * 要素
	 */
	public String ingredient;
	public String description;
	public String normal;
	public String invert;
	public String image;

	public static Card formJson(JSONObject jsonObject) {
		Card card = new Card();
		try {
			if (jsonObject.has("name")) {
				card.name = jsonObject.getString("name");
			}
			if (jsonObject.has("keyword")) {
				card.keyword = jsonObject.getString("keyword");
			}
			if (jsonObject.has("astrology")) {
				card.astrology = jsonObject.getString("astrology");
			}
			if (jsonObject.has("ingredient")) {
				card.ingredient = jsonObject.getString("ingredient");
			}
			if (jsonObject.has("description")) {
				card.description = jsonObject.getString("description");
			}
			if (jsonObject.has("normal")) {
				card.normal = jsonObject.getString("normal");
			}
			if (jsonObject.has("invert")) {
				card.invert = jsonObject.getString("invert");
			}
			if (jsonObject.has("image")) {
				card.image = jsonObject.getString("image");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return card;
	}

	public static List<Card> formJson(JSONArray array) {
		List<Card> cards = new ArrayList<Card>();
		try {
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				cards.add(formJson(obj));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cards;
	}
}
