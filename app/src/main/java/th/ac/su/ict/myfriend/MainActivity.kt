package th.ac.su.ict.myfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val FriendCollection = db.collection("Friend")

        val data1:MutableMap<String,Any> = HashMap()

        var edtName = findViewById<EditText>(R.id.edtName)
        var edtId = findViewById<EditText>(R.id.edtNumber)
        var edtNumber = findViewById<EditText>(R.id.edtNumber)



        btnAdd.setOnClickListener {

            data1["Name"] = edtName.getText().toString()
            data1["Id"] = edtId.getText().toString()
            data1["Number"] = edtNumber.getText().toString()
            FriendCollection.add(data1)

        }

        val FriendDocRef = db.collection("Friend").document("XZv2IPXku6tNJ6YmpqzM")

        FriendDocRef.get()
            .addOnSuccessListener {

                Log.d("Firebase_debug",it.data.toString())

                val Name = it["Name"]
                val Id = it ["Id"]
                val Number = it ["Number"]
                tvName.setText(Name.toString())
                tvId.setText(Id.toString())
                tvNumber.setText(Number.toString())

            }
    }
}
