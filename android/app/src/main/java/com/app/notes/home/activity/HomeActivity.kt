package com.app.notes.home.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.app.notes.ApiResponse
import com.app.notes.R
import com.app.notes.RetrofitClient
import com.app.notes.createnote.activity.CreateNoteActivity
import com.app.notes.databinding.ActivityHomeBinding
import com.app.notes.databinding.ItemInfoAlertBinding
import com.app.notes.home.adapter.NoteAdapter
import com.app.notes.model.Note
import com.app.notes.search.activity.SearchActivity
import retrofit2.Call

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        clickListeners()
        header()

    }


    override fun onResume() {
        super.onResume()
        loadNotes()
    }


    private fun clickListeners() = binding.apply {
        flAddNote.setOnClickListener {
            startActivity(Intent(this@HomeActivity, CreateNoteActivity::class.java))
        }


    }

    private fun header() {
        binding.header.apply {

            tvHeading.visibility = View.VISIBLE
            ivInfo.visibility = View.VISIBLE
            ivSearch.visibility = View.VISIBLE


            tvHeading.text = getString(R.string.notes)
            ivBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            ivSearch.setOnClickListener {
                startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
            }

            ivInfo.setOnClickListener {
                openInfoDialog()
            }
        }

    }
    private fun openInfoDialog() {
        val dialog = Dialog(this)
        val sheetBinding = ItemInfoAlertBinding.inflate(layoutInflater)



        dialog.setContentView(sheetBinding.root)
        dialog.show()
    }

    private fun loadNotes() {

        RetrofitClient.instance.getAllNotes()
            .enqueue(object : retrofit2.Callback<ApiResponse<List<Note>>> {

                override fun onResponse(
                    call: Call<ApiResponse<List<Note>>>,
                    response: retrofit2.Response<ApiResponse<List<Note>>>
                ) {
                    if (response.isSuccessful && response.body() != null) {

                        val notes = response.body()?.data ?: emptyList()

                        Log.d("NOTES_SIZE", "Size: ${notes.size}")

                        if (notes.isEmpty()) {
                            binding.rvNotes.visibility = View.GONE
                            binding.ivBgHome.visibility = View.VISIBLE
                        } else {
                            binding.ivBgHome.visibility = View.GONE
                            binding.rvNotes.visibility = View.VISIBLE

                            binding.rvNotes.layoutManager =
                                androidx.recyclerview.widget.LinearLayoutManager(this@HomeActivity)

                            val adapter = NoteAdapter(notes)
                            binding.rvNotes.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse<List<Note>>>, t: Throwable) {
                    Log.e("API_ERROR", t.message.toString())
                }
            })
    }


}


