package com.teapot.firebaseui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.chip.Chip
import com.teapot.firebaseui.models.Team

class TeamAdapter(options: FirestoreRecyclerOptions<Team>) :
    FirestoreRecyclerAdapter<Team, TeamAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val teamView =
            LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return ViewHolder(teamView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Team) {
        holder.title.text = model.title
        holder.description.text = model.description
        holder.amount.text = model.amount.toString()
    }

    class ViewHolder(teamView: View) : RecyclerView.ViewHolder(teamView) {

        val title: TextView = teamView.findViewById(R.id.tvTitle)
        val description: TextView = teamView.findViewById(R.id.tvDescription)
        val amount: Chip = teamView.findViewById(R.id.cpAmount)

    }

}