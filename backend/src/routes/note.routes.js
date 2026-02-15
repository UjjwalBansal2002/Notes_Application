const express = require("express");
const router = express.Router();

const noteController = require("../controllers/note.controller");

// Create a note
router.post("/notes", noteController.createNote);

// Get all notes
router.get("/notes", noteController.getAllNotes);

// Update a note
router.put("/notes/:id", noteController.updateNote);

// Delete a note
router.delete("/notes/:id", noteController.deleteNote);

module.exports = router;
