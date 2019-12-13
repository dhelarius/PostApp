package com.itla.postapp.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.itla.postapp.R;
import com.itla.postapp.databinding.FragmentPostsBinding;
import com.itla.postapp.model.post.Post;
import com.itla.postapp.preference.TokenPreference;
import com.itla.postapp.ui.viewmodel.PostViewModel;
import com.itla.postapp.ui.viewmodel.PostViewModelFactory;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    private static String TAG = PostFragment.class.getSimpleName();

    private FragmentPostsBinding binding;
    private View view;

    private PostViewModel viewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private EditText editTitlePost;
    private EditText editContentPost;
    private EditText editTagsPost;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_posts, container, false);

        setHasOptionsMenu(true);

        view = binding.getRoot();

        View dialogPublish = LayoutInflater.from(getContext()).inflate(R.layout.dialog_publish, null);

        editTitlePost = dialogPublish.findViewById(R.id.editTitlePost);

        editContentPost = dialogPublish.findViewById(R.id.editContentPost);

        editTagsPost = dialogPublish.findViewById(R.id.editTagsPost);

        binding.setLifecycleOwner(this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.posts));

        String token = TokenPreference.getInstance(getActivity()).read();

        PostViewModelFactory viewModelFactory = new PostViewModelFactory(token);

        viewModel = ViewModelProviders
                .of(this, viewModelFactory).get(PostViewModel.class);

        viewModel.getPosts().observe(this, posts -> {
            if(posts != null){
                Log.i(TAG, "Total posts: " + posts.size());
                updateUI(posts);
            }
        });

        viewModel.isPostSending().observe(this, isPostSending -> {
            if(isPostSending){

                String title = editTitlePost.getText().toString();
                String body = editContentPost.getText().toString();
                String stringTags = editTagsPost.getText().toString();
                String[] tags = stringTags.split(",");

                Log.i(TAG,title);

                Post post = new Post(body, tags, title);

                viewModel.setPost(post);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String token = TokenPreference.getInstance(getActivity()).read();

        if(token.isEmpty()){
            navigateToLoginFragment();
            Log.i(TAG, "Token is empty");
        }else{
            Log.i(TAG, "Token is not empty");
        }
    }

    private void navigateToLoginFragment() {
        Navigation.findNavController(view).navigate(R.id.action_postsFragment_to_loginFragment);
    }

    private void updateUI(List<Post> postList){
        initRecyclerView(postList);
    }

    private void initRecyclerView(List<Post> postList){
        recyclerView = binding.postList;

        recyclerView.setHasFixedSize(true);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostListAdapter(postList);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.overflow_menu_post_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.addPost: showPublishDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPublishDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_publish, null))
                .setPositiveButton("PUBLICAR", (dialog, id) -> {

                    viewModel.publish().observe(this, post -> {

                        if(post != null){
                            Log.i(TAG, "Post: " + post.getTitle());
                        }

                        viewModel.hasBeenSendindPost();
                    });
                })
                .setNegativeButton("CANCELAR", (dialog, id) -> {
                    dialog.dismiss();
                })
                .setCancelable(false);

        builder.create().show();
    }
}
