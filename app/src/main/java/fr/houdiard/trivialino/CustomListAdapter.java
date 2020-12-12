package fr.houdiard.trivialino;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<ResultReponse> listData;
    private LayoutInflater layoutinflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<ResultReponse> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutinflater = LayoutInflater.from(aContext);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutinflater.inflate(R.layout.list_item_layout,null);
            holder = new ViewHolder();
            holder.flagView = (LinearLayout) convertView.findViewById(R.id.thelayout);
            holder.questionView = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.correctView = (TextView) convertView.findViewById(R.id.textView_correct);
            holder.answeredView = (TextView) convertView.findViewById(R.id.textView_answered);
            holder.logoView = (ImageView) convertView.findViewById(R.id.logo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResultReponse rerep = this.listData.get(position);
        holder.questionView.setText("Question " + (position+1) + ": " + rerep.getQuestion());
        holder.correctView.setText("Correct Answer : " + rerep.getCorrect());
        holder.answeredView.setText("Answered : " + rerep.getAnswered());

        if (rerep.getTagName()==true) {
            holder.flagView.setBackgroundColor(Color.rgb(159,232,85));

        } else {
            holder.flagView.setBackgroundColor(Color.rgb(252,93,93));

        }

        int imageId= this.getMipmapResIdByNames(rerep.getCateg());
        holder.logoView.setImageResource(imageId);


        return convertView;
    }

    public int getMipmapResIdByNames(String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        return resID;
    }




    static class ViewHolder {
        LinearLayout flagView;
        TextView questionView;
        TextView correctView;
        TextView answeredView;
        ImageView logoView;

    }
}
