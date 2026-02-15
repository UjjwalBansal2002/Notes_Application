const Note = require("../models/note.model");

// Create a new note

const createNote = async (data) => {
    const note = await Note.create({
        title: data.title,
        content: data.content,
        createdAt: Date.now(),
    });

    return note;
};

// Get all notes

const getAllNotes = async () => {
    const notes = await Note.find().sort({ updatedAt: -1 });
    return notes;
};



// Update a note by ID

const updateNoteById = async (id, data) => {
    const note = await Note.findByIdAndUpdate(
        id,
        {
            title: data.title,
            content: data.content,
            updatedAt: Date.now(),
        },
        { new: true }
    );

    return note;
};

// Delete a note by ID
 
const deleteNoteById = async (id) => {
    const note = await Note.findByIdAndDelete(id);
    return note;
};

module.exports = {
    createNote,
    getAllNotes,
    updateNoteById,
    deleteNoteById,
};
