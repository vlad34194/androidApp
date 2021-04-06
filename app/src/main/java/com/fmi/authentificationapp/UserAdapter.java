package com.fmi.authentificationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersAdapterVH> implements Filterable {
    private List<UserModel> userModelList;
    private Context context;
    private SelectedUser selectedUser;
    private List<UserModel> getUserModelListFiltered;


    public UserAdapter(List<UserModel> userModelList, SelectedUser selectedUser) {
        this.userModelList = userModelList;
        this.getUserModelListFiltered = userModelList;
        this.selectedUser = selectedUser;
    }

    @NonNull
    @Override
    public UserAdapter.UsersAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UsersAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UsersAdapterVH holder, int position) {
        UserModel userModel = userModelList.get(position);
        String username = userModel.getUserName();
        String prefix = userModel.getUserName().substring(0, 1);

        holder.tvUserName.setText(username);
        holder.tvPrefix.setText(prefix);
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = getUserModelListFiltered.size();
                    filterResults.values = getUserModelListFiltered;
                } else {
                    String searchChr = constraint.toString().toLowerCase();
                    List<UserModel> resultData = new ArrayList<>();
                    for (UserModel userModel : getUserModelListFiltered) {
                        if (userModel.getUserName().toLowerCase().contains(searchChr)) {
                            resultData.add(userModel);
                        }
                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userModelList = (List<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface SelectedUser {
        void selectedUser(UserModel userModel);
    }

    public class UsersAdapterVH extends RecyclerView.ViewHolder {
        TextView tvPrefix;
        TextView tvUserName;
        ImageView imIcon;

        public UsersAdapterVH(@NonNull View itemView) {
            super(itemView);
            tvPrefix = itemView.findViewById(R.id.prefix);
            tvUserName = itemView.findViewById(R.id.userName);
            imIcon = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedUser.selectedUser(userModelList.get(getAdapterPosition()));
                }
            });
        }
    }
}
