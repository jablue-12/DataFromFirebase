package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView infoTextView;
    private CollectionReference bookCollection = db.collection("BookCollections");
    private ArrayList<Book> listBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTextView = findViewById(R.id.infoTextView);
    }//end onCreate

    @Override
    protected void onStart() {
        super.onStart();
        listBook = new ArrayList<>();
        bookCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            Book book = documentSnapshot.toObject(Book.class);
                            book.setDocumentId(documentSnapshot.getId());
                            listBook.add(book);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,e.toString());
                    }
                });

    }//end onStart

    public void loadInformation(View v){

        //loadInfoFromDB();
        String data = "";
        for(int i = 0; i < listBook.size(); i++){

            Book book = listBook.get(i);
            String title= book.getTitle();
            String author = book.getAuthor();
            String summary = book.getSummary();
            int copies = book.getCopies();
            double price = book.getPrice();
            String bookId = book.getDocumentId();

            data +=  "id: " + bookId + "\nTitle: " + title + "\nAuthor: " + author +
                    "\nSummary: " + summary + "\nCopies: " +copies +
                    "\nPrice: $" + price + "\n\n";

        }//end for

        infoTextView.setText(data);

    }//end loadInformation
    public void loadInfoFromDB(){

        bookCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String data = "";
                        for(QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots){
                            Book book = documentSnapshots.toObject(Book.class);
                            book.setDocumentId(documentSnapshots.getId());
                            //add to the list
                            listBook.add(book);

                            /*  //getting the info from the db, but we are using the arraylist instead to load the info
                                 Book book = listBook.get(i);
                                String title= book.getTitle();
                                String author = book.getAuthor();
                                String summary = book.getSummary();
                                int copies = book.getCopies();
                                double price = book.getPrice();
                                String bookId = book.getDocumentId();

                                data +=  "id: " + bookId + "\nTitle: " + title + "\nAuthor: " + author +
                                        "\nSummary: " + summary + "\nCopies: " +copies +
                                        "\nPrice: $" + price + "\n\n";
                             */

                        }

                        //infoTextView.setText(data);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,e.toString());
                    }
                });
    }

}
