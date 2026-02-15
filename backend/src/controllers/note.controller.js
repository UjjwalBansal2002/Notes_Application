const noteService = require("../services/note.service");

// Create a new note

const createNote = async (req, res, next) => {
  try {
    const { title, content } = req.body;

    // Basic validation
    if (!title || !content) {
      return res.status(400).json({
        success: false,
        message: "Title and content are required",
      });
    }

    const note = await noteService.createNote({ title, content });

    res.status(201).json({
      success: true,
      data: note,
    });
  } catch (error) {
    next(error);
  }
};

// Get all notes

const getAllNotes = async (req, res, next) => {
  try {
    const notes = await noteService.getAllNotes();

    res.status(200).json({
      success: true,
      data: notes,
    });
  } catch (error) {
    next(error);
  }
};

// Update a note

const updateNote = async (req, res, next) => {
  try {
    const { id } = req.params;
    const { title, content } = req.body;

    const note = await noteService.updateNoteById(id, { title, content });

    if (!note) {
      return res.status(404).json({
        success: false,
        message: "Note not found",
      });
    }

    res.status(200).json({
      success: true,
      data: note,
    });
  } catch (error) {
    next(error);
  }
};

//  Delete a note
const deleteNote = async (req, res, next) => {
  try {
    const { id } = req.params;

    const note = await noteService.deleteNoteById(id);

    if (!note) {
      return res.status(404).json({
        success: false,
        message: "Note not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Note deleted successfully",
    });
  } catch (error) {
    next(error);
  }
};

module.exports = {
  createNote,
  getAllNotes,
  updateNote,
  deleteNote,
};
