package com.example.fragment_tutor;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.frame_container) != null) {
            if(savedInstanceState != null)
                return;
            HeadlinesFragment headlinesFragment = new HeadlinesFragment();
            headlinesFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container, headlinesFragment).commit();
        }
    }

    @Override
    public void onArticleSelected(int position) {
        boolean isLandscape = getApplicationContext().getResources().getBoolean(R.bool.isLandscape);
        if(isLandscape) {
            ArticleFragment articleFragment = (ArticleFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.article_fragment);
            articleFragment.updateArticleView(position);
        }
        else {
            ArticleFragment newArticleFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newArticleFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, newArticleFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}