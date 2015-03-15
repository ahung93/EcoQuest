package com.example.regular.ecoquest;

/**
 * Created by William on 2015-03-14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nwhacks.ecoquest.classes.User;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {

    private Context currentContext;
    private List<User> allUsers;

    public UserListAdapter(Context context, List<User> users){
        super(context, R.layout.user_item, users);
        allUsers = users;
        currentContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        LayoutInflater inflater = (LayoutInflater) currentContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        if (itemView == null){
            itemView = inflater.inflate(R.layout.user_item, parent, false);
        }

        User currentUser = allUsers.get(position);

        TextView rankText = (TextView) itemView.findViewById(R.id.user_item_rank);
        TextView fullNameText = (TextView) itemView.findViewById(R.id.user_item_fullname);
        TextView descriptionText = (TextView) itemView.findViewById(R.id.user_item_description);
        TextView pointsText = (TextView) itemView.findViewById(R.id.user_item_points);

        rankText.setText(Integer.toString(position + 1));
        fullNameText.setText(currentUser.getName());
        descriptionText.setText("Some description");
        pointsText.setText((Integer.toString(currentUser.getTotalPoints())));

        return itemView;
    }

}
