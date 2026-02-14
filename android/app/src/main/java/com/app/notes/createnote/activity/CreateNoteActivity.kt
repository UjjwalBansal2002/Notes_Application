package com.app.notes.createnote.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.notes.ApiResponse
import com.app.notes.R
import com.app.notes.RetrofitClient
import com.app.notes.databinding.ActivityCreateNoteBinding
import com.app.notes.model.Note
import retrofit2.Call
import retrofit2.Response

class CreateNoteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCreateNoteBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        header()

    }

    private fun header() {
        binding.header.apply {

            tvHeading.text = getString(R.string.create_note)

            ivSave.visibility = View.VISIBLE
            ivBack.visibility = View.VISIBLE
            ivBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            ivSave.setOnClickListener {
                saveNote()
            }
        }
    }

    private fun saveNote() {

        val title = binding.etTitle.text.toString().trim()
        val content = binding.etDescription.text.toString().trim()

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Title and description required", Toast.LENGTH_SHORT).show()
            return
        }

        val noteData = mapOf(
            "title" to title,
            "content" to content
        )

        RetrofitClient.instance.createNote(noteData)
            .enqueue(object : retrofit2.Callback<ApiResponse<Note>> {



                override fun onResponse(
                    call: Call<ApiResponse<Note>>,
                    response: Response<ApiResponse<Note>>
                ) {

                    Log.d("POST_CODE", response.code().toString())
                    Log.d("POST_RAW", response.body().toString())
                    Log.d("POST_ERROR", response.errorBody()?.string() ?: "no error body")

                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@CreateNoteActivity, "Note Saved", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@CreateNoteActivity, "Server error", Toast.LENGTH_SHORT).show()
                    }
                }



                override fun onFailure(call: Call<ApiResponse<Note>>, t: Throwable) {
                    Log.e("API_ERROR", t.message ?: "Unknown error")
                    Toast.makeText(this@CreateNoteActivity, t.message, Toast.LENGTH_LONG).show()
                }
            })
        Log.d("BASE_URL_TEST", RetrofitClient.instance.toString())
    }

}