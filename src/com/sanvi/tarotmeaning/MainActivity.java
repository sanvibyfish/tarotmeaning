package com.sanvi.tarotmeaning;


import java.io.InputStream;
import java.util.List;

import net.youmi.android.AdManager;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	  static{ 
	    	AdManager.init("bed7e32215f0f647", "63b1afaddcce4102", 30, false,"2.1");   
	    }
	  
	  
	List<Card> cards;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        InputStream is =  getResources().openRawResource(R.raw.result); 
        String resultJson = StringUtils.convertStreamToString(is);
        Gallery g = (Gallery) findViewById(R.id.gallery);
        try {
			g.setAdapter(new ImageAdapter(this,Card.formJson(new JSONArray(resultJson))));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Set a item click listener, and just Toast the clicked position
        g.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
            	Card card = cards.get(position);
            	Bundle bundle = new Bundle();
            	bundle.putSerializable("card", card);
            	ActivityUtils.jump(MainActivity.this, CardActivity.class, 0, bundle);
            }
        });
        
    }

	public static class ViewHolder {
		public TextView txtName;
		public ImageView imageCard;
	}
	
    public class ImageAdapter extends BaseAdapter {
    	
    	private LayoutInflater mInflater;
        public ImageAdapter(Context c,List<Card> tempCards) {
            mContext = c;
            cards = tempCards;
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // See res/values/attrs.xml for the <declare-styleable> that defines
            // Gallery1.
//            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
//            mGalleryItemBackground = a.getResourceId(
//                    R.styleable.Gallery1_android_galleryItemBackground, 0);
//            a.recycle();
        }
        

        public int getCount() {
        	if(cards != null){
        		return cards.size();
        	}else{
        		return 0;
        	}
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	ViewHolder holder = null;
    		if(convertView == null){
    			holder = new ViewHolder();
    			convertView = mInflater.inflate(R.layout.card_item, null);
    			holder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
    			holder.imageCard = (ImageView) convertView.findViewById(R.id.img_card);
    		}else{
    			holder = (ViewHolder) convertView.getTag();
    		}
    		Card card = cards.get(position);
    		int res = getResources().getIdentifier("com.sanvi.tarotmeaning:drawable/"+card.image, null, null);
    		holder.imageCard.setImageResource(res);
    		holder.txtName.setText(card.name);
    		convertView.setTag(holder);
    		return convertView;
        }

        private Context mContext;

    }
}