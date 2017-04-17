package id.sch.smktelkom_mlg.learn.bookinventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sch.smktelkom_mlg.learn.bookinventory.model.Book;
import id.sch.smktelkom_mlg.learn.bookinventory.activity.BookFormActivity;
import id.sch.smktelkom_mlg.learn.bookinventory.adapter.BooksAdapter;
import id.sch.smktelkom_mlg.learn.bookinventory.helper.HelperFunction;

public class MainActivity extends AppCompatActivity {
    public int TO_FORM = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recylerBook)
    RecyclerView recylerBook;
    @BindView(R.id.fab)
    FloatingActionButton button;
    private List<Book> bookList = new ArrayList<>();
    private BooksAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Books Catalog");

        mAdapter = new BooksAdapter(this, bookList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recylerBook.setLayoutManager(mLayoutManager);
        recylerBook.setItemAnimator(new DefaultItemAnimator());
        recylerBook.addItemDecoration(new DividerDecoration(this));

        recylerBook.setAdapter(mAdapter);
        recylerBook.addOnItemTouchListener(new HelperFunction.RecyclerTouchListener(this, recylerBook,
                new HelperFunction.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent i = new Intent(MainActivity.this, BookFormActivity.class);
                        i.putExtra("bookEdit", bookList.get(position));
                        startActivity(i);
                    }

                    @Override
                    public void onLongClick(View view, final int position) {
                        final Book book = bookList.get(position);
                        AlertDialog dialog = new AlertDialog.
                                Builder(MainActivity.this).setTitle("Delete")
                                .setMessage("Are you sure to delete " + book.getBook_title() + " ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                        bookList.remove(book);
                                        mAdapter.notifyItemRemoved(position);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .create();
                        dialog.show();
                    }
                }));


        prepareBookData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void prepareBookData() {
        Book book = new Book("9780439064873", "Harry Potter And The Chamber Of Secrets", "J.K. Rowling", 2000, "Fantasy, Thriller, Mystery", "This is some synopsis", R.drawable.hp_chamber);
        bookList.add(book);

        book = new Book("9780316015844", "Twilight (The Twilight Saga, Book 1)", "Stephanie Meyer", 2006, "Fantasy, Drama, Romance", "This is some synopsis", R.drawable.twilight1);
        bookList.add(book);

        book = new Book("9781484724989", "Journey to Star Wars: The Force Awakens Lost Stars", "Claudia Gray", 2015, "Action, Thriller, Science Fiction", "This is some synopsis", R.drawable.star_wars);
        bookList.add(book);

        book = new Book("9780439136365", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 2001, "Fantasy, Thriller, Mystery", "This is some synopsis", R.drawable.hp_azkaban);
        bookList.add(book);

        mAdapter.notifyDataSetChanged();
    }

}
