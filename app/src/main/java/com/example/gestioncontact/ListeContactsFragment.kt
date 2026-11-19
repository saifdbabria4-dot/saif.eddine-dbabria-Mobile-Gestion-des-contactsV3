package com.example.gestioncontact

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class ListeContactsFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var etSearch: EditText
    private lateinit var rvContacts: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var adapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_contacts, container, false)

        etSearch = view.findViewById(R.id.etSearch)
        rvContacts = view.findViewById(R.id.rvContacts)
        tvEmpty = view.findViewById(R.id.tvEmpty)

        rvContacts.layoutManager = LinearLayoutManager(requireContext())

        adapter = ContactAdapter(viewModel.contacts, viewModel.contacts) { originalIndex ->
            viewModel.selectedIndex = originalIndex
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DetailContactFragment())
                .addToBackStack(null)
                .commit()
        }

        rvContacts.adapter = adapter

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterContacts(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }

    override fun onResume() {
        super.onResume()
        filterContacts(etSearch.text.toString())
    }

    private fun filterContacts(query: String) {
        val trimmed = query.trim().lowercase(Locale.ROOT)
        val filtered = if (trimmed.isEmpty()) {
            viewModel.contacts
        } else {
            viewModel.contacts.filter {
                it.nom.lowercase(Locale.ROOT).contains(trimmed) ||
                        it.prenom.lowercase(Locale.ROOT).contains(trimmed) ||
                        it.telephone.contains(trimmed)
            }
        }

        adapter.updateList(filtered)
        if (filtered.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            rvContacts.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            rvContacts.visibility = View.VISIBLE
        }
    }
}
