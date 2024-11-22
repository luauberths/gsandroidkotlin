package com.example.gsandroidkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoApp"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        val tipEditText = findViewById<EditText>(R.id.editText2)

        button.setOnClickListener {
            val title = editText.text.toString().trim()
            val tip = tipEditText.text.toString().trim()

            if (title.isEmpty() || tip.isEmpty()) {
                if (title.isEmpty()) editText.error = "Preencha o título"
                if (tip.isEmpty()) tipEditText.error = "Preencha uma dica"
                return@setOnClickListener
            }

            viewModel.addItem(title, tip)
            editText.text.clear()
            tipEditText.text.clear()
        }

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    filterItems(it)
                }
                return true
            }
        })

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }

    private fun filterItems(query: String) {
        viewModel.itemsLiveData.observe(this) { items ->
            val filteredItems = items.filter {
                it.name.contains(query, ignoreCase = true) || it.tip.contains(
                    query,
                    ignoreCase = true
                )
            }
            itemsAdapter.updateItems(filteredItems)
        }
    }
}