package team.max.jsonplaceholder.user.view.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.max.tzrustam.R;
import team.max.jsonplaceholder.user.dto.UserDTO;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{

    public interface OnItemClickListener {
        void onUserItemClicked(UserDTO user);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        protected TextView name;

        @BindView(R.id.email)
        protected TextView email;

        @BindView(R.id.company_name)
        protected TextView companyName;

        private UserDTO user;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.user_item)
        public void onUserItemClicked() {
            listener.onUserItemClicked(user);
        }

        public void bind(UserDTO user){
            this.user = user;
            name.setText(user.getName());
            email.setText(user.getEmail());
            companyName.setText(user.getCompany().getName());
        }
    }

    private List<UserDTO> users;
    private OnItemClickListener listener;

    public UsersAdapter(List<UserDTO> users, OnItemClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserDTO dto = users.get(position);
        holder.bind(dto);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void clear(){
        users.clear();
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
