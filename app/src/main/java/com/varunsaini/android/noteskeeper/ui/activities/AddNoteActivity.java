package com.varunsaini.android.noteskeeper.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import io.paperdb.Paper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ebolo.krichtexteditor.RichEditor;
import com.ebolo.krichtexteditor.fragments.KRichEditorFragment;
import com.ebolo.krichtexteditor.fragments.Options;
import com.ebolo.krichtexteditor.ui.widgets.EditorButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.varunsaini.android.noteskeeper.R;
import com.varunsaini.android.noteskeeper.models.SimpleNote;
import com.varunsaini.android.noteskeeper.ui.ViewModels.AddNotesViewModel;
import com.varunsaini.android.noteskeeper.util.DateFormatter;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class AddNoteActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    //    private RichEditor mEditor;
    private KRichEditorFragment editorFragment;
    FrameLayout fragment_holder;
    AddNotesViewModel addNoteViewModel;
    FloatingActionButton saveNote;
    EditText title;
    int id;
    String content = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        fragment_holder = findViewById(R.id.fragment_holder);
        saveNote = findViewById(R.id.saveNote);
        editorFragment = KRichEditorFragment.getInstance(Options.DEFAULT);
        addNoteViewModel = ViewModelProviders.of(this).get(AddNotesViewModel.class);
        title = findViewById(R.id.title);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Karla.ttf");
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/Karla-Bold.ttf");
        title.setTypeface(tf1);

        id = getIntent().getIntExtra("id", -1);
        useTextEditorLibrary("Start Writing Note", R.id.fragment_holder, true);

//        if (id != -1) {
//            SimpleNote simpleNote= null;
//            try {
//                simpleNote = addNoteViewModel.getSingleNote(id);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////            editorFragment.getEditor().setHtmlContent(simpleNote.getContent(),true);
//            title.setText(simpleNote.getTitle());
//        }

        saveNote.setOnClickListener(new View.OnClickListener() {
            Calendar c = Calendar.getInstance();
            int date = (c.get(Calendar.DATE));
            int month = (c.get(Calendar.MONTH));
            String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
            String min = String.valueOf(c.get(Calendar.MINUTE));

            @Override
            public void onClick(View v) {

                if(id==-1) {
                    editorFragment.getEditor().getText(new RichEditor.OnTextReturned() {
                        @Override
                        public void process(@NotNull final String text) {
                            Log.d("bhg", "process: " + text);
                            addNoteViewModel.insertNote(new SimpleNote((int) c.getTimeInMillis(), title.getText().toString(), text, DateFormatter.changeDateFormat(date, month), hour + " : " + min, 0));
                            startActivity(new Intent(AddNoteActivity.this, AllNotesActivity.class));
                        }

                    });
                }else{
//                    SimpleNote simpleNote= addNoteViewModel.getSingleNote(id)
//                    editorFragment.getEditor().setContents(simpleNote.getContent());
//                    title.setText(simpleNote.getTitle());

                }
            }

        });



    }


    public void useTextEditorLibrary(String placeholder, int layoutId, boolean showToolbar) {
        editorFragment = (KRichEditorFragment) getSupportFragmentManager().findFragmentByTag("EDITOR");
        if (editorFragment == null)
            editorFragment = KRichEditorFragment.getInstance(
                    new Options()
                            .placeHolder(placeholder)
                            .onImageButtonClicked(new Runnable() {
                                @Override
                                public void run() {
//                                    ImagePicker.create(AddNoteActivity.this).start();
                                    Intent intent = new Intent();
                                    intent.setType("image/*");
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                                }
                            })
//                             Un-comment this line and comment out the layout below to
//                             disable the toolbar
                            .showToolbar(showToolbar)
                            .buttonLayout(Arrays.asList(
                                    EditorButton.UNDO,
                                    EditorButton.REDO,
                                    EditorButton.IMAGE,
                                    EditorButton.LINK,
                                    EditorButton.BOLD,
                                    EditorButton.ITALIC,
                                    EditorButton.UNDERLINE,
                                    EditorButton.SUBSCRIPT,
                                    EditorButton.SUPERSCRIPT,
                                    EditorButton.STRIKETHROUGH,
                                    EditorButton.JUSTIFY_LEFT,
                                    EditorButton.JUSTIFY_CENTER,
                                    EditorButton.JUSTIFY_RIGHT,
                                    EditorButton.JUSTIFY_FULL,
                                    EditorButton.ORDERED,
                                    EditorButton.UNORDERED,
                                    EditorButton.CHECK,
                                    EditorButton.NORMAL,
                                    EditorButton.H1,
                                    EditorButton.H2,
                                    EditorButton.H3,
                                    EditorButton.H4,
                                    EditorButton.H5,
                                    EditorButton.H6,
                                    EditorButton.INDENT,
                                    EditorButton.OUTDENT,
                                    EditorButton.BLOCK_QUOTE,
                                    EditorButton.BLOCK_CODE,
                                    EditorButton.CODE_VIEW
                            ))
                            .buttonActivatedColorResource(R.color.fabBlue)
                            .onInitialized(new Runnable() {
                                @Override
                                public void run() {
                                    // Simulate loading saved contents action
                                    editorFragment.getEditor().setContents(
                                            Paper.book("demo").read("content", "")

                                    );
                                }
                            })

            );

        getSupportFragmentManager().beginTransaction()
                .replace(layoutId, editorFragment, "EDITOR")
                .commit();

        if (id != -1) {
            SimpleNote simpleNote= null;
            try {
                simpleNote = addNoteViewModel.getSingleNote(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            editorFragment.getEditor().setHtmlContent(simpleNote.getContent(),true);
            title.setText(simpleNote.getTitle());
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            Uri image = data.getData();
            Toast.makeText(this, "ughjh", Toast.LENGTH_SHORT).show();
            editorFragment.getEditor().command(EditorButton.IMAGE, true, image.toString());
        }
    }
}


//////////////////
