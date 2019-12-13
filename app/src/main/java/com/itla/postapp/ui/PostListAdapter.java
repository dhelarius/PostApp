package com.itla.postapp.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.itla.postapp.R;
import com.itla.postapp.model.post.Post;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {

    private static String TAG = PostListAdapter.class.getSimpleName();

    private List<Post> postList;

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_post_item, parent, false);
        PostListViewHolder viewHolder = new PostListViewHolder(view);
        return viewHolder;
    }

    public PostListAdapter(List<Post> postList){
        this.postList = postList;
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        Post item = postList.get(position);

        holder.bind(item);

        holder.postTitle.setOnClickListener(v -> {
            updateBody(position, item);
        });

        holder.toggleArrow.setOnClickListener(v -> {
            updateBody(position, item);
        });

        holder.comments.setOnClickListener(v -> {
            Log.i(TAG, "click in comments");
        });

        holder.likes.setOnClickListener(v -> {
            Log.i(TAG, "click in likes");
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostListViewHolder extends RecyclerView.ViewHolder{

        private TextView postTitle;
        private TextView date;
        private TextView body;
        private TextView author;
        private TextView viewCounter;
        private TextView commentsCounter;
        private TextView likesCounter;
        private ImageView toggleArrow;

        private LinearLayout likes;
        private LinearLayout comments;

        public PostListViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.postTitle);
            date = itemView.findViewById(R.id.date);
            body = itemView.findViewById(R.id.body);
            author = itemView.findViewById(R.id.author);
            viewCounter = itemView.findViewById(R.id.viewCounter);
            commentsCounter = itemView.findViewById(R.id.commentsCounter);
            likesCounter = itemView.findViewById(R.id.likesCounter);
            toggleArrow = itemView.findViewById(R.id.toggleArrow);

            likes = itemView.findViewById(R.id.likes);
            comments = itemView.findViewById(R.id.comments);
        }

        private void bind(Post item) {

            boolean expanded = item.isExpanded();

            body.setVisibility(expanded ? View.VISIBLE : View.GONE);

            toggleArrow.setImageResource(expanded ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);

            postTitle.setText(item.getTitle());
            date.setText(String.valueOf(item.getCreateAt()));
            body.setText(item.getBody());
            String author = item.getUserName() + " (" + item.getUserEmail() + ")";
            this.author.setText(author);
            viewCounter.setText(String.valueOf(item.getViews()));
            commentsCounter.setText(String.valueOf(item.getComments()));
            likesCounter.setText(String.valueOf(item.getLikes()));
        }
    }

    private void updateBody(int position, Post item) {
        boolean expanded = item.isExpanded();

        item.setExpanded(!expanded);

        notifyItemChanged(position);
    }
}
