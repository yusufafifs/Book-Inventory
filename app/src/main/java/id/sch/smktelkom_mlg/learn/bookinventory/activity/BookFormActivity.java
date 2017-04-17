package id.sch.smktelkom_mlg.learn.bookinventory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sch.smktelkom_mlg.learn.bookinventory.R;
import id.sch.smktelkom_mlg.learn.bookinventory.model.Book;

public class BookFormActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edittitle)
    EditText edittitle;
    @BindView(R.id.editauthor)
    EditText editauthore;
    @BindView(R.id.editgenre)
    EditText editgenre;
    @BindView(R.id.editIsbn)
    EditText editIsbn;
    @BindView(R.id.edityear)
    EditText edityear;
    @BindView(R.id.editsynopsis)
    EditText editsynopsis;
    @BindView(R.id.button)
    Button button;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Toast.makeText(BookFormActivity.this, "DATA Valid !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validate() {
        boolean valid = true;
        String isbn = editIsbn.getText().toString();
        String booktitle = edittitle.getText().toString();
        String bookauthor = editauthore.getText().toString();
        String PublishedYear = edityear.getText().toString();

        if (isbn.isEmpty()) {
            editIsbn.setError("Enter ISBN");
            valid = false;
        } else {
            editIsbn.setError(null);
        }

        if (booktitle.isEmpty()) {
            edittitle.setError("Enter Book Title");
            valid = false;
        } else {
            edittitle.setError(null);
        }

        if (bookauthor.isEmpty()) {
            editauthore.setError("Enter Book Author");
            valid = false;
        } else {
            editauthore.setError(null);
        }
        if (PublishedYear.isEmpty() || PublishedYear.length() < 4) {
            edityear.setError("Publish Year empty or mus in yyyy format");
            valid = false;
        } else {
            edityear.setError(null);
        }
        return valid;
    }
}

