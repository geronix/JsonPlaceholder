package team.max.jsonplaceholder.user.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.max.jsonplaceholder.user.dto.AddressDTO;
import team.max.tzrustam.R;
import team.max.jsonplaceholder.user.dto.CompanyDTO;
import team.max.jsonplaceholder.user.dto.PostDTO;
import team.max.jsonplaceholder.user.dto.UserDTO;


public class UserDetailPostAdapter extends RecyclerView.Adapter {

    protected class UserDetailViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_detail_name)
        protected TextView userDetailName;

        @BindView(R.id.user_detail_username)
        protected TextView userDetailUsername;

        @BindView(R.id.user_detail_email)
        protected TextView userDetailEmail;

        @BindView(R.id.user_detail_phone)
        protected TextView userDetailPhone;

        @BindView(R.id.user_detail_website)
        protected TextView userDetailWebsite;

        @BindView(R.id.street)
        protected TextView street;

        @BindView(R.id.suite)
        protected TextView suite;

        @BindView(R.id.city)
        protected TextView city;

        @BindView(R.id.zipcode)
        protected TextView zipcode;

        @BindView(R.id.user_detail_company_name)
        protected TextView userDetailCompanyName;

        @BindView(R.id.catch_phrase)
        protected TextView catchPhrase;

        @BindView(R.id.bs)
        protected TextView bs;

        public UserDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(UserDTO user){
            userDetailName.setText(user.getName());
            userDetailUsername.setText(user.getUsername());
            userDetailEmail.setText(user.getEmail());
            userDetailPhone.setText(user.getPhone());
            userDetailWebsite.setText(user.getWebsite());

            AddressDTO address = user.getAddress();
            street.setText(address.getStreet());
            suite.setText(address.getSuite());
            city.setText(address.getCity());
            zipcode.setText(address.getZipcode());

            CompanyDTO company = user.getCompany();
            userDetailCompanyName.setText(company.getName());
            catchPhrase.setText(company.getCatchPhrase());
            bs.setText(company.getBs());
        }
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title_post)
        protected TextView titlePost;

        @BindView(R.id.body_post)
        protected TextView bodyPost;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind (PostDTO post){
            titlePost.setText(post.getTitle());
            bodyPost.setText(post.getBody());
        }
    }

    public static final int VIEW_TYPE_USER_DETAIL = 0;
    public static final int VIEW_TYPE_POST = 1;

    private UserDTO user;
    private List<PostDTO> posts;

    public UserDetailPostAdapter(UserDTO user, List<PostDTO> posts) {
        this.user = user;
        this.posts = posts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(VIEW_TYPE_USER_DETAIL == viewType){
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user_detail, parent, false);
            return new UserDetailViewHolder(v);
        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_posts, parent, false);
        PostViewHolder vh = new PostViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position > 0){
            int postPosition = position - 1;
            PostViewHolder ph = (PostViewHolder)holder;
            ph.bind(posts.get(postPosition));
        } else {
            ((UserDetailViewHolder)holder).bind(user);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_USER_DETAIL : VIEW_TYPE_POST;
    }
}
