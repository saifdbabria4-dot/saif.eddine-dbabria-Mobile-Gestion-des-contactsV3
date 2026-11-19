package com.example.gestioncontact

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class ContactAdapter(
    private var contactList: List<Contact>,
    private val originalList: List<Contact>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivItemPhoto: ImageView = view.findViewById(R.id.ivItemPhoto)
        val tvItemName: TextView = view.findViewById(R.id.tvItemName)
        val tvItemPhone: TextView = view.findViewById(R.id.tvItemPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        val originalIndex = originalList.indexOf(contact)

        holder.tvItemName.text = "${contact.nom} ${contact.prenom}"
        holder.tvItemPhone.text = contact.telephone

        if (!contact.photoUri.isNullOrEmpty()) {
            try {
                holder.ivItemPhoto.setImageURI(Uri.parse(contact.photoUri))
            } catch (e: Exception) {
                holder.ivItemPhoto.setImageResource(R.mipmap.ic_launcher)
            }
        } else {
            holder.ivItemPhoto.setImageResource(R.mipmap.ic_launcher)
        }

        holder.itemView.setOnClickListener {
            if (originalIndex != -1) {
                onItemClick(originalIndex)
            }
        }
    }

    override fun getItemCount(): Int = contactList.size

    fun updateList(newList: List<Contact>) {
        contactList = newList
        notifyDataSetChanged()
    }
}
