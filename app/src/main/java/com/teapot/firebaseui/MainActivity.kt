package com.teapot.firebaseui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.teapot.firebaseui.models.Team

class MainActivity : AppCompatActivity() {
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val teamCollection = db.collection("teams")
    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener { it ->
            teamCollection.add(Team("jada", "adaj", 21)).addOnSuccessListener { ref ->
                run {
                    Snackbar.make(it, "Team saved ${ref.id}", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        val query = teamCollection.orderBy("title", Query.Direction.DESCENDING)
        val opts = FirestoreRecyclerOptions.Builder<Team>().setQuery(query, Team::class.java).build()
        adapter = TeamAdapter(opts)
        val rvTeam = findViewById<RecyclerView>(R.id.rvTeam)
        rvTeam.setHasFixedSize(false)
        rvTeam.layoutManager = LinearLayoutManager(this)
        rvTeam.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}