package com.hy.utils;

import java.util.Random;

import me.kaede.tagview.OnTagClickListener;
import me.kaede.tagview.OnTagDeleteListener;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.jpush.android.ui.ListViewActivity;

import com.hldj.hmyg.R;


public class TagViewActivity extends Activity implements View.OnClickListener{

    private TagView tagView;
    private EditText editText;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagview);
        findViewById(R.id.tv_add).setOnClickListener(this);
        findViewById(R.id.tv_start_activity).setOnClickListener(this);
        findViewById(R.id.tv_list_activity).setOnClickListener(this);
        findViewById(R.id.tv_recyclerview_activity).setOnClickListener(this);
        editText = (EditText) findViewById(R.id.edit_tag);
        tagView = (TagView) this.findViewById(R.id.tagview);
        //SET LISTENER
        tagView.setOnTagClickListener(new OnTagClickListener() {

            @Override
            public void onTagClick(int position, Tag tag) {
                Toast.makeText(TagViewActivity.this, "click tag id = " + tag.id + " position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        tagView.setOnTagDeleteListener(new OnTagDeleteListener() {

            @Override
            public void onTagDeleted(int position, Tag tag) {
                Toast.makeText(TagViewActivity.this, "delete tag id = " + tag.id + " position =" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //ADD TAG
        String[] tags = getResources().getStringArray(R.array.continents);
        tagView.addTags(tags);
        random = new Random();
        String[] colors = this.getResources().getStringArray(R.array.colors);
        for (int i = 1; i < colors.length; i++) {
            Tag tag = new Tag("Colorful Text");
            tag.tagTextColor = Color.parseColor(colors[i]);
            tagView.addTag(tag);
        }
        for (String item : colors) {
            Tag tag = new Tag("Colorful Background");
            tag.layoutColor = Color.parseColor(item);
            tagView.addTag(tag);
        }
        Tag tag = new Tag("Border");
        tag.layoutBorderSize = 1f;
        tagView.addTag(tag);

        tag = new Tag("Border");
        tag.layoutBorderSize = 2f;
        tag.layoutBorderColor = Color.parseColor(colors[1]);
        tagView.addTag(tag);

        tag = new Tag("Border");
        tag.layoutBorderSize = 3f;
        tag.layoutBorderColor = Color.parseColor(colors[3]);
        tagView.addTag(tag);

        tag = new Tag("Round Corner");
        tag.radius = 0f;
        tagView.addTag(tag);

        tag = new Tag("Round Corner");
        tag.radius = 20f;
        tagView.addTag(tag);

        tag = new Tag("Round Corner");
        tag.radius = 60f;
        tagView.addTag(tag);

        tag = new Tag("Deletable");
        tag.isDeletable = true;
        tagView.addTag(tag);

        tag = new Tag("Custom Background");
        tag.tagTextColor = Color.parseColor(colors[0]);
        tag.background = this.getResources().getDrawable(R.drawable.bg_tag);
        tagView.addTag(tag);

        tag = new Tag("Detail Tag");
        tag.tagTextColor = Color.parseColor("#FFFFFF");
        tag.layoutColor = Color.parseColor("#DDDDDD");
        tag.layoutColorPress = Color.parseColor("#555555");
        //or tag.background = this.getResources().getDrawable(R.drawable.custom_bg);
        tag.radius = 20f;
        tag.tagTextSize = 14f;
        tag.layoutBorderSize = 1f;
        tag.layoutBorderColor = Color.parseColor("#FFFFFF");
        tag.isDeletable = true;
        tagView.addTag(tag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Tag tag = new Tag("ADD AFTER ACTIVITY RESULT");
            tagView.addTag(tag);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                String string = "ADD A TAG";
                if (editText.getText().toString() != null && !editText.getText().toString().equals(""))
                    string = editText.getText().toString();
                Tag tag = new Tag(string);
                int r = random.nextInt(2);
                if (r == 0) tag.isDeletable = true;
                r = random.nextInt(5);
                tag.layoutColor = Color.parseColor(TagViewActivity.this.getResources().getStringArray(R.array.colors)[r]);
                tagView.addTag(tag);
                break;
            case R.id.tv_start_activity:
                break;
            case R.id.tv_list_activity:
                startActivity(new Intent(TagViewActivity.this, ListViewActivity.class));
                break;
            case R.id.tv_recyclerview_activity:
                break;
        }
    }
}
